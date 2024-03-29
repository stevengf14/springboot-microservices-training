package ec.learning.springboot.app.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import brave.Tracer;
import ec.learning.springboot.app.commons.users.models.entity.User;
import ec.learning.springboot.app.oauth.services.IUserService;
import feign.FeignException;

/**
 *
 * @author Steven Guamán - January 2023
 */
@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private Tracer tracer;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		// if (authentication.getName().equalsIgnoreCase("frontendapp")) {
		if (authentication.getDetails() instanceof WebAuthenticationDetails) {
			return;
		}

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String message = "Login Success: " + userDetails.getUsername();
		System.out.println(message);
		log.info(message);

		User user = userService.findByUsername(authentication.getName());
		if (user.getAttempts() != null && user.getAttempts() > 0) {
			user.setAttempts(0);
			userService.update(user, user.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String message = "Login Error: " + exception.getMessage();
		System.out.println(message);
		log.error(message);

		try {
			StringBuilder errors = new StringBuilder();
			errors.append(message);
			User user = userService.findByUsername(authentication.getName());
			if (user.getAttempts() == null) {
				user.setAttempts(0);
			}
			log.info("current number of attempts: " + user.getAttempts());
			user.setAttempts(user.getAttempts() + 1);
			log.info("Number of attempts after update: " + user.getAttempts());
			errors.append(" - " + "Number of attempts after login: " + user.getAttempts());
			if (user.getAttempts() >= 3) {
				String maxAttemptsError = String.format(
						"The user %s has been disabled for exceeding the number of attempts (3)", user.getUsername());
				log.error(maxAttemptsError);
				errors.append(" - " + maxAttemptsError);
				user.setEnabled(false);
			}
			userService.update(user, user.getId());
			tracer.currentSpan().tag("error.message", errors.toString());
		} catch (FeignException e) {
			log.error(String.format("The user %s doesn't exists", authentication.getName()));
		}

	}

}

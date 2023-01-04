package ec.learning.springboot.app.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		// if (authentication.getName().equalsIgnoreCase("frontendapp")) {
		if (authentication.getDetails() instanceof WebAuthenticationDetails) {
			return;
		}

		UserDetails user = (UserDetails) authentication.getPrincipal();
		String message = "Login Success: " + user.getUsername();
		System.out.println(message);
		log.info(message);
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String message = "Login Error: " + exception.getMessage();
		System.out.println(message);
		log.error(message);
	}

}

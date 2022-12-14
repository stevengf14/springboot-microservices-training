package ec.learning.springboot.app.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ec.learning.springboot.app.commons.users.models.entity.User;
import ec.learning.springboot.app.oauth.clients.IUserFeignClient;
import feign.FeignException;

/**
 *
 * @author Steven Guamán - December 2022
 */
@Service
public class UserService implements IUserService, UserDetailsService {

	private Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private IUserFeignClient client;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = client.findByUsername(username);
			List<GrantedAuthority> authorities = user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getName()))
					.peek(authority -> log.info("Role: " + authority.getAuthority())).collect(Collectors.toList());
			log.info("User identified: " + username);
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					user.getEnabled(), true, true, true, authorities);
		} catch (FeignException e) {
			log.error("Login error, user does not exists '" + username + "' in the system");
			throw new UsernameNotFoundException("Login error, user does not exists '" + username + "' in the system");
		}
	}

	@Override
	public User findByUsername(String username) {
		return client.findByUsername(username);
	}

	@Override
	public User update(User user, Long id) {
		return client.update(user, id);
	}

}

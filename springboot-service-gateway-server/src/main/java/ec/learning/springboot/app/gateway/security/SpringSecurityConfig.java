package ec.learning.springboot.app.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 *
 * @author Steven Guamán - January 2023
 */
@EnableWebFluxSecurity
public class SpringSecurityConfig {

	@Bean
	public SecurityWebFilterChain configure(ServerHttpSecurity http) {
		return http.authorizeExchange().pathMatchers("/api/security/oauth/**").permitAll()
				.pathMatchers(HttpMethod.GET, "/api/products/getAll", "/api/items/getAll", "/api/users/users",
						"/api/products/get/{id}", "/api/items/get/{id}/quantity/{quantity}")
				.permitAll().pathMatchers(HttpMethod.GET, "/api/users/users/{id}", "/api/users/users/search/**")
				.hasAnyRole("ADMIN", "PRINCESS")
				.pathMatchers("/api/products/**", "/api/items/**", "/api/users/users/**").hasRole("ADMIN").anyExchange()
				.authenticated().and().csrf().disable().build();
	}
}

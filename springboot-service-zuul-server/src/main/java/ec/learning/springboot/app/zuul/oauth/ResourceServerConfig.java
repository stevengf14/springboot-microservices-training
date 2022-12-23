package ec.learning.springboot.app.zuul.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 *
 * @author Steven Guamán - December 2022
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/security/oauth/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/products/getAll", "/api/items/getAll", "/api/users/users")
				.permitAll()
				.antMatchers(HttpMethod.GET, "/api/products/get/{id}", "/api/items/get/{id}/quantity/{quantity}",
						"/api/users/users/{id}", "/api/users/users/search/**")
				.hasAnyRole("ADMIN", "PRINCESS").antMatchers("/api/products/**", "/api/items/**", "/api/users/users/**")
				.hasRole("ADMIN").anyRequest().authenticated();
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("any_secret_code_aeiou");
		return tokenConverter;
	}

}

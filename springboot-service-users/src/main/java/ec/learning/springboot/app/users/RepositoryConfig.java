package ec.learning.springboot.app.users;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import ec.learning.springboot.app.commons.users.models.entity.Role;
import ec.learning.springboot.app.commons.users.models.entity.User;

/**
 *
 * @author Steven Guamán - December 2022
 */
@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		config.exposeIdsFor(User.class, Role.class);
	}

}

package ec.learning.springboot.app.item;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Steven Guamán - October 2022
 */
@Configuration
public class AppConfig {

	@Bean("restClient")
	@LoadBalanced
	public RestTemplate registerRestTemplate() {
		return new RestTemplate();
	}

}

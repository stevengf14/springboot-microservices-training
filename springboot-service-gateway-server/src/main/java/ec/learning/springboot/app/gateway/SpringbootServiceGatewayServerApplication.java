package ec.learning.springboot.app.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *
 * @author Steven Guamán - November 2022
 */
@EnableEurekaClient
@SpringBootApplication
public class SpringbootServiceGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceGatewayServerApplication.class, args);
	}

}

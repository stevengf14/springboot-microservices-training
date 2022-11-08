package ec.learning.springboot.app.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 * @author Steven Guamán - November 2022
 */
@EnableEurekaServer
@SpringBootApplication
public class SpringbootServiceEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceEurekaServerApplication.class, args);
	}

}

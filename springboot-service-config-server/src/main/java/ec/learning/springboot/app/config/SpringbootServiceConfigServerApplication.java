package ec.learning.springboot.app.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 *
 * @author Steven Guamán - November 2022
 */
@EnableConfigServer
@SpringBootApplication
public class SpringbootServiceConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceConfigServerApplication.class, args);
	}

}

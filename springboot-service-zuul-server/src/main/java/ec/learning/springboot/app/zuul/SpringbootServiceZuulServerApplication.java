package ec.learning.springboot.app.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 *
 * @author Steven Guamán - November 2022
 */
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class SpringbootServiceZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceZuulServerApplication.class, args);
	}

}

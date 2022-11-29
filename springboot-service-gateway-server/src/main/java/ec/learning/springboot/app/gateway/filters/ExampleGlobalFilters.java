package ec.learning.springboot.app.gateway.filters;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
// import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 *
 * @author Steven Guamán - November 2022
 */
@Component
public class ExampleGlobalFilters implements GlobalFilter, Ordered {

	private final Logger logger = LoggerFactory.getLogger(ExampleGlobalFilters.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("Executing pre filter");
		exchange.getRequest().mutate().headers(h -> h.add("token", "123456"));

		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			logger.info("Executing post filter");

			Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent(value -> {
				exchange.getResponse().getHeaders().add("token", value);
			});
			exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "red").build());
			// exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
		}));
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

}
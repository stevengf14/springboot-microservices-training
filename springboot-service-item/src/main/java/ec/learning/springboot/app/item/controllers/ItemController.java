package ec.learning.springboot.app.item.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.aspectj.weaver.ast.HasAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.learning.springboot.app.commons.models.entity.Product;
import ec.learning.springboot.app.item.models.Item;
import ec.learning.springboot.app.item.models.service.IItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

/**
 *
 * @author Steven Guamán - November 2022
 */
@RefreshScope
@RestController
public class ItemController {

	private final Logger logger = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private Environment env;

	@Autowired
	private CircuitBreakerFactory cbFactory;

	@Autowired
	@Qualifier("itemServiceFeign") // @Qualifier("serviceRestTemplate")
	private IItemService itemService;

	@Value("${configuration.text}")
	private String text;

	@GetMapping("/getAll")
	public List<Item> getAll(@RequestParam(name = "name", required = false) String name,
			@RequestHeader(name = "token-request", required = false) String token) {
		System.out.println(name);
		System.out.println(token);
		return itemService.findAll();
	}

	@GetMapping("get/{id}/quantity/{quantity}")
	public Item get(@PathVariable Long id, @PathVariable Integer quantity) {
		return cbFactory.create("items").run(() -> itemService.findById(id, quantity),
				e -> alternativeMethod(id, quantity, e));
	}

	@CircuitBreaker(name = "items", fallbackMethod = "alternativeMethod")
	@GetMapping("get2/{id}/quantity/{quantity}")
	public Item get2(@PathVariable Long id, @PathVariable Integer quantity) {
		return itemService.findById(id, quantity);
	}

	@CircuitBreaker(name = "items", fallbackMethod = "alternativeMethod2")
	@TimeLimiter(name = "items")
	@GetMapping("get3/{id}/quantity/{quantity}")
	public CompletableFuture<Item> get3(@PathVariable Long id, @PathVariable Integer quantity) {
		return CompletableFuture.supplyAsync(() -> itemService.findById(id, quantity));
	}

	public Item alternativeMethod(Long id, Integer quantity, Throwable e) {
		logger.info(e.getMessage());
		Item item = new Item();
		item.setQuantity(quantity);
		Product product = new Product();
		product.setId(id);
		product.setName("Sony camera default");
		product.setPrice(500.00);
		item.setProduct(product);
		return item;
	}

	public CompletableFuture<Item> alternativeMethod2(Long id, Integer quantity, Throwable e) {
		logger.info(e.getMessage());
		Item item = new Item();
		item.setQuantity(quantity);
		Product product = new Product();
		product.setId(id);
		product.setName("Sony camera default");
		product.setPrice(500.00);
		item.setProduct(product);
		return CompletableFuture.supplyAsync(() -> item);
	}

	@GetMapping("/get-config")
	public ResponseEntity<?> getConfig(@Value("${server.port}") String port) {
		logger.info(text);
		Map<String, String> json = new HashMap<>();
		json.put("text", text);
		json.put("port", port);
		if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equalsIgnoreCase("dev")) {
			json.put("author.name", env.getProperty("configuration.author.name"));
			json.put("author.email", env.getProperty("configuration.author.email"));
		}

		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Product create(@RequestBody Product product) {
		return itemService.save(product);
	}

	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product edit(@RequestBody Product product, @PathVariable Long id) {
		return itemService.update(product, id);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		itemService.delete(id);
	}

}

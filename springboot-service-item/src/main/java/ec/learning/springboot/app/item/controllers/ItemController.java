package ec.learning.springboot.app.item.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ec.learning.springboot.app.item.models.Item;
import ec.learning.springboot.app.item.models.Product;
import ec.learning.springboot.app.item.models.service.IItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

/**
 *
 * @author Steven Guamán - November 2022
 */
@RestController
public class ItemController {

	private final Logger logger = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private CircuitBreakerFactory cbFactory;

	@Autowired
	@Qualifier("itemServiceFeign") // @Qualifier("serviceRestTemplate")
	private IItemService itemService;

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

}

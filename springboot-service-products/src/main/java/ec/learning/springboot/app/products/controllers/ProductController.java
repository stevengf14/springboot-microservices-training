package ec.learning.springboot.app.products.controllers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ec.learning.springboot.app.products.models.service.IProductService;
import ec.learning.springboot.app.products.models.entity.Product;

/**
 *
 * @author Steven Guamán - October 2022
 */
@RestController
public class ProductController {

	@Autowired
	private Environment env;

	@Value("${server.port}")
	private Integer port;

	@Autowired
	private IProductService productService;

	@GetMapping("/getAll")
	public List<Product> getAll() {
		return productService.findAll().stream().map(product -> {
			// product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			product.setPort(port);
			return product;
		}).collect(Collectors.toList());
	}

	@GetMapping("/get/{id}")
	public Product get(@PathVariable Long id) throws InterruptedException {

		// to make tests
		if (id.equals(10L)) {
			throw new IllegalStateException("Product not exists");
		}
		if (id.equals(7L)) {
			TimeUnit.SECONDS.sleep(5L);
		}

		Product product = productService.findById(id);
		product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		// product.setPort(port);

		return product;
	}

}

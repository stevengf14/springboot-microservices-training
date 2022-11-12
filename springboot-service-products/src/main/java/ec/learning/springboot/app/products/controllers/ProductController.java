package ec.learning.springboot.app.products.controllers;

import java.util.List;
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
	public Product get(@PathVariable Long id) {
		Product product = productService.findById(id);
		// product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		product.setPort(port);

		/*try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/

		return product;
	}

}

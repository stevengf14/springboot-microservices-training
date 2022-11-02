package ec.learning.springboot.app.products.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	private IProductService productService;

	@GetMapping("/getAll")
	public List<Product> getAll() {
		return productService.findAll();
	}

	@GetMapping("/get/{id}")
	public Product get(@PathVariable Long id) {
		return productService.findById(id);
	}

}

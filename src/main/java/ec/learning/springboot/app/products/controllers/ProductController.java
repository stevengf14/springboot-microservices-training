package ec.learning.springboot.app.products.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ec.learning.springboot.app.products.models.service.IProductService;
import ec.learning.springboot.app.products.models.entity.Product;

@RestController
public class ProductController {

	@Autowired
	private IProductService productService;

	@GetMapping("/toList")
	public List<Product> toList() {
		return productService.findAll();
	}

	@GetMapping("/toList/{id}")
	public Product detail(@PathVariable Long id) {
		return productService.findById(id);
	}

}

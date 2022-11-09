package ec.learning.springboot.app.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import ec.learning.springboot.app.item.models.Item;
import ec.learning.springboot.app.item.models.Product;
import ec.learning.springboot.app.item.models.service.IItemService;

/**
 *
 * @author Steven Guamán - November 2022
 */
@RestController
public class ItemController {

	@Autowired
	@Qualifier("itemServiceFeign") // @Qualifier("serviceRestTemplate")
	private IItemService itemService;

	@GetMapping("/getAll")
	public List<Item> getAll() {
		return itemService.findAll();
	}

	@HystrixCommand(fallbackMethod = "alternativeMethdo")
	@GetMapping("get/{id}/quantity/{quantity}")
	public Item get(@PathVariable Long id, @PathVariable Integer quantity) {
		return itemService.findById(id, quantity);
	}

	public Item alternativeMethdo(Long id, Integer quantity) {
		Item item = new Item();
		item.setQuantity(quantity);
		Product product = new Product();
		product.setId(id);
		product.setName("Sony camera default");
		product.setPrice(500.00);
		item.setProduct(product);
		return item;
	}
}

package ec.learning.springboot.app.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ec.learning.springboot.app.item.models.Item;
import ec.learning.springboot.app.item.models.service.IItemService;

/**
 *
 * @author Steven Guamán - November 2022
 */
@RestController
public class ItemController {
	
	@Autowired
	@Qualifier("itemServiceFeign")
	private IItemService itemService;
	
	@GetMapping("/getAll")
	public List<Item> getAll(){
		return itemService.findAll();
	}
	
	@GetMapping("get/{id}/quantity/{quantity}")
	public Item get(@PathVariable Long id, @PathVariable Integer quantity) {
		return itemService.findById(id, quantity);
	}

}

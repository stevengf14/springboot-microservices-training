package ec.learning.springboot.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.learning.springboot.app.item.clients.IProductClientRest;
import ec.learning.springboot.app.item.models.Item;
import ec.learning.springboot.app.item.models.Product;

/**
 *
 * @author Steven Guamán - November 2022
 */
@Service("itemServiceFeign")
public class ItemServiceFeign implements IItemService {

	@Autowired
	private IProductClientRest feignClient;

	@Override
	public List<Item> findAll() {
		return feignClient.getAll().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		return new Item(feignClient.get(id), quantity);
	}

	@Override
	public Product save(Product product) {
		return feignClient.create(product);
	}

	@Override
	public Product update(Product product, Long id) {
		return feignClient.edit(product, id);
	}

	@Override
	public void delete(Long id) {
		feignClient.delete(id);
	}

}

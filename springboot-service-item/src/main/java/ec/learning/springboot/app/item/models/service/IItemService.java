package ec.learning.springboot.app.item.models.service;

import java.util.List;

import ec.learning.springboot.app.item.models.Item;
import ec.learning.springboot.app.item.models.Product;

/**
 *
 * @author Steven Guamán - October 2022
 */
public interface IItemService {

	public List<Item> findAll();

	public Item findById(Long id, Integer quantity);

	public Product save(Product product);

	public Product update(Product product, Long id);

	public void delete(Long id);

}

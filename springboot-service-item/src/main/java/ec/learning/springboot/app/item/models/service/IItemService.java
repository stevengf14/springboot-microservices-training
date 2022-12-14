package ec.learning.springboot.app.item.models.service;

import java.util.List;

import ec.learning.springboot.app.commons.models.entity.Product;
import ec.learning.springboot.app.item.models.Item;

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

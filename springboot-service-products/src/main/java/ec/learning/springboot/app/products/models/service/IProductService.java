package ec.learning.springboot.app.products.models.service;

import java.util.List;

import ec.learning.springboot.app.products.models.entity.Product;

/**
 *
 * @author Steven Guamán - October 2022
 */
public interface IProductService {

	public List<Product> findAll();

	public Product findById(Long id);

	public Product save(Product product);

	public void deleteById(Long id);

}

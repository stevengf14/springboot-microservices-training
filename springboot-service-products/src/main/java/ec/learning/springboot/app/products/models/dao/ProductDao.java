package ec.learning.springboot.app.products.models.dao;

import org.springframework.data.repository.CrudRepository;

import ec.learning.springboot.app.commons.models.entity.Product;

/**
 *
 * @author Steven Guamán - October 2022
 */
public interface ProductDao extends CrudRepository<Product, Long> {

}

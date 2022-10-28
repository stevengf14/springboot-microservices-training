package ec.learning.springboot.app.products.models.dao;

import org.springframework.data.repository.CrudRepository;

import ec.learning.springboot.app.products.models.entity.Product;

public interface ProductDao extends CrudRepository<Product, Long> {

}

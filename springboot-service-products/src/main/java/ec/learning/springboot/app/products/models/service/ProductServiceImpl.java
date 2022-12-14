package ec.learning.springboot.app.products.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.learning.springboot.app.commons.models.entity.Product;
import ec.learning.springboot.app.products.models.dao.ProductDao;

/**
 *
 * @author Steven Guamán - October 2022
 */
@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return (List<Product>) productDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {
		return productDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Product save(Product product) {
		return productDao.save(product);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		productDao.deleteById(id);
	}

}

package ec.learning.springboot.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ec.learning.springboot.app.item.models.Item;
import ec.learning.springboot.app.item.models.Product;

/**
 *
 * @author Steven Guamán - October 2022
 */
@Service("serviceRestTemplate")
public class ItemServiceImpl implements IItemService {

	@Autowired
	private RestTemplate restClient;

	@Override
	public List<Item> findAll() {
		List<Product> products = Arrays
				.asList(restClient.getForObject("http://localhost:8001/getAll", Product[].class));
		return products.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Product product = restClient.getForObject("http://localhost:8001/get/{id}", Product.class, pathVariables);
		return new Item(product, quantity);
	}

}

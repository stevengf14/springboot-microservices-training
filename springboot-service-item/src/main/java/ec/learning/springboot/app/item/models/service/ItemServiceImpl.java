package ec.learning.springboot.app.item.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ec.learning.springboot.app.item.models.Item;

/**
 *
 * @author Steven Guamán - October 2022
 */
@Service
public class ItemServiceImpl implements IItemService {

	@Autowired
	private RestTemplate restClient;
	
	@Override
	public List<Item> findAll() {
		return null;
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		return null;
	}

}

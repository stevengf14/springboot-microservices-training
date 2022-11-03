package ec.learning.springboot.app.item.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ec.learning.springboot.app.item.models.Product;

@FeignClient(name = "service-products", url = "localhost:8001")
public interface IProductClientRest {
	
	@GetMapping("/getAll")
	public List<Product> getAll();

	@GetMapping("/get/{id}")
	public Product get(@PathVariable Long id);
	
}

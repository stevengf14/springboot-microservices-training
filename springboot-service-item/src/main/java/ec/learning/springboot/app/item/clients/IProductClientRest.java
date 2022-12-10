package ec.learning.springboot.app.item.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ec.learning.springboot.app.item.models.Product;

/**
 *
 * @author Steven Guamán - November 2022
 */
@FeignClient(name = "service-products")
public interface IProductClientRest {

	@GetMapping("/getAll")
	public List<Product> getAll();

	@GetMapping("/get/{id}")
	public Product get(@PathVariable Long id);

	@PostMapping("/create")
	public Product create(@RequestBody Product product);

	@PutMapping("/edit/{id}")
	public Product edit(@RequestBody Product product, @PathVariable Long id);

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id);

}

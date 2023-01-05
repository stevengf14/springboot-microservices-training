package ec.learning.springboot.app.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ec.learning.springboot.app.commons.users.models.entity.User;

/**
 *
 * @author Steven Guamán - December 2022
 */
@FeignClient(name = "service-users")
public interface IUserFeignClient {

	@GetMapping("/users/search/get-username")
	public User findByUsername(@RequestParam String username);

	@PutMapping("/users/{id}")
	public User update(@RequestBody User user, @PathVariable Long id);

}

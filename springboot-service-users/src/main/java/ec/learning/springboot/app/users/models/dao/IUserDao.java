package ec.learning.springboot.app.users.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ec.learning.springboot.app.users.models.entity.User;

/**
 *
 * @author Steven Guamán - December 2022
 */
@RepositoryRestResource(path = "users")
public interface IUserDao extends PagingAndSortingRepository<User, Long> {

	public User findByUsername(String username);

	@Query("select u from User u where u.username=?1")
	public User getByUsername(String username);
}

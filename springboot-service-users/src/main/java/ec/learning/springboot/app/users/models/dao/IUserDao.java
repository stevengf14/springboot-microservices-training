package ec.learning.springboot.app.users.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import ec.learning.springboot.app.commons.users.models.entity.User;

/**
 *
 * @author Steven Guamán - December 2022
 */
@RepositoryRestResource(path = "users")
public interface IUserDao extends PagingAndSortingRepository<User, Long> {

	@RestResource(path = "get-username")
	public User findByUsername(@Param("username") String username);

	@Query("select u from User u where u.username=?1")
	public User getByUsername(String username);
}

package ec.learning.springboot.app.oauth.services;

import ec.learning.springboot.app.commons.users.models.entity.User;

/**
 *
 * @author Steven Guamán - December 2022
 */
public interface IUserService {

	public User findByUsername(String username);

}

package com.sdi.business.impl.user;

import java.util.List;

import javax.ejb.Stateless;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.impl.classes.UsersAlta;
import com.sdi.business.impl.classes.UsersBuscar;
import com.sdi.business.impl.classes.UsersListado;
import com.sdi.model.User;

/**
 * Clase de implementación (una de las posibles) del interfaz de la fachada de
 * servicios
 * 
 * @author Enrique
 * 
 */
@Stateless
public class EjbUserService implements LocalUserService,RemoteUserService {

	@Override
	public List<User> getUsers() throws Exception{
		return new UsersListado().getUsers();
	}

	@Override
	public void saveUser(User user) throws EntityAlreadyExistsException {
		new UsersAlta().save(user);
	}

	@Override
	public User findById(Long id) {
		return new UsersBuscar().find(id);
	}

	@Override
	public User finByLogin(String login) {
		return new UsersBuscar().findByLogin(login);
	}
}

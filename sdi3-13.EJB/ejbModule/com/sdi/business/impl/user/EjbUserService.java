package com.sdi.business.impl.user;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.sdi.business.impl.classes.UsersBuscar;
import com.sdi.business.impl.classes.UsersListado;
import com.sdi.business.impl.classes.UsersManagement;
import com.sdi.model.User;

/**
 * Clase de implementación (una de las posibles) del interfaz de la fachada de
 * servicios
 * 
 * @author Enrique
 * 
 */
@Stateless
@WebService(name="UserService")
public class EjbUserService implements LocalUserService,RemoteUserService {

	@Override
	public List<User> getUsers(){
		return new UsersListado().getUsers();
	}

	@Override
	public void saveUser(User user){
		new UsersManagement().save(user);
	}

	@Override
	@WebMethod
	public User findById(Long id) {
		return new UsersBuscar().find(id);
	}

	@Override
	public User finByLogin(String login) {
		return new UsersBuscar().findByLogin(login);
	}

	@Override
	public void unsubscribe(String login) {
		new UsersManagement().unsubscribe(login);
		
	}

}

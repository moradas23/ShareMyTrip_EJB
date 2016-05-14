package com.sdi.rest;

import java.util.List;

import com.sdi.business.UserService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;

public class UserServiceRestImpl implements UserServiceRest {

	UserService service = Factories.services.getUserService();
	
	@Override
	public User getUserByLogin(String login) {
		return service.findByLogin(login);
	}

	
	@Override
	public List<User> getUsers() {
		return service.getUsers();
	}

}

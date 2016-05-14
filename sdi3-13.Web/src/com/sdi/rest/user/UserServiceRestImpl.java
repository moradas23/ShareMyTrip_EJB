package com.sdi.rest.user;

import com.sdi.business.UserService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;

public class UserServiceRestImpl implements UserServiceRest {

	UserService service = Factories.services.getUserService();
	
	@Override
	public User getUserById(Long id) {
		return service.findById(id);
	}

}

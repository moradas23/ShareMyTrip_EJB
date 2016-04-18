package com.sdi.business.impl.classes;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.persistence.UserDao;


public class UsersListado {

	public List<User> getUsers() {
		
		UserDao dao = Factories.persistence.createUserDao();
		return dao.findAll();
	}
}

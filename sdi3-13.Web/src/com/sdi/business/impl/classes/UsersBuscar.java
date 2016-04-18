package com.sdi.business.impl.classes;

import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.persistence.UserDao;

public class UsersBuscar {

	public User find(Long id) {
		UserDao dao = Factories.persistence.createUserDao();
		User u = dao.findById(id);
		return u;
	}
	
	public User findByLogin(String login) {
		UserDao dao = Factories.persistence.createUserDao();
		User u = dao.findByLogin(login);
		return u;
	}

}

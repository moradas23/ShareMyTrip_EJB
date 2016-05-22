package com.sdi.business.impl.classes;

import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.model.UserLogin;
import com.sdi.model.UserStatus;
import com.sdi.persistence.UserDao;
import com.sdi.utilidades.Utilidades;

public class LoginVerify {
	
	public boolean verifyPassword(String login,String password){
		UserDao dao = Factories.persistence.createUserDao();
		User user = dao.findByLogin(login);
		
		String pssw = Utilidades.getStringMessageDigest(password, Utilidades.MD5);
		
		if(user==null || user.getStatus().equals(UserStatus.CANCELLED))
			return false;
		if(user.getPassword().compareTo(pssw)==0)
			return true;
		return false;
	}

	public static UserLogin getUserLogin(String login) {
		UserDao dao = Factories.persistence.createUserDao();
		User user = dao.findByLogin(login);
		
		UserLogin userLogin = new UserLogin(login, user.getName(),user.getId());
		
		return userLogin;
	}
}

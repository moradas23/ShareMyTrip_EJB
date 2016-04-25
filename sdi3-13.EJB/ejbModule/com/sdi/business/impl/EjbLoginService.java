package com.sdi.business.impl;

import javax.ejb.Stateless;

import com.sdi.business.impl.classes.LoginVerify;
import com.sdi.model.UserLogin;

@Stateless
public class EjbLoginService implements LocalLoginService,RemoteLoginService{

	@Override
	public UserLogin verify(String login, String password) {
		if (!validLogin(login, password))
			return null;
		return getUserLogin(login);
	}
	
	private UserLogin getUserLogin(String login){
		return LoginVerify.getUserLogin(login);
	}
	
	private boolean validLogin(String login, String password) {
		return new LoginVerify().verifyPassword(login, password);
	}

}

package com.sdi.rest.login;

import com.sdi.business.LoginService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.UserLogin;



public class LoginServiceRestImpl implements LoginServiceRest {

	LoginService service = Factories.services.getLoginService();
	
	@Override
	public UserLogin verify(String login, String password) {
		return service.verify(login, password);
		
	}

}

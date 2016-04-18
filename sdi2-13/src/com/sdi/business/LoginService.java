package com.sdi.business;

import com.sdi.model.UserLogin;

public interface LoginService {
	UserLogin verify(String login, String password);
}

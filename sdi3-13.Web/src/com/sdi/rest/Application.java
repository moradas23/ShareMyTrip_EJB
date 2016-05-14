package com.sdi.rest;

import java.util.HashSet;
import java.util.Set;

import com.sdi.rest.login.LoginServiceRestImpl;
import com.sdi.rest.trip.TripsServiceRestImpl;
import com.sdi.rest.user.UserServiceRestImpl;

public class Application extends javax.ws.rs.core.Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> res = new HashSet<>();
		res.add(TripsServiceRestImpl.class);
		res.add(LoginServiceRestImpl.class);
		res.add(UserServiceRestImpl.class);
		return res;
	}
	
}
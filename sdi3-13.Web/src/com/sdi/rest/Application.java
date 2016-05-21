package com.sdi.rest;

import java.util.HashSet;
import java.util.Set;

public class Application extends javax.ws.rs.core.Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> res = new HashSet<>();
		res.add(TripsServiceRestImpl.class);
		res.add(LoginServiceRestImpl.class);
		res.add(UserServiceRestImpl.class);
		res.add(ApplicationServiceRestImpl.class);
		res.add(SeatServiceRestImpl.class);
		return res;
	}
	
}
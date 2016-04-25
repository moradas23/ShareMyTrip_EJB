package com.sdi.business.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sdi.business.ApplicationService;
import com.sdi.business.LoginService;
import com.sdi.business.SeatService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.TripsService;
import com.sdi.business.UsersService;

public class LocalEjbServicesLocator implements ServicesFactory {
	private static final String LOGIN_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi3-13/" + "sdi3-13.EJB/" + "EjbLoginService!"
			+ "com.sdi.business.impl.LocalLoginService";

	@Override
	public LoginService getLoginService() {
		System.out.println("Using remote services locator");
		try {
			Context ctx = new InitialContext();
			return (LoginService) ctx.lookup(LOGIN_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public UsersService getUserService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripsService getTripService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SeatService getSeatService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicationService getApplicationService() {
		// TODO Auto-generated method stub
		return null;
	}
}

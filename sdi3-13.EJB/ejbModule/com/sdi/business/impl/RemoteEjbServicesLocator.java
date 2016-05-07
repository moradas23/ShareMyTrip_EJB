package com.sdi.business.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sdi.business.ApplicationService;
import com.sdi.business.LoginService;
import com.sdi.business.RatingService;
import com.sdi.business.SeatService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.TripsService;
import com.sdi.business.UsersService;

public class RemoteEjbServicesLocator implements ServicesFactory {

	private static final String LOGIN_SERVICE_JNDI_KEY =
			//"java:global/" + 
			"sdi3-13/"
			+ "sdi3-13.EJB/" 
			+ "EjbLoginService!"
			+ "com.sdi.business.impl.login.RemoteLoginService";
	
	private static final String APPLICATION_SERVICE_JNDI_KEY =
			//"java:global/" + 
			"sdi3-13/"
			+ "sdi3-13.EJB/" 
			+ "EjbApplicationService!"
			+ "com.sdi.business.impl.application.RemoteLoginService";
	
	private static final String TRIP_SERVICE_JNDI_KEY =
			//"java:global/" + 
			"sdi3-13/"
			+ "sdi3-13.EJB/" 
			+ "EjbTripService!"
			+ "com.sdi.business.impl.trip.RemoteTripService";
	
	private static final String SEAT_SERVICE_JNDI_KEY =
			//"java:global/" + 
			"sdi3-13/"
			+ "sdi3-13.EJB/" 
			+ "EjbSeatService!"
			+ "com.sdi.business.impl.seat.RemoteSeatService";
	
	private static final String USER_SERVICE_JNDI_KEY =
			//"java:global/" + 
			"sdi3-13/"
			+ "sdi3-13.EJB/" 
			+ "EjbUserService!"
			+ "com.sdi.business.impl.user.RemoteUserService";
	
	private static final String RATING_SERVICE_JNDI_KEY = 
			"sdi3-13/"
			+ "sdi3-13.EJB/" 
			+ "EjbUserService!"
			+ "com.sdi.business.impl.rating.RemoteRatingService";
			

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
		System.out.println("Using remote services locator");
		try {
			Context ctx = new InitialContext();
			return (UsersService) ctx.lookup(USER_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public TripsService getTripService() {
		System.out.println("Using remote services locator");
		try {
			Context ctx = new InitialContext();
			return (TripsService) ctx.lookup(TRIP_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public SeatService getSeatService() {
		System.out.println("Using remote services locator");
		try {
			Context ctx = new InitialContext();
			return (SeatService) ctx.lookup(SEAT_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public ApplicationService getApplicationService() {
		System.out.println("Using remote services locator");
		try {
			Context ctx = new InitialContext();
			return (ApplicationService) ctx.lookup(APPLICATION_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	public RatingService getRatingService() {
		System.out.println("Using remote services locator");
		try {
			Context ctx = new InitialContext();
			return (RatingService) ctx.lookup(RATING_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}


}

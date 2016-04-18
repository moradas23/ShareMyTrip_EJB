package com.sdi.business.impl;



import com.sdi.business.ApplicationService;
import com.sdi.business.LoginService;
import com.sdi.business.SeatService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.TripsService;
import com.sdi.business.UsersService;

public class SimpleServicesFactory implements ServicesFactory {


	public UsersService createUserService() {
		return new SimpleUserService();
	}

	public TripsService createTripService() {
		return new SimpleTripService();
	}

	public LoginService createLoginService() {
		return new SimpleLoginService();

	}

	public SeatService createSeatService() {
		return new SimpleSeatService();
	}

	@Override
	public ApplicationService createApplicationService() {
		return new SimpleApplicationService();
	}

}

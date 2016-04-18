package com.sdi.business;

public interface ServicesFactory {
	
	UsersService createUserService();
	TripsService createTripService();
	LoginService createLoginService();
	SeatService createSeatService();
	ApplicationService createApplicationService();

}

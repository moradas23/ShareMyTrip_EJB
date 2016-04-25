package com.sdi.business;

public interface ServicesFactory {
	
	UsersService getUserService();
	TripsService getTripService();
	LoginService getLoginService();
	SeatService getSeatService();
	ApplicationService getApplicationService();

}


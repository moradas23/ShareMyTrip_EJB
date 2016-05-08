package com.sdi.business;

public interface ServicesFactory {
	
	UserService getUserService();
	TripService getTripService();
	LoginService getLoginService();
	SeatService getSeatService();
	ApplicationService getApplicationService();

}


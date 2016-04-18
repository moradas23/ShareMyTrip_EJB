package com.sdi.persistence;

public interface PersistenceFactory {
	
	UserDao createUserDao();
	TripDao createTripDao();
	SeatDao createSeatDao();
	ApplicationDao createApplicationDao();
	
	
}


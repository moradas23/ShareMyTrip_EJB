package com.sdi.persistence.impl;


import com.sdi.persistence.ApplicationDao;
import com.sdi.persistence.PersistenceFactory;
import com.sdi.persistence.RatingDao;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;
import com.sdi.persistence.UserDao;

/**
 * Implementacion de la factoria que devuelve implementaci??????n de la capa
 * de persistencia con Jdbc 
 * 
 * @author alb
 *
 */
public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public UserDao createUserDao() {
		return new UserJdbcDAO();
	}

	@Override
	public TripDao createTripDao() {
		return new TripJdbcDAO();
	}

	@Override
	public SeatDao createSeatDao() {
		return new SeatJdbcDAO();
	}

	@Override
	public ApplicationDao createApplicationDao() {
		return new ApplicationJdbcDAO();
	}

	@Override
	public RatingDao createRatingDao() {
		return new RatingJdbcDAO();
	}

}

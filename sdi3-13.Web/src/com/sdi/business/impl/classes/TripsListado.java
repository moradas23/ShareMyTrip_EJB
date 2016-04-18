package com.sdi.business.impl.classes;


import java.util.List;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.persistence.TripDao;



public class TripsListado {

	public List<Trip> getTrips() {
		TripDao dao = Factories.persistence.createTripDao();
		return dao.findAll();
	}
	
	public List<Trip> getTripsDisponibles() {
		TripDao dao = Factories.persistence.createTripDao();
		return dao.findAllDisponible();
	}

	public List<Trip> getTripsDisponiblesUser(String login) {
		TripDao dao = Factories.persistence.createTripDao();
		return dao.findAllDisponibleUser(login);
	}

}

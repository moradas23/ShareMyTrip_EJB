package com.sdi.business.impl.classes;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.persistence.TripDao;


public class TripsBuscar {

	public Trip getTrip(Long id) {
		TripDao dao = Factories.persistence.createTripDao();
		return dao.findById(id);
	}


	public List<Trip> getByPromoter(Long id) {
		TripDao dao = Factories.persistence.createTripDao();
		return dao.findByPromoterId(id);
	}


	public List<Trip> fechaCierrePasada() {
		TripDao dao = Factories.persistence.createTripDao();
		return dao.findFechaCierrePasada();
	}
}

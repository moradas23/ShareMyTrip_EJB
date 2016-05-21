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


	public List<Trip> findLastMonth() {
		TripDao dao = Factories.persistence.createTripDao();
		return dao.findLastMonth();
	}


	public List<Trip> getByPromoterAndDone(Long idUser) {
		TripDao dao = Factories.persistence.createTripDao();
		return dao.findByPromoterIdAndDone(idUser);
	}


	public List<Trip> findHaParticipado(Long idUser) {
		TripDao dao = Factories.persistence.createTripDao();
		return dao.findHaParticipado(idUser);
	}


	public List<Trip> getByPromoterAndAvailablePax(Long id) {
		TripDao dao = Factories.persistence.createTripDao();
		return dao.findByPromoterIdAndAvailablePax(id);
	}


	public List<Trip> findByAceptado(Long idUsuario) {
		TripDao dao = Factories.persistence.createTripDao();
		return dao.findParticipa(idUsuario);
	}



}

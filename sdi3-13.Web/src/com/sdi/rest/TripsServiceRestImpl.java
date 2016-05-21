package com.sdi.rest;

import java.util.List;

import com.sdi.business.TripService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

public class TripsServiceRestImpl implements TripsServiceRest {

	TripService service = Factories.services.getTripService();

	@Override
	public List<Trip> getPromotedTrips(Long id) {
		return service.findByPromoterAndAvailablePax(id);
	}

	@Override
	public Trip getTrip(Long id) {
		return service.findByIdTrip(id);
	}

	@Override
	public void actualizarViaje(Trip trip) {
		service.actualizar(trip);
	}
	
}

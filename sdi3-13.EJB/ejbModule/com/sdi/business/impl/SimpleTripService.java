package com.sdi.business.impl;

import java.util.List;
import java.util.Map;

import com.sdi.business.TripsService;
import com.sdi.business.impl.classes.TripRegistrar;
import com.sdi.business.impl.classes.TripsBuscar;
import com.sdi.business.impl.classes.TripsCancel;
import com.sdi.business.impl.classes.TripsListado;
import com.sdi.business.impl.classes.TripsModificar;
import com.sdi.dto.ModificarViajeDto;
import com.sdi.dto.RegistrarViajeDto;
import com.sdi.model.Trip;


public class SimpleTripService implements TripsService {

	@Override
	public List<Trip> findAll() {
		return new TripsListado().getTrips();
	}

	@Override
	public List<Trip> findAllDisponible() {
		return new TripsListado().getTripsDisponibles();
	}

	@Override
	public List<Trip> findAllDisponibleUser(String login) {
		return new TripsListado().getTripsDisponiblesUser(login);
	}

	@Override
	public boolean registrar(RegistrarViajeDto trip) {
		return new TripRegistrar().registerTrip(trip);
	}

	public Trip findById(Long tripId) {
		return new TripsBuscar().getTrip(tripId);
	}

	@Override
	public List<Trip> findByPromoter(Long id) {
		return new TripsBuscar().getByPromoter(id);
	}

	@Override
	public boolean modificar(ModificarViajeDto trip) {
		return new TripsModificar().modificarTrip(trip);
	}

	@Override
	public void cancelarViajes(Map<Long, Trip> viajesCancelar) {
		new TripsCancel().cancelarViajes(viajesCancelar);
	}
	
	@Override
	public void ocuparPlaza(Long idViaje) {
		new TripsModificar().disminuirPlazas(idViaje);	
	}
	
	@Override
	public void liberarPlaza(Long idViaje) {
		new TripsModificar().aumentarPlazas(idViaje);

	}

	@Override
	public List<Trip> viajesFechaCierrePasada() {
		return new TripsBuscar().fechaCierrePasada();
	}

	@Override
	public void cerrarViaje(Trip viaje) {
		new TripsModificar().cerrarViaje(viaje);
	}



}

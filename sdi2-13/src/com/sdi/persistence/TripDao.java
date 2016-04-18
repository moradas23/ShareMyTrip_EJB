package com.sdi.persistence;

import java.util.Date;
import java.util.List;

import com.sdi.model.Trip;
import com.sdi.persistence.util.GenericDao;

public interface TripDao extends GenericDao<Trip, Long> {

	Trip findByPromoterIdAndArrivalDate(Long id, Date arrivalDate);
	
	List<Trip> findByPromoterId(Long id);

	List<Trip> findAllDisponible();

	List<Trip> findAllDisponibleUser(String login);

	int disminuirPlazas(Long idViaje);

	int aumentarPlazas(Long idViaje);

	List<Trip> findFechaCierrePasada();


}

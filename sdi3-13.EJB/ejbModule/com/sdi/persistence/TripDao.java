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

	List<Trip> findLastMonth();

	/**
	 * Devuelve la listad de viajes en los que el usuario fue promotor
	 * 
	 * @param idUser
	 * @return
	 */
	List<Trip> findByPromoterIdAndDone(Long idUser);

	/**
	 * Devuelve los viajes en los que el usuario ha participado
	 * 
	 * @param idUser
	 * @return
	 */
	List<Trip> findHaParticipado(Long idUser);


}

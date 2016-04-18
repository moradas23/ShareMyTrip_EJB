package com.sdi.business;

import java.util.List;
import java.util.Map;

import com.sdi.model.Trip;
import com.sdi.presentation.BeanModifyTrip;
import com.sdi.presentation.BeanRegisterTrip;


public interface TripsService {

	List<Trip> findAll();
	List<Trip> findAllDisponible();
	
	/**
	 * Busca los viajes disponibles para un usuario registrado, teniendo en
	 * cuenta que no se puede apuntar a viajes en los que es promotor,
	 * está aceptado o mandó una solicitud
	 * 
	 * @param login
	 * @return
	 */
	List<Trip> findAllDisponibleUser(String login);
	boolean registrar(BeanRegisterTrip trip);
	Trip findById(Long tripId);
	List<Trip> findByPromoter(Long id);
	boolean modificar(BeanModifyTrip beanModificarViaje);
	void cancelarViajes(Map<Long, Trip> viajesCancelar);

	/**
	 * Disminuye en uno las plazas disponibles para el viaje
	 * 
	 * @param idViaje
	 */
	void ocuparPlaza(Long idViaje);
	
	/**
	 * Aumenta en uno las plazas disponibles para el viaje
	 * 
	 * @param idViaje
	 */
	void liberarPlaza(Long idViaje);
	
	/**
	 * Se obtienen los viajes cuya fecha de cierre ya se ha pasado y que su
	 * estado sea OPEN
	 * @return
	 */
	List<Trip> viajesFechaCierrePasada();
	
	/**
	 * Método para cancelar un viaje
	 * 
	 * @param viaje
	 */
	void cerrarViaje(Trip viaje);



}
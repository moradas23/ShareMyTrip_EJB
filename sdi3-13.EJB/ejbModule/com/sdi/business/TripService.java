package com.sdi.business;

import java.util.List;
import java.util.Map;

import com.sdi.dto.ModificarViajeDto;
import com.sdi.dto.RegistrarViajeDto;
import com.sdi.model.Trip;



public interface TripService {

	List<Trip> findAllTrip();
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
	
	boolean registrar(RegistrarViajeDto trip);
	
	Trip findByIdTrip(Long tripId);
	
	
	/**
	 * Busca los viajes en los que id es promotor y que todavía estén abiertos
	 * 
	 * @param id
	 * @return
	 */
	List<Trip> findByPromoter(Long id);
	
	boolean modificar(ModificarViajeDto beanModificarViaje);
	
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
	 * Cancelar un viaje pasado como parámetro
	 * 
	 * @param viaje
	 */
	void cerrarViaje(Trip viaje);
	
	/**
	 * 
	 * Devuelve los viajes realizados el último mes
	 * 
	 * @return
	 */
	List<Trip> findLastMonthTrip();
	
	/**
	 * 
	 * Busca los viajes en los que el usuario fue promotor
	 * 
	 * @param idUser
	 * @return
	 */
	List<Trip> findByPromoterAndDone(Long idUser);
	
	/**
	 * Busca los viajes en los que el usuario ha participado
	 * 
	 * @param id
	 * @return
	 */
	List<Trip> findViajesHaParticipado(Long id);
	
	void actualizar(Trip trip);
	
	/**
	 * Busca los viajes en los que es promotor el id pasado como parámetro
	 * que estén abiertos y que tengan plazas disponibles
	 * 
	 * @param id
	 * @return
	 */
	List<Trip> findByPromoterAndAvailablePax(Long id);
	



}
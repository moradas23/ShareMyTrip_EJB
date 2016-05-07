package com.sdi.business;

import java.util.List;

import com.sdi.model.Seat;

public interface SeatService {
	
	/**
	 * Devuelve los objetos Seat aceptados de un viaje
	 * cuyo id es pasado como parámetro
	 * 
	 * @param long1
	 * @return
	 */
	public List<Seat> findPlazasAceptadas(Long idViaje);

	/**
	 * Devuelve los Seat en los que esta implicado el usuario
	 * 
	 * @param id
	 * @return 
	 */
	public List<Seat> findByUser(Long id);

	public void insert(Long idUsuario, Long idViaje);

	public void delete(Long id, Long id2);

	
	/**
	 * Inserta en TSeat un usuario como excluido en el viaje
	 * 
	 * @param id
	 * @param id2
	 */
	public void insertExcluido(Long idUsuario, Long idViaje);

	/**
	 * Indicar que el usuario no tiene plaza en un viaje
	 * 
	 * @param userId
	 * @param tripId
	 */
	public void insertSinPlazas(Long userId, Long tripId);

	public void actualizarExcluido(Long idUsuario, Long idViaje);

	/**
	 * Excluye al usuario ,cuyo login es pasado como parámetro, de todos los 
	 * viajes aún abiertos en los que participa
	 * 
	 * @param login
	 */
	public void excluirUsuario(String login);

	/**
	 * Busca las plazas donde el usuario fue aceptado
	 * @param id
	 * @return 
	 */
	public List<Seat> findPlazasAceptadasUser(Long id);
}

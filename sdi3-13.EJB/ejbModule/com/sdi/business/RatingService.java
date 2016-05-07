package com.sdi.business;

import java.util.List;

import com.sdi.model.Rating;

public interface RatingService {

	/**
	 * Devuelve los comentarios realizados en los últimos 30 días
	 * 
	 * @return
	 */
	List<Rating> findLastMonth();

	/**
	 * Devuelve los comentarios dirigidos a un usuario
	 * 
	 * @param id
	 * @return
	 */
	List<Rating> findByAboutUser(Long id);

	/**
	 * Eliminar puntuación 
	 * 
	 * @param id
	 */
	void delete(Long id);

	

}

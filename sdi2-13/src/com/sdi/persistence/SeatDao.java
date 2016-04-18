package com.sdi.persistence;

import java.util.List;

import com.sdi.model.Seat;
import com.sdi.persistence.util.GenericDao;



public interface SeatDao extends GenericDao<Seat, Long[]> {

	Seat findByUserAndTrip(Long userId, Long tripId);

	List<Seat> findByUser(Long idUsuario);

	List<Seat> findByTrip(Long idViaje);

	List<Seat> findAllAceptadas(Long idViaje);

	List<Seat> findAcceptedByUser(Long idUsuario);

}

package com.sdi.business.impl.classes;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.exception.AlreadyPersistedException;

public class SeatInsertar {

	public void insert(Long idUsuario, Long idViaje) {
		SeatDao dao = Factories.persistence.createSeatDao();
		Seat seat = new Seat();
		seat.setUserId(idUsuario);
		seat.setTripId(idViaje);
		seat.setStatus(SeatStatus.ACCEPTED);
		try {
			dao.save(seat);
		} catch (AlreadyPersistedException e) {
			System.out.println("Ya se aceptó la solicitud");
		}
	}

	public void insertExcluido(Long idUsuario, Long idViaje) {
		SeatDao dao = Factories.persistence.createSeatDao();
		Seat seat = new Seat();
		seat.setUserId(idUsuario);
		seat.setTripId(idViaje);
		seat.setStatus(SeatStatus.EXCLUDED);
		try {
			dao.save(seat);
		} catch (AlreadyPersistedException e) {
			System.out.println("Ya se canceló la solicitud");
		}
		
	}
	
	public void actualizarExcluido(Long idUsuario, Long idViaje) {
		SeatDao dao = Factories.persistence.createSeatDao();
		Seat seat = new Seat();
		seat.setUserId(idUsuario);
		seat.setTripId(idViaje);
		seat.setStatus(SeatStatus.EXCLUDED);
		
		dao.update(seat);
		
		
	}

	public void insertarSinPlaza(Long idUsuario, Long idViaje) {
		SeatDao dao = Factories.persistence.createSeatDao();
		Seat seat = new Seat();
		seat.setUserId(idUsuario);
		seat.setTripId(idViaje);
		seat.setStatus(SeatStatus.SIN_PLAZA);
		try {
			dao.save(seat);
		} catch (AlreadyPersistedException e) {
			System.out.println("Ya se inserto la solicitud sin plaza");
		}
		
	}

}

package com.sdi.business.impl.classes;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.persistence.SeatDao;

public class SeatBuscar {

	public List<Seat> getPlazasAceptadas(Long long1) {
		SeatDao dao = Factories.persistence.createSeatDao();
		List<Seat> seats = dao.findAllAceptadas(long1);
		return seats;
		
	}

	public List<Seat> getPlazasAcepByUser(Long id) {
		SeatDao dao = Factories.persistence.createSeatDao();
		List<Seat> seats = dao.findAcceptedByUser(id);
		return seats;
	}

	public List<Seat> getPlazasByUser(Long id) {
		SeatDao dao = Factories.persistence.createSeatDao();
		List<Seat> seats = dao.findByUser(id);
		return seats;
	}

	public List<Seat> findPlazasAceptadasUser(Long id) {
		SeatDao dao = Factories.persistence.createSeatDao();
		List<Seat> seats = dao.findAcceptedByUser(id);
		return seats;
	}

	public List<Seat> findByTrip(Long idViaje) {
		SeatDao dao = Factories.persistence.createSeatDao();
		List<Seat> seats = dao.findByTrip(idViaje);
		return seats;
	}

}

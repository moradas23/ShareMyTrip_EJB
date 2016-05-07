package com.sdi.business.impl.classes;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;
import com.sdi.persistence.UserDao;
import com.sdi.persistence.exception.AlreadyPersistedException;

public class SeatUpdate {

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

	/**
	 * Excluye a un usuario de todos los viajes abiertos en los que 
	 * participa
	 * 
	 * @param login
	 */
	public void excluirUsuarioAll(String login) {
		SeatDao seatDao = Factories.persistence.createSeatDao();
		UserDao userDao = Factories.persistence.createUserDao();
		TripDao tripDao = Factories.persistence.createTripDao();
		
		Long idUsuario = userDao.findByLogin(login).getId();
		List<Seat> asientos = seatDao.findByUser(idUsuario);
		
		for(Seat asiento:asientos){
			Trip viaje = tripDao.findById(asiento.getTripId());
			if(viaje.getStatus().equals(TripStatus.OPEN)){
				asiento.setStatus(SeatStatus.EXCLUDED);
				seatDao.update(asiento);			
			}
		}
		
	}

}

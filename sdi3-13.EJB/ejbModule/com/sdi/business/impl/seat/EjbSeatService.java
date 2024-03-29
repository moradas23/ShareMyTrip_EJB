package com.sdi.business.impl.seat;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.classes.SeatBuscar;
import com.sdi.business.impl.classes.SeatDelete;
import com.sdi.business.impl.classes.SeatInsertar;
import com.sdi.business.impl.classes.SeatUpdate;
import com.sdi.model.Seat;

@Stateless
@WebService(name="SeatService")
public class EjbSeatService implements LocalSeatService,RemoteSeatService {

	@Override
	public List<Seat> findPlazasAceptadas(Long long1) {
		return new SeatBuscar().getPlazasAceptadas(long1);
		
	}

	@Override
	public List<Seat> findByUser(Long id) {
		return new SeatBuscar().getPlazasByUser(id);	
	}

	@Override
	public void insert(Long idUsuario, Long idViaje) {
		new SeatInsertar().insert(idUsuario,idViaje);
		
	}

	@Override
	public void delete(Long idUsuario, Long idViaje) {
		new SeatDelete().delete(idUsuario,idViaje);
		
	}

	@Override
	public void insertExcluido(Long idUsuario, Long idViaje) {
		new SeatInsertar().insertExcluido(idUsuario,idViaje);
		
	}

	@Override
	public void insertSinPlazas(Long userId, Long tripId) {
		new SeatInsertar().insertarSinPlaza(userId,tripId);
	}

	@Override
	public void actualizarExcluido(Long idUsuario, Long idViaje) {
		new SeatInsertar().actualizarExcluido(idUsuario, idViaje);
		
	}

	@Override
	public void excluirUsuario(String login) {
		new SeatUpdate().excluirUsuarioAll(login);
		
	}

	@Override
	public List<Seat> findPlazasAceptadasUser(Long id) {
		return new SeatBuscar().findPlazasAceptadasUser(id);
		
	}

	@Override
	public void insertSeat(Seat seat) {
		 new SeatInsertar().insert(seat);
	}

	@Override
	public List<Seat> findByTrip(Long idViaje) {
		return new SeatBuscar().findByTrip(idViaje);
	}


	
}

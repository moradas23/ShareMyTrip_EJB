package com.sdi.business.impl.seat;

import java.util.List;

import javax.ejb.Stateless;

import com.sdi.business.impl.classes.SeatBuscar;
import com.sdi.business.impl.classes.SeatDelete;
import com.sdi.business.impl.classes.SeatInsertar;
import com.sdi.model.Seat;

@Stateless
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
		new SeatUpdate().excluirUsuario(login);
		
	}


	
}

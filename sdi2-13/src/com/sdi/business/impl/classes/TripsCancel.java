package com.sdi.business.impl.classes;

import java.util.List;
import java.util.Map;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;

public class TripsCancel {

	public void cancelarViajes(Map<Long, Trip> viajesCancelar) {
		for(Long key:viajesCancelar.keySet()){
			Trip viaje = viajesCancelar.get(key);
			
			TripDao dao = Factories.persistence.createTripDao();
			
			viaje.setStatus(TripStatus.CANCELLED);
			dao.update(viaje);
				
			SeatDao daoSeat = Factories.persistence.createSeatDao();
			List<Seat> listaViaje =  daoSeat.findByTrip(viaje.getId());
				for(Seat s:listaViaje){
					s.setStatus(SeatStatus.EXCLUDED);
					daoSeat.update(s);
				}
		}
	}

	

}

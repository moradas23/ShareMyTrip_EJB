package com.sdi.business.impl.classes;

import java.util.Date;
import com.sdi.dto.RegistrarViajeDto;
import com.sdi.infrastructure.Factories;
import com.sdi.model.AddressPoint;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.model.Waypoint;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;
import com.sdi.persistence.exception.AlreadyPersistedException;

public class TripRegistrar {
	
	public boolean registerTrip(RegistrarViajeDto trip) {
		TripDao dao = Factories.persistence.createTripDao();
		
		Trip viaje = new Trip();
		
		Waypoint wSalida = new Waypoint(null,null);
		//Creamos AdreesPoint de la Salida
		if(trip.getLatZipCodeFrom().compareTo("")!=0 && trip.getLongZipCodeFrom().compareTo("")!=0){
			wSalida = new Waypoint(
					Double.parseDouble(trip.getLatZipCodeFrom()),
					Double.parseDouble(trip.getLongZipCodeFrom()));
		}
	
		AddressPoint addresSalida = new AddressPoint(trip.getAdressFrom(),
				trip.getCityFrom(), trip.getProvinceFrom(), trip.getCountryFrom(),
				trip.getPostalCodeFrom(), wSalida);
		
		//Creamos AdreesPoint del Destino
		Waypoint wDestino = new Waypoint(null,null);
		//Creamos AdreesPoint de la Salida
		if(trip.getLatZipCodeTo().compareTo("")!=0 && trip.getLongZipCodeTo().compareTo("")!=0){
			wDestino = new Waypoint(
					Double.parseDouble(trip.getLatZipCodeTo()),
					Double.parseDouble(trip.getLongZipCodeTo()));
		}
		
		AddressPoint addresDestino = new AddressPoint(trip.getAdressTo(),
				trip.getCityTo(), trip.getProvinceTo(), trip.getCountryTo(),
				trip.getPostalCodeTo(), wDestino);

		viaje.setDeparture(addresSalida);
		viaje.setDestination(addresDestino);
		
		viaje.setDepartureDate(trip.getDateFrom());
		viaje.setArrivalDate(trip.getDateTo());
		viaje.setClosingDate(trip.getDateLimit());

		Date fechaActual = new Date();
		if(viaje.getArrivalDate().before(fechaActual)
				||viaje.getDepartureDate().before(fechaActual)
				||viaje.getClosingDate().before(fechaActual)){
			trip.setResult("registerTrip_form_result_errorDate");
			return false;
		}
		
		//Comprobamos fechas
		if(trip.getDateFrom()
				.after(trip.getDateTo())||
				trip.getDateLimit().after(trip.getDateTo())){
			trip.setResult("registerTrip_form_result_errorDate");
			return false;
		}
		try{
		viaje.setEstimatedCost(Double.parseDouble(trip.getPriceTrip()));
		viaje.setComments(trip.getCommentTrip());
		viaje.setMaxPax(Integer.parseInt(trip.getMaxSeats()));
		viaje.setAvailablePax(Integer.parseInt(trip.getAvailableSeats()));
		viaje.setStatus(TripStatus.OPEN);
		}catch(NumberFormatException e){
			trip.setResult("registerTrip_form_result_errorFormat");
			return false;
		}
		
		//Comprobamos numeros negativos o concordancia de plazas
		if(viaje.getEstimatedCost()<=0||viaje.getMaxPax()<=0||
				viaje.getAvailablePax()<=0 
				|| viaje.getMaxPax() < viaje.getAvailablePax()){
			trip.setResult("registerTrip_form_result_errorCost");
			return false;
		}
		
		//Obtenemos el id del promotor 
		viaje.setPromoterId(trip.getIdUsuario());
		
		Trip tripMismaFecha = dao
				.findByPromoterIdAndArrivalDate(trip.getIdUsuario(), 
						viaje.getArrivalDate());
		if(tripMismaFecha!=null){
			trip.setResult("registerTrip_form_result_errorSameDate");
			return false;
		}
		
		//Añadimos el viaje a la BD
		try {
			dao.save(viaje);
		} catch (AlreadyPersistedException e1) {
			e1.printStackTrace();
		}
		
		SeatDao daoSeat = Factories.persistence.createSeatDao();
		Seat asiento = new Seat();
		asiento.setStatus(SeatStatus.ACCEPTED);
		asiento.setUserId(trip.getIdUsuario());

		Trip viaje2 = dao.findByPromoterIdAndArrivalDate(trip.getIdUsuario(), viaje.getArrivalDate());
		asiento.setTripId(viaje2.getId());
		
		try {
			daoSeat.save(asiento);
		} catch (AlreadyPersistedException e) {
			e.printStackTrace();
		}

		return true;
	} 

}

package com.sdi.business.impl.classes;

import java.util.Date;

import com.sdi.dto.ModificarViajeDto;
import com.sdi.infrastructure.Factories;
import com.sdi.model.AddressPoint;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.model.Waypoint;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;


public class TripsModificar {

	public boolean modificarTrip(ModificarViajeDto viaje) {
			
			try {
			
			Trip newTrip = new Trip();

			//Creamos AdreesPoint de la Salida
			Waypoint wSalida = new Waypoint(null,null);
			if(viaje.getLatZipCodeFrom().compareTo("")!=0 && viaje.getLongZipCodeFrom().compareTo("")!=0){
				wSalida = new Waypoint(
						Double.parseDouble(viaje.getLatZipCodeFrom()),
						Double.parseDouble(viaje.getLongZipCodeFrom()));
			}
		
			AddressPoint addresSalida = new AddressPoint(viaje.getAdressFrom(),
					viaje.getCityFrom(), viaje.getProvinceFrom(), viaje.getCountryFrom(),
					viaje.getPostalCodeFrom(), wSalida);
			
			//Creamos AdreesPoint del Destino
			Waypoint wDestino = new Waypoint(null,null);
			if(viaje.getLatZipCodeTo().compareTo("")!=0 && viaje.getLongZipCodeTo().compareTo("")!=0){
				wDestino = new Waypoint(
						Double.parseDouble(viaje.getLatZipCodeTo()),
						Double.parseDouble(viaje.getLongZipCodeTo()));
			}
			
			AddressPoint addresDestino = new AddressPoint(viaje.getAdressTo(),
					viaje.getCityTo(), viaje.getProvinceTo(), viaje.getCountryTo(),
					viaje.getPostalCodeTo(), wDestino);


			newTrip.setDeparture(addresSalida);
			newTrip.setDestination(addresDestino);
			
			//Añadimos las fechas al viaje
			newTrip.setArrivalDate(viaje.getDateTo() );
			newTrip.setDepartureDate(viaje.getDateFrom());
			newTrip.setClosingDate(viaje.getDateLimit());
			
			
			Date fechaActual = new Date();
			if(newTrip.getArrivalDate().before(fechaActual)
					||newTrip.getDepartureDate().before(fechaActual)
					||newTrip.getClosingDate().before(fechaActual)){
				viaje.setResult("registerTrip_form_result_errorDate");
				return false;
			}
			
			//Comprobamos fechas
			if(viaje.getDateFrom()
					.after(viaje.getDateTo())||
					viaje.getDateLimit().after(viaje.getDateTo())){
				viaje.setResult("registerTrip_form_result_errorDate");
				return false;
			}
			
			newTrip.setEstimatedCost(Double.parseDouble(viaje.getPriceTrip()));
			newTrip.setComments(viaje.getCommentTrip());
			
			SeatDao daoSeat = Factories.persistence.createSeatDao();
			int seatsConfirmados = daoSeat.findByTrip(viaje.getIdViaje()).size();
			if(Integer.parseInt(viaje.getMaxSeats())<seatsConfirmados){
				viaje.setResult("registerTrip_form_result_errorMaxSeats");
				return false;
			}
			
			newTrip.setMaxPax(Integer.parseInt(viaje.getMaxSeats()));
			newTrip.setAvailablePax(Integer.parseInt(viaje.getAvailableSeats()));
			newTrip.setStatus(TripStatus.OPEN);
			
			//Comprobamos numeros negativos o concordancia de plazas
			if(Double.parseDouble(viaje.getPriceTrip())<=0||Integer.parseInt(viaje.getMaxSeats())<=0||
					Integer.parseInt(viaje.getAvailableSeats())<=0 
					|| Integer.parseInt(viaje.getMaxSeats())<Integer.parseInt(viaje.getAvailableSeats())){
				viaje.setResult("registerTrip_form_result_errorCost");
				return false;
			}

			newTrip.setPromoterId(viaje.getPromoter());
			newTrip.setId(viaje.getIdViaje());
			
			TripDao tdao = Factories.persistence.createTripDao();
			Trip tripMismaFecha = tdao
					.findByPromoterIdAndArrivalDate(newTrip.getPromoterId(), 
							newTrip.getArrivalDate());
			if(tripMismaFecha!=null && tripMismaFecha.getId().longValue()!=newTrip.getId().longValue()){
				tripMismaFecha=null;
			}
			
			if(tripMismaFecha==null){
				viaje.setResult("registerTrip_form_result_errorSameDate");
				return false;
			}
			
			//Añadimos el viaje a la BD
			
			tdao.update(newTrip);

		} catch (NumberFormatException e) {
			viaje.setResult("registerTrip_form_result_errorFormat");
			return false;
		}

		return true;
	
	}

	public void disminuirPlazas(Long idViaje) {
		TripDao dao = Factories.persistence.createTripDao();
		dao.disminuirPlazas(idViaje);	
	}
	
	public void aumentarPlazas(Long idViaje) {
		TripDao dao = Factories.persistence.createTripDao();
		dao.aumentarPlazas(idViaje);	
	}
	
	public void cerrarViaje(Trip viaje) {
		TripDao dao = Factories.persistence.createTripDao();
		viaje.setStatus(TripStatus.CLOSED);
		dao.update(viaje);		
	}

	public void update(Trip trip) {
		TripDao dao = Factories.persistence.createTripDao();
		dao.update(trip);
	}
}

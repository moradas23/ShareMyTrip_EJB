package com.sdi.model;


public class TripImplicacion extends Trip {
	
	private ImplicacionStatus implicacion;

	public TripImplicacion(Trip viaje) {
		setArrivalDate(viaje.getArrivalDate());
		setAvailablePax(viaje.getAvailablePax());
		setClosingDate(viaje.getClosingDate());
		setComments(viaje.getComments());
		setDeparture(viaje.getDeparture());
		setDepartureDate(viaje.getDepartureDate());
		setDestination(viaje.getDestination());
		setEstimatedCost(viaje.getEstimatedCost());
		setId(viaje.getId());
		setMaxPax(viaje.getMaxPax());
		setPromoterId(viaje.getPromoterId());
		setStatus(viaje.getStatus());
	}

	public ImplicacionStatus getImplicacion() {
		return implicacion;
	}

	public void setImplicacion(ImplicacionStatus implicacion) {
		this.implicacion = implicacion;
	}


}

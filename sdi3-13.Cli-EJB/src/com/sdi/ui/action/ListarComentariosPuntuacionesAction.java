package com.sdi.ui.action;

import java.util.List;

import com.sdi.business.TripsService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.Trip;

import alb.util.menu.Action;

public class ListarComentariosPuntuacionesAction implements Action{

	@Override
	public void execute() throws Exception {
		RatingService tService = new RemoteEjbServicesLocator().getTripService();
		List<Trip> viajes = tService.findLastMonth();
		
		
		for(Trip viaje:viajes){
			System.out.println("Destino: "+viaje.getDestination().getCity());
		}
	}

}

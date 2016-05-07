package com.sdi.ui.action;

import java.util.List;

import com.sdi.business.RatingService;
import com.sdi.business.TripsService;
import com.sdi.business.UsersService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.Rating;
import com.sdi.model.Trip;
import com.sdi.model.User;

import alb.util.menu.Action;

public class ListarComentariosPuntuacionesAction implements Action{

	@Override
	public void execute() throws Exception {
		RatingService rService = new RemoteEjbServicesLocator().getRatingService();
		TripsService tService = new RemoteEjbServicesLocator().getTripService();
		UsersService uService = new RemoteEjbServicesLocator().getUserService();
		
		List<Rating> comentarios = rService.findLastMonth();
		if(comentarios!=null)
			for(Rating comentario:comentarios){
				Trip viaje = tService.findById(comentario.getSeatAboutTripId());
				User usuario1 = uService.findById(comentario.getSeatFromUserId());
				User usuario2 = uService.findById(comentario.getSeatAboutUserId());
				
				System.out.println("---Destino: "+viaje.getDestination().getCity()+"------------------------");
				System.out.println();
				System.out.println("Usuario hizo comentario: ");
				System.out.println("\t ID: "+usuario1.getId());
				System.out.println("\t Login: "+usuario1.getLogin());
				System.out.println("\t Nombre: "+usuario1.getName());
				
				System.out.println("Usuario recibe comentario: ");
				System.out.println("\t ID: "+usuario2.getId());
				System.out.println("\t Login: "+usuario2.getLogin());
				System.out.println("\t Nombre: "+usuario2.getName());
				
				System.out.println("Valoración: "+comentario.getValue());
				System.out.println("Comentario: "+comentario.getComment());
				
				System.out.println();
		}
	}

}

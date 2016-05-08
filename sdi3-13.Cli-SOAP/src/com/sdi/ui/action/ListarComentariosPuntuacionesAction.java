package com.sdi.ui.action;

import java.util.List;

import com.sdi.ws.rating.EjbRatingServiceService;
import com.sdi.ws.rating.Rating;
import com.sdi.ws.rating.RatingService;
import com.sdi.ws.trip.EjbTripServiceService;
import com.sdi.ws.trip.Trip;
import com.sdi.ws.trip.TripService;
import com.sdi.ws.user.EjbUserServiceService;
import com.sdi.ws.user.User;
import com.sdi.ws.user.UserService;

import alb.util.menu.Action;

public class ListarComentariosPuntuacionesAction implements Action{

	@Override
	public void execute() throws Exception {
		RatingService rService = new EjbRatingServiceService().getRatingServicePort();
		TripService tService = new EjbTripServiceService().getTripServicePort();
		UserService uService = new EjbUserServiceService().getUserServicePort();
		
		List<Rating> comentarios = rService.findLastMonth();
		if(comentarios!=null)
			for(Rating comentario:comentarios){
				Long id = comentario.getSeatAboutTripId();
				Trip viaje = tService.findByIdTrip(id);
				
				User usuario1 = uService.findById(comentario.getSeatFromUserId());
				User usuario2 = uService.findById(comentario.getSeatAboutUserId());
				
				System.out.println("---Destino: "+viaje.getCityDestination()+"------------------------");
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

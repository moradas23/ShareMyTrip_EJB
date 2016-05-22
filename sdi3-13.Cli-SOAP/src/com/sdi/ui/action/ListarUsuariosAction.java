package com.sdi.ui.action;

import java.util.List;








import com.sdi.ws.trip.EjbTripServiceService;
import com.sdi.ws.trip.Trip;
import com.sdi.ws.trip.TripService;
import com.sdi.ws.user.EjbUserServiceService;
import com.sdi.ws.user.User;
import com.sdi.ws.user.UserService;

import alb.util.menu.Action;

public class ListarUsuariosAction implements Action{

	@Override
	public void execute() throws Exception {
		UserService uService = new EjbUserServiceService().getUserServicePort();
		List<User> usuarios = uService.getUsers();
		
		TripService tService = new EjbTripServiceService().getTripServicePort();
		
		for(User usuario:usuarios){
			System.out.println("---- Id: "+usuario.getId()+" -----------------------");
			System.out.println("Login: "+usuario.getLogin());
			System.out.println("Contraseña(Encriptada): "+usuario.getPassword());
			System.out.println("Nombre: "+usuario.getName());
			System.out.println("Apellidos: "+usuario.getSurname());
			System.out.println("Email: "+usuario.getEmail());
			System.out.println("Estado de la cuenta: "+usuario.getStatus());
			
			List<Trip> viajesPromotor = tService.findByPromoterAndDone(usuario.getId());
			System.out.println("Viajes como promotor: "+viajesPromotor.size());
			
			List<Trip> viajesParticipo = tService.findViajesHaParticipado(usuario.getId());
			System.out.println("Viajes ha participado: "+viajesParticipo.size());
		}
	}
	
}

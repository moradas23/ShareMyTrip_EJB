package com.sdi.ui.action;

import java.util.List;

import com.sdi.business.SeatService;
import com.sdi.business.TripsService;
import com.sdi.business.UsersService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.User;

import alb.util.menu.Action;

public class ListarUsuariosAction implements Action{

	@Override
	public void execute() throws Exception {
		UsersService uService = new RemoteEjbServicesLocator().getUserService();
		List<User> usuarios = uService.getUsers();
		
		TripsService tService = new RemoteEjbServicesLocator().getTripService();
		
		
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

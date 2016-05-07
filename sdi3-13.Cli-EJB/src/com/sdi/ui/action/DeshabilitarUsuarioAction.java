package com.sdi.ui.action;

import com.sdi.business.SeatService;
import com.sdi.business.TripsService;
import com.sdi.business.UsersService;
import com.sdi.business.impl.RemoteEjbServicesLocator;

import alb.util.console.Console;
import alb.util.menu.Action;

public class DeshabilitarUsuarioAction implements Action{

	@Override
	public void execute() throws Exception {
		
		String login = Console.readString("Login del usuario");
		UsersService service = new RemoteEjbServicesLocator().getUserService();
		service.unsubscribe(login);
		
		SeatService seatService = new RemoteEjbServicesLocator().getSeatService();
		seatService.excluirUsuario(login);
		
		
	}

}

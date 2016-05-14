package com.sdi.ui.action;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.business.SeatService;
import com.sdi.business.UserService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.User;
import com.sdi.model.UserStatus;

public class DeshabilitarUsuarioAction implements Action {

	@Override
	public void execute() throws Exception {

		String login = Console.readString("Login del usuario");
		UserService service = new RemoteEjbServicesLocator().getUserService();
		User usuario = service.findByLogin(login);

		if (usuario != null) {
			if (usuario.getStatus().equals(UserStatus.ACTIVE)) {
				service.unsubscribe(login);
				SeatService seatService = new RemoteEjbServicesLocator()
						.getSeatService();
				seatService.excluirUsuario(login);
				System.out.println("Usuario deshabilitado");

			} else {
				System.out.println("El usuario con login '" + login
						+ "' ya está deshabilitado");
			}
		} else {
			System.out.println("No existe ningún usuario con ese login");
		}

	}

}

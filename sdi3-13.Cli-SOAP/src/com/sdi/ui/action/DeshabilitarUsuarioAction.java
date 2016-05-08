package com.sdi.ui.action;



import com.sdi.ws.seat.EjbSeatServiceService;
import com.sdi.ws.seat.SeatService;
import com.sdi.ws.user.EjbUserServiceService;
import com.sdi.ws.user.User;
import com.sdi.ws.user.UserService;
import com.sdi.ws.user.UserStatus;

import alb.util.console.Console;
import alb.util.menu.Action;


public class DeshabilitarUsuarioAction implements Action {

	@Override
	public void execute() throws Exception {

		String login = Console.readString("Login del usuario");
		UserService service = new EjbUserServiceService().getUserServicePort();
		SeatService seatService = new EjbSeatServiceService().getSeatServicePort();
		
		User usuario = service.finByLogin(login);

		if (usuario != null) {
			if (usuario.getStatus().equals(UserStatus.ACTIVE)) {
				service.unsubscribe(login);
				
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

package com.sdi.ui.action;

import java.util.List;

import com.sdi.business.UsersService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.User;

import alb.util.menu.Action;

public class ListarUsuariosAction implements Action{

	@Override
	public void execute() throws Exception {
		UsersService service = new RemoteEjbServicesLocator().getUserService();
		List<User> usuarios = service.getUsers();
		
		for(User usuario:usuarios){
			System.out.println("---- Id: "+usuario.getId()+" -----------------------");
			System.out.println("Login: "+usuario.getLogin());
			System.out.println("Contraseña(Encriptada): "+usuario.getPassword());
			System.out.println("Nombre: "+usuario.getName());
			System.out.println("Apellidos: "+usuario.getSurname());
			System.out.println("Email: "+usuario.getEmail());
			System.out.println("Estado de la cuenta: "+usuario.getStatus());

		}
	}
	
}

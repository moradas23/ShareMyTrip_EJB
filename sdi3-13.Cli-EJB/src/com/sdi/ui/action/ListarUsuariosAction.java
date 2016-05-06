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
			System.out.println(usuario);
		}
	}
	
}

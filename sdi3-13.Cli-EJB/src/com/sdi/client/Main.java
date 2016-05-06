package com.sdi.client;

import java.util.List;

import com.sdi.business.UsersService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.User;


public class Main {
	
	public static void main(String[] args) throws Exception {
		Main.run();
	}

	private static void run() throws Exception {
	
		
		UsersService service = new RemoteEjbServicesLocator().getUserService();
		List<User> usuarios = service.getUsers();
		
		for(User usuario:usuarios){
			System.out.println(usuario);
		}
		

	}

}

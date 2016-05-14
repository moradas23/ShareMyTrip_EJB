package com.sdi.business.impl.classes;

import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.model.UserStatus;
import com.sdi.persistence.UserDao;
import com.sdi.persistence.exception.AlreadyPersistedException;

public class UsersManagement {

	public void save(User user){
		UserDao dao = Factories.persistence.createUserDao();
		try {
			dao.save(user);
		}
		catch (AlreadyPersistedException ex) {
			System.out.println("Usuario ya existe " + user);
		}
	}
	
	public void unsubscribe(String login){
		UserDao dao = Factories.persistence.createUserDao();
		User usuario = dao.findByLogin(login);
		if(usuario.getStatus().equals(UserStatus.ACTIVE)){
			dao.unsubscribe(login);
			System.out.println("Se ha deshabilitado al usuario con login '"+login+"'");
		}
		else{
			System.out.println("El usuario con login '"+login+"' ya está deshabilitado"); //PROBLEMA - Muestra mensajes en consola "Servidor"
		}
		
	}

}

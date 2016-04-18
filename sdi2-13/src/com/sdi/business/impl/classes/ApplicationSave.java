package com.sdi.business.impl.classes;

import com.sdi.infrastructure.Factories;
import com.sdi.persistence.ApplicationDao;


public class ApplicationSave {

	public void save(String loginUsuario, Long idViaje) {
		ApplicationDao dao = Factories.persistence.createApplicationDao();		
		dao.save(loginUsuario,idViaje);
	}

}

package com.sdi.business.impl.classes;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.persistence.ApplicationDao;

public class ApplicactionBuscar {

	public List<Application> find(String login) {
		ApplicationDao dao = Factories.persistence.createApplicationDao();
		return dao.findByUserLogin(login);	
	}

	public List<Application> findByViaje(Long id) {
		ApplicationDao dao = Factories.persistence.createApplicationDao();
		return dao.findByTripId(id);
	}	
}

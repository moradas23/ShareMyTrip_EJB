package com.sdi.business.impl;

import java.util.List;

import com.sdi.business.ApplicationService;
import com.sdi.business.impl.classes.ApplicactionBuscar;
import com.sdi.business.impl.classes.ApplicationDelete;
import com.sdi.business.impl.classes.ApplicationSave;
import com.sdi.model.Application;


public class SimpleApplicationService implements ApplicationService {

	@Override
	public void save(String loginUsuario, Long idViaje) {
		new ApplicationSave().save(loginUsuario,idViaje);
	}

	@Override
	public List<Application> getSolicitudes(String login) {
		return new ApplicactionBuscar().find(login);
		
	}

	@Override
	public List<Application> getSolicitudesViaje(Long id) {
		return new ApplicactionBuscar().findByViaje(id);
	}

	@Override
	public void delete(Long idUsuario,Long idViaje) {
		new ApplicationDelete().delete(idUsuario,idViaje);
		
	}

}

package com.sdi.business.impl.application;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.classes.ApplicactionBuscar;
import com.sdi.business.impl.classes.ApplicationDelete;
import com.sdi.business.impl.classes.ApplicationSave;
import com.sdi.model.Application;

@Stateless
@WebService(name="ApplicationService")
public class EjbApplicationService implements  LocalApplicationService,RemoteApplicationService {

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

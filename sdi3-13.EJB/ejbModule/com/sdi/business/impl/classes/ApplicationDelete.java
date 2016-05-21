package com.sdi.business.impl.classes;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.persistence.ApplicationDao;

public class ApplicationDelete {

	public void delete(Long idUsuario, Long idViaje) {
		ApplicationDao dao = Factories.persistence.createApplicationDao();
		Long[] ids={idUsuario,idViaje};
		dao.delete(ids);
		
	}

	public void delete(Application app) {
		ApplicationDao dao = Factories.persistence.createApplicationDao();
		Long[] ids={app.getUserId(),app.getTripId()};
		dao.delete(ids);
	}

}

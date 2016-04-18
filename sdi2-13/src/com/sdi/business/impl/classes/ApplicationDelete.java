package com.sdi.business.impl.classes;

import com.sdi.infrastructure.Factories;
import com.sdi.persistence.ApplicationDao;

public class ApplicationDelete {

	public void delete(Long idUsuario, Long idViaje) {
		ApplicationDao dao = Factories.persistence.createApplicationDao();
		Long[] ids={idUsuario,idViaje};
		dao.delete(ids);
		
	}

}

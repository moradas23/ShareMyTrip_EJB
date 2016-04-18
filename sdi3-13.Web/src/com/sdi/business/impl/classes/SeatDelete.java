package com.sdi.business.impl.classes;

import com.sdi.infrastructure.Factories;
import com.sdi.persistence.SeatDao;

public class SeatDelete {

	public void delete(Long idUsuario, Long idViaje) {
		SeatDao dao = Factories.persistence.createSeatDao();
		Long[] ids = {idUsuario,idViaje};
		dao.delete(ids);
	}

}

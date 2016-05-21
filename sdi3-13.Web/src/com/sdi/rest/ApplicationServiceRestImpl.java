package com.sdi.rest;

import java.util.List;
import com.sdi.business.ApplicationService;
import com.sdi.infrastructure.Factories;

public class ApplicationServiceRestImpl implements ApplicationServiceRest {

	ApplicationService service = Factories.services.getApplicationService();
	
	@Override
	public List<com.sdi.model.Application> getSolicitudes(Long idViaje) {
		return service.getSolicitudesViaje(idViaje);
	}

	@Override
	public void borrarSolicitud(Long idSolicitante, Long idViaje) {
		service.delete(idSolicitante, idViaje);
	}

}

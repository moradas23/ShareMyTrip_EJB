package com.sdi.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ApplicationServiceRs")
public interface ApplicationServiceRest {

	@GET
	@Path("{idViaje}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public List<com.sdi.model.Application> getSolicitudes(@PathParam("idViaje") Long idViaje);
	
}

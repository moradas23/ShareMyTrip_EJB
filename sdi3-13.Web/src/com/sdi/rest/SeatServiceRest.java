package com.sdi.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/SeatServiceRs/")
public interface SeatServiceRest {

	@POST
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	boolean confirmarPasajero(@PathParam("id") Long id);
}

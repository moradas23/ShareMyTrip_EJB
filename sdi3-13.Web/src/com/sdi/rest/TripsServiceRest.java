package com.sdi.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.model.Trip;

@Path("/TripsServiceRs")
public interface TripsServiceRest {

	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	List<Trip> getPromotedTrips(@PathParam("id") Long id);
	
	@GET
	@Path("/viaje/{idViaje}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	Trip getTrip(@PathParam("idViaje") Long idViaje);
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	void actualizarViaje(Trip trip);
	
}

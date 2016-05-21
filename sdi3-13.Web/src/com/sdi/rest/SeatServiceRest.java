package com.sdi.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.sdi.model.Seat;

@Path("/SeatServiceRs")
public interface SeatServiceRest {

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }) 
	void insertSeat(Seat seat);
}

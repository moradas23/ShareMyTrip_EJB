package com.sdi.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.model.UserLogin;

@Path("/loginServiceRS")
public interface LoginServiceRest {

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	UserLogin verify(String login, String password);
	
}

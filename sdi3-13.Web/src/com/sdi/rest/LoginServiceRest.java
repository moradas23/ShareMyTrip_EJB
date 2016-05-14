package com.sdi.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.model.UserLogin;





@Path("/loginServiceRS")
public interface LoginServiceRest {

	@GET
	@Path("/{login}/{pass}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	UserLogin verify(@PathParam("login") String login,@PathParam("pass") String password);
	
}

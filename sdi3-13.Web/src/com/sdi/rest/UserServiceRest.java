package com.sdi.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.model.User;

@Path("/UserServiceRs")
public interface UserServiceRest {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	User getUserById(String id);
	
	

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	List<User> getUsers();


	
}

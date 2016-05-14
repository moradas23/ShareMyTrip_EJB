package com.sdi.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import alb.util.console.Console;

import com.sdi.client.trip.Trip;
import com.sdi.client.user.User;


public class Main {
	
	private static final String REST_TRIP_SERVICE_URL = "http://localhost:8280"
			+ "/sdi3-13.Web/rest/TripsServiceRs";
	
	private static final String REST_USER_SERVICE_URL = "http://localhost:8280"
			+ "/sdi3-13.Web/rest/UserServiceRs";

	
	private String login;
	private String password;

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		   login = Console.readString("Login");
		   password = Console.readString("Password");
		  
		   User usuario = getUserByLogin(); 
				  
		  	List<Trip> viajes = getTripsPromoted(usuario.getId());
		  	
		  	for(Trip viaje:viajes){
		  		System.out.println(viaje);
		  	}
		  
		  
	}
	
	
	private List<Trip> getTripsPromoted(Long id) { 
		
		GenericType<List<Trip>> listm = new GenericType<List<Trip>>() {};
		
		List<Trip> lista =  ClientBuilder.newClient()
			.register(new Authenticator(login, password)) 
			.target( REST_TRIP_SERVICE_URL )
			.path(id.toString())
			.request()
			.get()
			.readEntity(listm);
			
	return lista;

}
	
	
	private User getUserByLogin() {
		return (User) ClientBuilder.newClient()
		.register(new Authenticator(login, password))
		.target( REST_USER_SERVICE_URL )
		.path(login)
		.request()
		.accept( MediaType.APPLICATION_XML )
		.get()
		.readEntity(User.class);
		}

	

}
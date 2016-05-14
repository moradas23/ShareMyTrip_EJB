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


	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		  String user = Console.readString("Login");
		  String password = Console.readString("password");
		  
		  Authenticator auth = new Authenticator(user, password);
		  List<Trip> viajes = getTripsPromoted(auth);
		  
		  for(Trip viaje:viajes){
			  System.out.println(viaje);
		  }
	}
	
	
	private List<Trip> getTripsPromoted(Authenticator auth) { 
		GenericType<List<Trip>> listm = new GenericType<List<Trip>>() {};
		
		List<Trip> lista = (List<Trip>) ((Response) ClientBuilder.newClient()
			.register( auth ) 
			.target( REST_TRIP_SERVICE_URL )
			.request()
			.get(listm))
			.getEntity();
			
	return lista;

}
	
	
	private User getUserById(User usuario) {
		return (User) ClientBuilder.newClient()
		.register( new Authenticator("sdi", "password") )
		.target( REST_USER_SERVICE_URL )
		.path( usuario.getId().toString() )
		.request()
		.accept( MediaType.APPLICATION_XML )
		.get()
		.getEntity();
		}

	

}
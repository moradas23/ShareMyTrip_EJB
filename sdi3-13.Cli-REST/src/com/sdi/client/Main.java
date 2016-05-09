package com.sdi.client;

import java.util.List;



import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

import alb.util.console.Console;

public class Main {
	
	private static final String REST_TRIP_SERVICE_URL = "http://localhost:8280"
			+ "/sdi3-13.Web/rest/TripsServiceRs";

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
		
		List<Trip> lista = ClientBuilder.newClient()
			.register( auth ) 
			.target( REST_TRIP_SERVICE_URL )
			.request()
			.get(listm);
			
	return lista;

}
	

}
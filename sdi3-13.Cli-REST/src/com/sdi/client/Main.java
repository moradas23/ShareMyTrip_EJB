package com.sdi.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import alb.util.console.Console;

import com.sdi.client.application.Application;
import com.sdi.client.trip.Trip;
import com.sdi.client.user.User;

public class Main {

	private static final String REST_TRIP_SERVICE_URL = "http://localhost:8280"
			+ "/sdi3-13.Web/rest/TripsServiceRs";

	private static final String REST_USER_SERVICE_URL = "http://localhost:8280"
			+ "/sdi3-13.Web/rest/UserServiceRs";

	private static final String REST_APPLICATION_SERVICE_URL = "http://localhost:8280"
			+ "/sdi3-13.Web/rest/ApplicationServiceRs";

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

		if (viajes.size() > 0){
			System.out.println("\nViajes abiertos como promotor:");
			mostrarViajes(viajes);
		}
		else {
			System.out.println("No tiene ningún viaje abierto como promotor");
			return;
		}

		int idViaje = Console.readInt("ID del viaje");

		List<Application> solicitantes = getSolicitantesViaje(idViaje);

		if (solicitantes.size() > 0) {
			System.out.println("\nSolicitantes del viaje:");
			mostrarSolicitantes(solicitantes);
			
		} else {
			System.out.println("Este viaje no tiene ninguna solicitud de participación");
			return;
		}
		
		Long idConfirmado = Console.readLong("Inserte ID del usuario a confirmar");
		

	}

	private void mostrarSolicitantes(List<Application> solicitantes) {
		for (Application app : solicitantes) {
			mostrarUsuario(getUserById(app.getUserId()));

		}	
	}

	private void mostrarUsuario(User user) {
		System.out.println("\n---ID del Usuario: " + user.getId()+ "-------------");
		System.out.println("Login: " + user.getLogin());
		System.out.println("Nombre: " + user.getName());
		System.out.println("Apellidos: " + user.getSurname());
		System.out.println("Email: " + user.getEmail() + "\n");

	}

	private void mostrarViajes(List<Trip> viajes) {
		for (Trip viaje : viajes) {
			System.out.println("\n---ID del Viaje: " + viaje.getId()+ "-------------");
			System.out.println("Ciudad Salida: "+ viaje.getDeparture().getCity());
			System.out.println("Ciudad Destino: "+ viaje.getDestination().getCity());
			System.out.println("Fecha Salida: " + viaje.getDepartureDate());
			System.out.println("Fecha llegada: " + viaje.getArrivalDate()+ "\n");
		}
	}

	private List<Trip> getTripsPromoted(Long id) {

		GenericType<List<Trip>> listm = new GenericType<List<Trip>>() {
		};

		List<Trip> lista = ClientBuilder.newClient()
				.register(new Authenticator(login, password))
				.target(REST_TRIP_SERVICE_URL)
				.path(id.toString())
				.request()
				.get()
				.readEntity(listm);

		return lista;

	}

	private User getUserByLogin() {
		return (User) ClientBuilder.newClient()
				.register(new Authenticator(login, password))
				.target(REST_USER_SERVICE_URL)
				.path("login/"+login)
				.request()
				.accept(MediaType.APPLICATION_XML)
				.get()
				.readEntity(User.class);
	}

	private User getUserById(Long id) {
		return (User) ClientBuilder.newClient()
				.register(new Authenticator(login, password))
				.target(REST_USER_SERVICE_URL)
				.path("id/"+id.toString())
				.request()
				.accept(MediaType.APPLICATION_XML)
				.get()
				.readEntity(User.class);
	}

	private List<Application> getSolicitantesViaje(int idViaje) {

		GenericType<List<Application>> listm = new GenericType<List<Application>>() {
		};

		List<Application> lista = ClientBuilder.newClient()
				.register(new Authenticator(login, password))
				.target(REST_APPLICATION_SERVICE_URL)
				.path(String.valueOf(idViaje))
				.request()
				.get()
				.readEntity(listm);

		return lista;

	}

}
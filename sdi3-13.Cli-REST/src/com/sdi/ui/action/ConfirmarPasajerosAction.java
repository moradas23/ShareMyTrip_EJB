package com.sdi.ui.action;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.sdi.client.Authenticator;
import com.sdi.client.application.Application;
import com.sdi.client.seat.Seat;
import com.sdi.client.seat.SeatStatus;
import com.sdi.client.trip.Trip;
import com.sdi.client.user.User;

import alb.util.console.Console;
import alb.util.menu.Action;

public class ConfirmarPasajerosAction implements Action {

	private static final String REST_TRIP_SERVICE_URL = "http://localhost:8280"
			+ "/sdi3-13.Web/rest/TripsServiceRs";

	private static final String REST_USER_SERVICE_URL = "http://localhost:8280"
			+ "/sdi3-13.Web/rest/UserServiceRs";

	private static final String REST_APPLICATION_SERVICE_URL = "http://localhost:8280"
			+ "/sdi3-13.Web/rest/ApplicationServiceRs";
	
	private static final String REST_SEAT_SERVICE_URL = "http://localhost:8280"
			+ "/sdi3-13.Web/rest/SeatServiceRs";

	private String login;
	private String password;

	@Override
	public void execute() throws Exception {
		login = Console.readString("Login");
		password = Console.readString("Password");

		User usuario = getUserByLogin();

		List<Trip> viajes = getTripsPromoted(usuario.getId());

		if (viajes!= null && viajes.size() > 0) {
			System.out.println("\nViajes abiertos como promotor:");
			mostrarViajes(viajes);
		} else {
			System.out.println("No tiene ningún viaje abierto como promotor");
			return;
		}

		int idViaje = Console.readInt("ID del viaje");

		List<Application> solicitantes = getSolicitantesViaje(idViaje);

		if (solicitantes.size() > 0) {
			System.out.println("\nSolicitantes del viaje:");
			mostrarSolicitantes(solicitantes);

		} else {
			System.out
					.println("Este viaje no tiene ninguna solicitud de participación");
			return;
		}

		Long idConfirmado = Console.readLong("Inserte ID del usuario a confirmar");

		
		//Borrar solicitud
		borrarSolicitud(idConfirmado,(long) idViaje);
		
		//Insertamos el asiento
		Seat seat = new Seat();
		seat.setUserId(idConfirmado);
		seat.setTripId((long) idViaje);
		seat.setStatus(SeatStatus.ACCEPTED);

		insertarAsiento(seat);
		
		//Actualizamos viajes
		Trip viaje = obtenerViaje((long) idViaje);
		viaje.setAvailablePax(viaje.getAvailablePax()-1);
		disminuirPlaza(viaje);
		
		System.out.println("");
		System.out.println("El pasajero  ha sido confirmado satisfactoriamente");
	}


	private void mostrarSolicitantes(List<Application> solicitantes) {
		for (Application app : solicitantes) {
			mostrarUsuario(getUserById(app.getUserId()));

		}
	}

	private void mostrarUsuario(User user) {
		System.out.println("\n---ID del Usuario: " + user.getId()
				+ "-------------");
		System.out.println("Login: " + user.getLogin());
		System.out.println("Nombre: " + user.getName());
		System.out.println("Apellidos: " + user.getSurname());
		System.out.println("Email: " + user.getEmail() + "\n");

	}

	private void mostrarViajes(List<Trip> viajes) {
		for (Trip viaje : viajes) {
			System.out.println("\n---ID del Viaje: " + viaje.getId()
					+ "-------------");
			System.out.println("Ciudad Salida: "
					+ viaje.getDeparture().getCity());
			System.out.println("Ciudad Destino: "
					+ viaje.getDestination().getCity());
			System.out.println("Fecha Salida: " + viaje.getDepartureDate());
			System.out.println("Fecha llegada: " + viaje.getArrivalDate()
					+ "\n");
		}
	}

	private List<Trip> getTripsPromoted(Long id) {

		GenericType<List<Trip>> listm = new GenericType<List<Trip>>() {
		};

		List<Trip> lista = ClientBuilder.newClient()
				.register(new Authenticator(login, password))
				.target(REST_TRIP_SERVICE_URL).path(id.toString()).request()
				.get().readEntity(listm);

		return lista;

	}

	private User getUserByLogin() {
		return (User) ClientBuilder.newClient()
				.register(new Authenticator(login, password))
				.target(REST_USER_SERVICE_URL)
				.path("login/" + login)
				.request()
				.accept(MediaType.APPLICATION_XML)
				.get()
				.readEntity(User.class);
	}

	private User getUserById(Long id) {
		return (User) ClientBuilder.newClient()
				.register(new Authenticator(login, password))
				.target(REST_USER_SERVICE_URL)
				.path("id/" + id.toString())
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
	
	private void insertarAsiento(Seat seat) {
		 ClientBuilder.newClient()
				.register(new Authenticator(login, password))
				.target(REST_SEAT_SERVICE_URL)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.put(Entity.json(seat));
	}
	
	private void borrarSolicitud(Long idSolicitante,Long idViaje) {
		 ClientBuilder.newClient()
			.register(new Authenticator(login, password))
			.target(REST_APPLICATION_SERVICE_URL)
			.path("/" + idSolicitante.toString()+"/"+idViaje.toString())
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.delete();
	}
	
	private void disminuirPlaza(Trip viaje) {
		 ClientBuilder.newClient()
			.register(new Authenticator(login, password))
			.target(REST_TRIP_SERVICE_URL)
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.post(Entity.json(viaje));
	}
	

	private Trip obtenerViaje(Long idViaje) {
		return (Trip) ClientBuilder.newClient()
				.register(new Authenticator(login, password))
				.target(REST_TRIP_SERVICE_URL)
				.path("/viaje/" + idViaje.toString())
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get()
				.readEntity(Trip.class);
	}

}

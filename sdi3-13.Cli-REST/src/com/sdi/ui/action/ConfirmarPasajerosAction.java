package com.sdi.ui.action;

import java.util.List;

import javax.ws.rs.ProcessingException;
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
		
		//Obtenemos el login y contraseña
		obtenerCredenciales();

		//Obtenemos usuario
		User usuario = getUserByLogin();
		
		//Si el usuario es null las credenciales son erroneas
		if(usuario==null){
			System.out.println("Usuario y/o contraseña erroneos o cuenta deshabilitada");
			return;
		}
		
		List<Trip> viajes = getTripsPromoted(usuario.getId());

		if (viajes != null && viajes.size() > 0) {
			System.out.println("\nViajes abiertos como promotor:");
			mostrarViajes(viajes);
			
		} else {
			System.out.println("No tiene ningún viaje abierto como promotor"
					+ " con plazas disponibles");
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
		
		for(Application app:solicitantes){
			if(app.getUserId().equals(idConfirmado)){
				aceptarSolicitud(idViaje, idConfirmado);
				return;
			}
		}

		System.out.println("ID no valido");
	}
	
	


	/**
	 * Borra la solicitud de un pasajero, lo inserta en una plaza y disminuye
	 * una plaza disponible en el viaje
	 * 
	 * @param idViaje
	 * @param idConfirmado
	 */
	private void aceptarSolicitud(int idViaje, Long idConfirmado) {
		// Borrar solicitud
		borrarSolicitud(idConfirmado, (long) idViaje);

		// Insertamos el asiento
		Seat seat = new Seat();
		seat.setUserId(idConfirmado);
		seat.setTripId((long) idViaje);
		seat.setStatus(SeatStatus.ACCEPTED);

		insertarAsiento(seat);

		// Actualizamos viajes
		Trip viaje = obtenerViaje((long) idViaje);
		viaje.setAvailablePax(viaje.getAvailablePax() - 1);
		disminuirPlaza(viaje);
		
		System.out.println("El pasajero ha sido confirmado satisfactoriamente");
	}
	
	/**
	 * Pide por pantalla las credenciales del usuario
	 * 
	 */
	private void obtenerCredenciales() {
		login = Console.readString("Login");
		password = Console.readString("Password");
	}

	/**
	 * Muestra los solicitantes por pantalla
	 * 
	 * @param solicitantes
	 */
	private void mostrarSolicitantes(List<Application> solicitantes) {
		for (Application app : solicitantes) {
			mostrarUsuario(getUserById(app.getUserId()));

		}
	}

	/**
	 * Muestra el usuario pasado como parámetro por pantalla
	 * 
	 * @param user
	 */
	private void mostrarUsuario(User user) {
		System.out.println("\n---ID del Usuario: " + user.getId()
				+ "-------------");
		System.out.println("Login: " + user.getLogin());
		System.out.println("Nombre: " + user.getName());
		System.out.println("Apellidos: " + user.getSurname());
		System.out.println("Email: " + user.getEmail() + "\n");

	}

	
	/**
	 * Muestra por pantalla los viajes pasados como parámetro
	 * 
	 * @param viajes
	 */
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

	
	/*Invocaciones a métodos REST. 
	Antes de procesar la petición esta pasará por 
	un filtro de servlet en el que se comprobará si las credenciales son correctas.
	En el caso de aquellas invocaciones que devuelven objetos se producirá una 
	excepción del tipo 'ProcessingException' que deberemos controlar*/

	private List<Trip> getTripsPromoted(Long id) {

		GenericType<List<Trip>> listm = new GenericType<List<Trip>>() {
		};

		List<Trip> lista = ClientBuilder.newClient()
				.register(new Authenticator(login, password))
				.target(REST_TRIP_SERVICE_URL)
				.path("promotor/"+id.toString())
				.request()
				.get()
				.readEntity(listm);

		return lista;

	}

	private User getUserByLogin() {
		try{
		return (User) ClientBuilder.newClient()
				.register(new Authenticator(login, password))
				.target(REST_USER_SERVICE_URL)
				.path("login/" + login)
				.request()
				.accept(MediaType.APPLICATION_XML)
				.get()
				.readEntity(User.class);
		}catch(ProcessingException e){
			return null;
		}
	}
	
	
	private User getUserById(Long id) {
		try{
		return (User) ClientBuilder.newClient()
				.register(new Authenticator(login, password))
				.target(REST_USER_SERVICE_URL)
				.path("id/" + id.toString())
				.request()
				.accept(MediaType.APPLICATION_XML)
				.get()
				.readEntity(User.class);
		}catch(ProcessingException e){
			return null;
		}
	}

	private List<Application> getSolicitantesViaje(int idViaje) {

		try{
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
		
		}catch(ProcessingException e){
			return null;
		}

	}

	private void insertarAsiento(Seat seat) {
		ClientBuilder.newClient().register(new Authenticator(login, password))
				.target(REST_SEAT_SERVICE_URL)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.put(Entity.json(seat));
	
	}


	private void borrarSolicitud(Long idSolicitante, Long idViaje) {
		ClientBuilder
				.newClient()
				.register(new Authenticator(login, password))
				.target(REST_APPLICATION_SERVICE_URL)
				.path("/" + idSolicitante.toString() + "/" + idViaje.toString())
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.delete();
	}

	private void disminuirPlaza(Trip viaje) {
		ClientBuilder.newClient().register(new Authenticator(login, password))
				.target(REST_TRIP_SERVICE_URL)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.json(viaje));
	}

	private Trip obtenerViaje(Long idViaje) {
		try{
		return (Trip) ClientBuilder.newClient()
				.register(new Authenticator(login, password))
				.target(REST_TRIP_SERVICE_URL)
				.path("/viaje/" + idViaje.toString())
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get()
				.readEntity(Trip.class);
		}catch(ProcessingException e){
			return null;
			}		
	}

}

package com.sdi.ui.action;

import java.util.List;








import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.client.Authenticator;
import com.sdi.trip.Trip;
import com.sdi.user.User;


public class ObtenerViajesAction implements Action {

	private static final String REST_TRIP_SERVICE_URL = "http://localhost:8280"
			+ "/sdi3-13.Web/rest/TripsServiceRs";

	private static final String REST_USER_SERVICE_URL = "http://localhost:8280"
			+ "/sdi3-13.Web/rest/UserServiceRs";

	private String login;
	private String password;
	
	@Override
	public void execute() throws Exception {
		login = Console.readString("Login");
		password = Console.readString("Password");

		User usuario = getUserByLogin();

		List<Trip> viajesPromotor = getTripsPromoted(usuario.getId());
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

}

package com.sdi.ui.action;

import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.client.Authenticator;
import com.sdi.integration.MessageReceiver;
import com.sdi.model.trip.Trip;
import com.sdi.model.user.User;
import com.sdi.util.Jndi;


public class ObtenerViajesAction implements Action {

	private static final String REST_TRIP_SERVICE_URL = "http://localhost:8280"
			+ "/sdi3-13.Web/rest/TripsServiceRs";

	private static final String REST_USER_SERVICE_URL = "http://localhost:8280"
			+ "/sdi3-13.Web/rest/UserServiceRs";

	private String login;
	private String password;
	private int idViaje;
	private Long idUsuario;

	private Connection con;
	private Session session;
	private MessageProducer sender;
	
	private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String MENSAJES_QUEUE = "jms/queue/MensajesQueue";


	@Override
	public void execute() throws Exception {
		
		obtenerCredenciales();

		User usuario = getUserByLogin();
		
		if(usuario==null){
			System.out.println("Usuario y/o contraseña incorrectos");
			return;
			
		}
		
		idUsuario = usuario.getId();
		
		List<Trip> viajesPromotor = getTripsPromoted(usuario.getId());
		mostrarViajes(viajesPromotor);
		
		idViaje = Console.readInt("Selecciona ID de un viaje");
					
		initialize();
		
		System.out.println("Escribe tus mensajes y pulsa enter");
		System.out.println("Para salir escribe '.' ");
		
		String mensaje = "";
			
		while(!mensaje.equals(".")){		
			mensaje = Console.readString("-->");
			if(!mensaje.equals(".")){
				Message msg = createMessage(mensaje);
				sender.send(msg);
			}
		}
		
		
		}
	
	

	/**
	 * Pide por pantalla las credenciales del usuario
	 * 
	 */
	private void obtenerCredenciales() {
		login = Console.readString("Login");
		password = Console.readString("Password");
	}
	
	
	
	private void initialize() throws JMSException {
		ConnectionFactory factory =
		 (ConnectionFactory) Jndi.find( JMS_CONNECTION_FACTORY );
		Destination queue = (Destination) Jndi.find( MENSAJES_QUEUE );
		con = factory.createConnection("sdi", "password");
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		sender = session.createProducer(queue);
		
		
		
		MessageConsumer consumer = session.createConsumer(queue);
		consumer.setMessageListener( new MessageReceiver() );
		
		con.start();
	}
	
	
	private MapMessage createMessage(String mensaje) throws JMSException {
		MapMessage msg = session.createMapMessage();
		msg.setString("login", login);
		msg.setString("idUsuario", String.valueOf(idUsuario));
		msg.setString("idViaje", String.valueOf(idViaje));
		msg.setString("mensaje",mensaje);
		return msg;
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

	try{
	List<Trip> lista = ClientBuilder.newClient()
			.register(new Authenticator(login, password))
			.target(REST_TRIP_SERVICE_URL)
			.path(id.toString())
			.request()
			.get()
			.readEntity(listm);

	return lista;
	}catch(ProcessingException e){
		return null;
	}

}


private User getUserByLogin() {
	try{
	return (User) ClientBuilder.newClient()
			.register(new Authenticator(login, password))
			.target(REST_USER_SERVICE_URL)
			.path("login/"+login)
			.request()
			.accept(MediaType.APPLICATION_XML)
			.get()
			.readEntity(User.class);
	}catch(ProcessingException e){
		return null;
	}
}


}

package com.sdi.ui.action;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import com.sdi.integration.InvalidMessageListener;
import com.sdi.util.Jndi;

import alb.util.console.Console;
import alb.util.menu.Action;

public class AdministrarInvalidosAction implements Action{

	private Connection con;
	private Session session;
	
	private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String MENSAJES_QUEUE = "jms/queue/MensajesInvalidQueue";
	
	@Override
	public void execute() throws Exception {
		initialize();	
		
		Console.readInt("Pulsa una tecla para salir");

		con.close();
	}

	
	private void initialize() throws JMSException {
		ConnectionFactory factory =
		 (ConnectionFactory) Jndi.find( JMS_CONNECTION_FACTORY );
		con = factory.createConnection("sdi", "password");
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination topic = (Destination) Jndi.find(MENSAJES_QUEUE);
		MessageConsumer consumer = session.createConsumer(topic);
		consumer.setMessageListener( new InvalidMessageListener() );
		
		con.start();
	}
}

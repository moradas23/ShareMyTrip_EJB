package com.sdi.integration;

import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sdi.business.SeatService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;

@MessageDriven(activationConfig = { 
		@ActivationConfigProperty(
				propertyName = "destination", 
				propertyValue = "queue/MensajesQueue")
})
public class UserMessageListener implements MessageListener {
	
	public SeatService service;
	
	private Connection con;
	private Session session;
	MessageProducer sender;
	
	@Override
	public void onMessage(Message msg) {
		System.out.println("UserMessageListener: Msg received");
	
		MapMessage message = (MapMessage) msg;
		
		try {

			initialize();
			process(message);
			

		} catch (JMSException | NamingException jex) {
			jex.printStackTrace();

		}

	}

	/**
	 * Procesa los mensajes recibidos
	 * 
	 * @param msg
	 * @throws JMSException
	 * @throws NamingException
	 */
	private void process(MapMessage msg) throws JMSException, NamingException {
		
		
		service = Factories.services.getSeatService();
		
		List<Seat> implicados = service.findByTrip(Long.valueOf(msg.getString("idViaje")));
		
		boolean aux = false;
		
		for(Seat seat:implicados){
			if(seat.getUserId().equals(Long.valueOf(msg.getString("idUsuario")))){
				aux=true;
				break;
			}
		}
		
		if(aux){
			sendMessage(msg,implicados);		
		}else{
			
			//Meter en cola de inválidos!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		}
		
		con.close();
	}

	/**
	 * Envía el mensaje generado a el canal Publish-Subscribe 
	 * 
	 * @param message
	 * @param implicados
	 * @throws JMSException
	 * @throws NamingException
	 */
	public void sendMessage(MapMessage message,List<Seat> implicados) throws JMSException,
			NamingException {

		MapMessage msg = session.createMapMessage();
		
		Long idUsuario = Long.valueOf(message.getString("idUsuario"));
		
		StringBuilder sb = new StringBuilder();
		for(Seat s:implicados){
			if(!s.getUserId().equals(idUsuario))
				sb.append(s.getUserId()+":");
		}
		
		msg.setString("login", message.getString("login"));
		msg.setString("idUsuario",idUsuario.toString());
		msg.setString("idViaje", message.getString("idViaje"));
		msg.setString("mensaje", message.getString("mensaje"));
		msg.setString("implicados", sb.toString());

		sender.send(msg);
	}
	
	/**
	 * Inicializa los elementos JMS
	 * 
	 * @throws NamingException
	 * @throws JMSException
	 */
	public void initialize() throws NamingException, JMSException{
		
		Context context = new InitialContext();
		 
		 ConnectionFactory factory =
				 (ConnectionFactory) context.lookup("java:/ConnectionFactory");
		Destination queue = (Destination) context.lookup("java:/topic/MensajesTopic");
		con = factory.createConnection("sdi", "password");
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		sender = session.createProducer(queue);
		 
		con.start();
		
	}
	
}
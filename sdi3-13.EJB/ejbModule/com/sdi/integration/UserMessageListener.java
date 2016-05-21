package com.sdi.integration;

import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sdi.business.SeatService;
import com.sdi.business.impl.seat.EjbSeatService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;

@MessageDriven(activationConfig = { 
		@ActivationConfigProperty(
				propertyName = "destination", 
				propertyValue = "queue/MensajesQueue")
})
public class UserMessageListener implements MessageListener {


	
	private static final String SEAT_SERVICE_JNDI_KEY =
			"java:global/" + 
			"sdi3-13/"
			+ "sdi3-13.EJB/" 
			+ "EjbSeatService!"
			+ "com.sdi.business.impl.seat.RemoteSeatService";
	
//	@EJB(mappedName = "sdi3-13/sdi3-13.EJB/EjbSeatService!com.sdi.business.impl.seat.LocalSeatService") 
	public SeatService service;
	
	private TopicSession session;
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

	private void process(MapMessage msg) throws JMSException, NamingException {
		
		
		service = Factories.services.getSeatService();
		
		List<Seat> implicados = service.findByTrip(Long.valueOf(msg.getString("idViaje")));
		
		boolean aux = false;
		
		for(Seat seat:implicados){
			if(seat.getTripId().equals(Long.valueOf(msg.getString("idUsuario")))){
				aux=true;
				break;
			}
		}
		
		if(aux){
			sendMessage(msg,implicados);		
		}else{
			//Meter en cola de inválidos
		}
		

	}

	public void sendMessage(MapMessage message,List<Seat> implicados) throws JMSException,
			NamingException {

		MapMessage msg = session.createMapMessage();

		msg.setString("login", message.getString(message.getString("login")));
		msg.setString("idUsuario", message.getString(message.getString("idUsuario")));
		msg.setString("idViaje", message.getString(message.getString("idViaje")));
		msg.setString("Mensaje", message.getString(message.getString("mensaje")));
		msg.setObject("implicados", implicados);

		sender.send(msg);
	}
	
	public void initialize() throws NamingException, JMSException{
		

		Context context = new InitialContext();

		TopicConnectionFactory factory = (TopicConnectionFactory) 
				context.lookup("java:/ConnectionFactory");
		
		Topic topic = (Topic) context.lookup("java:/topic/MensajesTopic");
		
		TopicConnection con = factory.createTopicConnection("sdi", "password");
		
		
		 session = con.createTopicSession(false,
				Session.AUTO_ACKNOWLEDGE);

		 sender = session.createPublisher(topic);
		
		con.start();
		
		
		
	}
	
	

}
package com.sdi.integration;

import javax.ejb.ActivationConfigProperty;
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

import com.sdi.business.exception.BusinessException;

@MessageDriven(activationConfig = { 
		@ActivationConfigProperty(
				propertyName = "destination", 
				propertyValue = "queue/MensajesQueue")
})
public class UserMessageListener implements MessageListener {

	
	private TopicSession session;
	
	MessageProducer sender;
	
	@Override
	public void onMessage(Message msg) {
		System.out.println("UserMessageListener: Msg received");

		try {

			initialize();
			process(msg);
			

			sendMessage((MapMessage) msg);

			// TENEMOS QUE AÑADIR AQUÍ EL MENSAJE AL TOPIC (Invocar método)

		} catch (JMSException | NamingException jex) {

			jex.printStackTrace();

		}

	}

	private void process(Message msg) throws BusinessException, JMSException {
		// Filtrado de mensajes

	}

	public void sendMessage(MapMessage message) throws JMSException,
			NamingException {

		/*// first configure and retreive initial context:
		Context context = new InitialContext();
		

		// get the topic factory:
		TopicConnectionFactory factory = (TopicConnectionFactory)

		context.lookup("ConnectionFactory");

		// create a topic connection and session:
		TopicConnection connection = factory.createTopicConnection();
		
		connection.start();
		
		TopicSession session = connection.createTopicSession(false,
				Session.AUTO_ACKNOWLEDGE);

		// finds the topic and build a publisher:
		Topic topic = (Topic) context.lookup("topic/MensajesTopic");
		MessageProducer publisher = session.createPublisher(topic);
*/
		MapMessage msg = session.createMapMessage();

		msg.setString("login", message.getString("login"));
		msg.setString("Mensaje", message.getString("mensaje"));

		
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
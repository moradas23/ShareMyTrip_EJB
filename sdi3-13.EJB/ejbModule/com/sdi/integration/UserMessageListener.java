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

		} catch (JMSException | NamingException jex) {

			jex.printStackTrace();

		}

	}

	private void process(Message msg) throws BusinessException, JMSException {
		

	}

	public void sendMessage(MapMessage message) throws JMSException,
			NamingException {

		MapMessage msg = session.createMapMessage();

		msg.setString("login", message.getString(message.getString("login")));
		msg.setString("login", message.getString(message.getString("idViaje")));
		msg.setString("Mensaje", message.getString(message.getString("mensaje")));

		
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
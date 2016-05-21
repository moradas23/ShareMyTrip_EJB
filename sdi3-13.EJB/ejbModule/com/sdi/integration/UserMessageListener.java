package com.sdi.integration;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
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

	@Override
	public void onMessage(Message msg) {
		System.out.println("UserMessageListener: Msg received");

		try {

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

		// first configure and retreive initial context:
		Context context = new InitialContext();

		// get the topic factory:
		TopicConnectionFactory factory = (TopicConnectionFactory)

		context.lookup("ConnectionFactory");

		// create a topic connection and session:
		TopicConnection connection = factory.createTopicConnection();
		TopicSession session = connection.createTopicSession(false,
				Session.AUTO_ACKNOWLEDGE);

		// finds the topic and build a publisher:
		Topic topic = (Topic) context.lookup("topic/MensajesTopic");
		TopicPublisher publisher = session.createPublisher(topic);

		MapMessage msg = session.createMapMessage();

		msg.setString("login", message.getString("login"));
		msg.setString("Mensaje", message.getString("mensaje"));

		publisher.publish(msg);

	}

}
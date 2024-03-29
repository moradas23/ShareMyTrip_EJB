package com.sdi.integration;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;


@MessageDriven(activationConfig = { 
		@ActivationConfigProperty(
				propertyName = "destination", 
				propertyValue = "queue/MensajesInvalidQueue")
})
public class InvalidMessageListener  implements MessageListener{

	@Override
	public void onMessage(Message msg) {
		
		MapMessage message = (MapMessage) msg;
		try {
			System.err.println("InvalidMessage: "+message.getString("login") + ": "+ message.getString("mensaje"));

		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}

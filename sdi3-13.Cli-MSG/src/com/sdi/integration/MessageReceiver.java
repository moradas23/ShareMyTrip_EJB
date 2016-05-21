package com.sdi.integration;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;


@MessageDriven( 
		activationConfig = {
		@ActivationConfigProperty(
				propertyName = "destination",
				propertyValue = "queue/MensajesQueue")
})
public class MessageReceiver implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		System.out.println("MSG received");
		try {
			System.out.println(msg.getStringProperty("login")+": "+msg.getStringProperty("mensaje"));
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}

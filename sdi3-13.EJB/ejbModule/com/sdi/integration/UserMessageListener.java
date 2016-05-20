package com.sdi.integration;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(
activationConfig = {
		@ActivationConfigProperty(
				propertyName = "MensajesTopic",
				propertyValue = "topic/MensajesTopic")

})

public class UserMessageListener implements MessageListener {

	@Override
	public void onMessage(Message arg0) {
		
		
	}

}

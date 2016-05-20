package com.sdi.integration;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.sdi.business.exception.BusinessException;

@MessageDriven(
activationConfig = {
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
		} catch (JMSException jex) {
		// here we should log the exception
		jex.printStackTrace();
		}
		
		//TENEMOS QUE AÑADIR AQUÍ EL MENSAJE AL TOPIC

		
	}

	private void process(Message msg) throws BusinessException, JMSException {
		if (!messageOfExpectedType(msg)) {
			System.out.println("Not of expected type " + msg);
			return;
			}
			
		
	}


	private boolean messageOfExpectedType(Message msg) {
		// TODO Auto-generated method stub
		return false;
	}

	}
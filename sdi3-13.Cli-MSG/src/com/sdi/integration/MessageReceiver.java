package com.sdi.integration;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;


@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", 
						propertyValue = "topic/MensajesTopic") })
public class MessageReceiver implements MessageListener {

	@Override
	public void onMessage(Message msg) {

		MapMessage message = (MapMessage) msg;
		try {
			boolean esMismo=false;
			String[] implicados = message.getString("implicados").split(":");
			for(String i :implicados){
				if(i.compareTo(message.getString("idUsuario"))==0){
					esMismo=true;
					break;
				}
			}
			
			if(!esMismo)
				System.out.println(message.getString("login") + ": "+ message.getString("mensaje"));
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}

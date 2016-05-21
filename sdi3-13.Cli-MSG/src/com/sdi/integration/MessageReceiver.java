package com.sdi.integration;

import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.sdi.model.seat.Seat;


@MessageDriven( 
		activationConfig = {
		@ActivationConfigProperty(
				propertyName = "destination",
				propertyValue = "queue/MensajesQueue")
})
public class MessageReceiver implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		
		MapMessage message = (MapMessage) msg;
		
		try {
			List<Seat> implicados = ((List<Seat>) message.getObject("implicados"));
			implicados.size();
		} catch (JMSException e1) {
			e1.printStackTrace();
		}
		
		try {
			System.out.println(message.getString("login")+": "+message.getString("mensaje"));
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}

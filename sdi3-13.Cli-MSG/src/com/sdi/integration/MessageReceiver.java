package com.sdi.integration;

import java.util.Arrays;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.sdi.util.EstadoCliente;


@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", 
						propertyValue = "topic/MensajesTopic") })
public class MessageReceiver implements MessageListener {

	@Override
	public void onMessage(Message msg) {

		MapMessage message = (MapMessage) msg;
		try {
						
			List<String> implicados = Arrays.asList(message.getString("implicados").split(":"));
			
			if(EstadoCliente.getIdUsuario()!=null
					&& EstadoCliente.getIdViaje().equals(Long.valueOf(message.getString("idViaje"))) 
					&& implicados.contains(EstadoCliente.getIdUsuario().toString())){
				
				System.out.println("\n\t"+message.getString("login") + ": "+ message.getString("mensaje"));
			}

				
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}

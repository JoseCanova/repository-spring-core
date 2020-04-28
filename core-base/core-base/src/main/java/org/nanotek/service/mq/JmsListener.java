package org.nanotek.service.mq;

import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.Message;
import org.apache.activemq.util.ByteSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

@Service
@Qualifier(value = "JmsListener")
public class JmsListener implements SessionAwareMessageListener<ActiveMQBytesMessage>{

	private Logger logger = LoggerFactory.getLogger(JmsListener.class);
	
	@Override
	public void onMessage(ActiveMQBytesMessage message, Session session) throws JMSException {
		logger.debug("Received JMSTYPE: "+ message.getJMSType());		
		logger.debug("Received JMSTYPE: "+ message.getJMSXMimeType());
		Message innerMessage = message.getMessage();
		ByteSequence sequence = innerMessage.getContent();
		String payLoad = new String (sequence.data);
	}

}

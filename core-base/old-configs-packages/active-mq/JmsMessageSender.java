package org.nanotek.apachemq;

import java.util.stream.Stream;

import javax.jms.Queue;

import org.nanotek.IdBase;
import org.nanotek.Sender;
import org.springframework.jms.core.JmsMessagingTemplate;

public abstract class JmsMessageSender <K extends IdBase<?,?>, T extends Ticket<?,?>>  implements Sender<K> , Ticket<K,T>{

	private static final long serialVersionUID = -5447828989965356991L;

	protected JmsMessagingTemplate jmsMessagingTemplate;
 
	protected Queue queue;
 
	public JmsMessageSender(JmsMessagingTemplate jmsMessagingTemplate , Queue queue ) { 
		this.jmsMessagingTemplate = jmsMessagingTemplate; 
		this.queue = queue;
	}
	
	public Stream<?> send(K message){
		jmsMessagingTemplate.convertAndSend(this.queue, message);
		return withTicket(message);
	}

	public JmsMessagingTemplate getJmsMessagingTemplate() {
		return jmsMessagingTemplate;
	}

	public void setJmsMessagingTemplate(JmsMessagingTemplate jmsMessagingTemplate) {
		this.jmsMessagingTemplate = jmsMessagingTemplate;
	}

	public Queue getQueue() {
		return queue;
	}

	public void setQueue(Queue queue) {
		this.queue = queue;
	}
	
}

package org.nanotek.apachemq.listener;

import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.nanotek.IdBase;
import org.nanotek.Mediator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;

import com.google.gson.Gson;

public class BaseBeanJmsListener <K extends IdBase<K,?>, T extends BaseBeanJmsListener<K , T>> implements SessionAwareMessageListener<ActiveMQBytesMessage>{
    
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired 
	Gson gson; 
	
	private Mediator<K> mediator; 
	
	private Class<K> clazz;
	
	public BaseBeanJmsListener(Mediator<K> mediator2 , Class<K> clazz) {
		this.mediator = mediator2;
		this.clazz = clazz;
	}
	
	@Override
	public void onMessage(ActiveMQBytesMessage message, Session session) throws JMSException {
		readAndDispatch(message,session);
	}

	private void readAndDispatch(ActiveMQBytesMessage message, Session session) {
		try { 
			K bean = MessageListenerHelper.processMessage(message, gson, getClazz());
			getMediator().mediate(bean);
			log.info(bean.toJson());
		}catch (Exception ex) { 
			log.error("error processing message pipeline", ex);
		}
	}

	protected Mediator<K> getMediator(){
		return mediator;
	}
	
	protected Class<K> getClazz(){
		return  clazz;
	}

}

package org.nanotek.apachemq;

import java.util.concurrent.Future;

import javax.jms.Queue;

import org.nanotek.beans.csv.BaseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class BaseBeanSender<K extends BaseBean<?,?> , ID extends BaseBeanSender<K,ID>> extends JmsMessageSender<K,ID> {

	public BaseBeanSender(@Autowired JmsMessagingTemplate jmsTemplate , @Autowired @Qualifier("baseQueue") Queue queue ) {
		super(jmsTemplate,queue);
	}
	
	@Async("threadPoolTaskExecutor")
	public Future<?> sendAsync(K rb) { 
		return new AsyncResult<> (send(rb));
	}
	
}

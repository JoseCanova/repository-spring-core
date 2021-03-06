package org.nanotek.service.mq;

import java.util.concurrent.Future;

import javax.jms.Queue;

import org.nanotek.IdBase;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

public class AsyncBaseSender 
<K extends IdBase<K,?>, ID extends AsyncBaseSender<K,ID> > 
extends JmsMessageSender<K,ID> {

	private static final long serialVersionUID = -8803675234764455668L;

	public AsyncBaseSender(JmsMessagingTemplate jmsTemplate , Queue queue ) {
		super(jmsTemplate,queue);
	}
	
	@Async("serviceTaskExecutor")
	public Future<?> sendAsync(K rb) { 
		return new AsyncResult<> (send(rb));
	}
}

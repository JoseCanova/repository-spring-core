package org.nanotek.apachemq;

import java.util.concurrent.Future;
import java.util.stream.Stream;

import javax.jms.Queue;

import org.nanotek.IdBase;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

public class AsyncBaseSender <K extends IdBase<K,?>, ID extends AsyncBaseSender<K,ID> > extends JmsMessageSender<K,ID> {

	public AsyncBaseSender(JmsMessagingTemplate jmsTemplate , Queue queue ) {
		super(jmsTemplate,queue);
	}
	
	@Async("threadPoolTaskExecutor")
	public Future<?> sendAsync(K rb) { 
		return new AsyncResult<> (send(rb));
	}
	
	@Override
	public Stream<?> send (K message) { 
		return super.send(message);
	}
}

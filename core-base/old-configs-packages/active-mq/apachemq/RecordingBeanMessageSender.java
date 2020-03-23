package org.nanotek.apachemq;

import java.util.concurrent.Future;
import java.util.stream.Stream;

import javax.jms.Queue;

import org.nanotek.beans.csv.RecordingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class RecordingBeanMessageSender<K extends RecordingBean<?,?>, ID extends RecordingBeanMessageSender<?,?>> extends JmsMessageSender<K,ID> {

	public RecordingBeanMessageSender(@Autowired JmsMessagingTemplate jmsTemplate , 
										@Autowired @Qualifier("recordingQueue")Queue queue) { 
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
package org.nanotek.apachemq;

import java.util.concurrent.Future;
import java.util.stream.Stream;

import javax.jms.Queue;

import org.nanotek.beans.csv.TrackBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class TrackBeanMessageSender<K extends TrackBean<?,?>, ID extends TrackBeanMessageSender<K,ID>> extends JmsMessageSender<K , ID> {

	private static final long serialVersionUID = 7034507517032906130L;

	public TrackBeanMessageSender(@Autowired JmsMessagingTemplate jmsMessagingTemplate,
									@Autowired @Qualifier("trackBeanQueue") Queue queue) {
		super(jmsMessagingTemplate, queue);
	}
	
	@Async("threadPoolTaskExecutor")
	public Future<?> sendAsync(K tb) { 
		return new AsyncResult<> (send(tb));
	}
	
	@Override
	public Stream<?> send (K message) { 
		return super.send(message);
	}

}

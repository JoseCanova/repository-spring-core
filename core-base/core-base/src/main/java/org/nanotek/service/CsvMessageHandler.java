package org.nanotek.service;

import org.nanotek.Base;
import org.nanotek.processor.csv.CsvBaseProcessor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;

public class CsvMessageHandler implements MessageHandler{ 

	private MessageChannel dispatcherChannel;

	private MessageChannel replyChannel;

	private CsvBaseProcessor<?,?,?> processor;

	public CsvMessageHandler(MessageChannel dispatcherChannel, MessageChannel replyChannel,
			CsvBaseProcessor<?, ?,?> processor) {
		super();
		this.dispatcherChannel = dispatcherChannel;
		this.replyChannel = replyChannel;
		this.processor = processor;
	}

	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		try { 
			processor.reopenFile();
			Object itBean = null; 
			if (replyChannel.send(message)){
				do{ 
					itBean = processor.next();
					if (itBean !=null) { 
						Message<?> m = MessageBuilder.withPayload(itBean).build();
						dispatcherChannel.send(m);
					}
				}while(itBean !=null);
			}
		}catch(Exception ex) { 
			throw new MessagingException(message , ex);
		}
	}
}

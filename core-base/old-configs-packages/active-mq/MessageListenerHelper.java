package org.nanotek.apachemq.listener;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.Message;
import org.apache.activemq.util.ByteSequence;
import org.nanotek.Base;

import com.google.gson.Gson;

public class MessageListenerHelper {

	public static <K extends Base<K>> K processMessage(ActiveMQBytesMessage message , Gson gson , Class<K> clazz){ 
		Message innerMessage = message.getMessage();
		ByteSequence sequence = innerMessage.getContent();
		String payload = new String (sequence.data);
		return gson.fromJson(payload , clazz);
	}
	
}

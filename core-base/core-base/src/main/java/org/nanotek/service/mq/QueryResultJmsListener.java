package org.nanotek.service.mq;

import java.io.StringReader;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.Message;
import org.apache.activemq.util.ByteSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
@Qualifier(value = "QueryResultJmsListener")
public class QueryResultJmsListener extends JmsListener{

	@Autowired
	Gson gson;
	
	public QueryResultJmsListener() {
	}

	@Override
	public void onMessage(ActiveMQBytesMessage message, Session session) throws JMSException {
		Message innerMessage = message.getMessage();
		ByteSequence sequence = innerMessage.getContent();
		StringBuffer bf =  new StringBuffer().append(sequence.getData());
		processBuffer(bf);
	}

	private void processBuffer(StringBuffer bf) {
		String result = bf.toString();
		Map<String,Object> valueMap = gson.fromJson(new StringReader(result), Map.class);
		Object nameValue = valueMap.get("id");
		Object className = valueMap.get("className");
		Object results = valueMap.get("results");
		System.out.println("");
	}
	
}

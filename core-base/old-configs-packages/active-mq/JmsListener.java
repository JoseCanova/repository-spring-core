package org.nanotek.apachemq.listener;

import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.Message;
import org.apache.activemq.util.ByteSequence;
import org.nanotek.Transformer;
import org.nanotek.beans.csv.ReleaseBean;
import org.nanotek.beans.entity.Release;
import org.nanotek.service.jpa.ReleaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
@Qualifier(value = "JmsListener")
public class JmsListener implements SessionAwareMessageListener<ActiveMQBytesMessage>{

	@Autowired
	private Gson gson;
	
	@Autowired 
	@Qualifier("ReleaseTransformerOld")
	private Transformer<ReleaseBean,Release> transformer;
	
	@Autowired
	private ReleaseJpaService jpaService;

	@Override
	public void onMessage(ActiveMQBytesMessage message, Session session) throws JMSException {
		System.out.println("Received JMSTYPE: "+ message.getJMSType());		
		System.out.println("Received JMSTYPE: "+ message.getJMSXMimeType());
		Message innerMessage = message.getMessage();
		ByteSequence sequence = innerMessage.getContent();
		String payLoad = new String (sequence.data);
		ReleaseBean releaseBean = gson.fromJson(payLoad, ReleaseBean.class);
		System.out.println("message " + releaseBean.toString());	
		Release release = transformer.transform(releaseBean);
		if (isValid(release)) {
			System.out.println("result " + release.toString());	
			jpaService.save(release);
		}
	}

	private boolean isValid(Release release) {
		
		return release !=null 
					&& release.getId() != null ;
	}

}

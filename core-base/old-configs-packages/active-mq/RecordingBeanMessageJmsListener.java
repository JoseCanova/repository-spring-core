package org.nanotek.apachemq.listener;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.validation.Valid;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.nanotek.Transformer;
import org.nanotek.beans.csv.RecordingBean;
import org.nanotek.beans.entity.Recording;
import org.nanotek.service.jpa.RecordingJpaService;
import org.nanotek.service.jpa.RecordingLengthJpaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.google.gson.Gson;

@Service
@Validated
@Qualifier(value="RecordingBeanMessageJmsListener")
public class RecordingBeanMessageJmsListener 
implements SessionAwareMessageListener<ActiveMQBytesMessage> , Transformer<RecordingBean,Recording>{

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	private Gson gson;
	
	@Autowired
	@Qualifier("RecordingTransformerOld")
	private Transformer<RecordingBean,Recording> transformer;
	
	@Autowired
	private RecordingJpaService jpaService; 
	
	@Autowired
	private RecordingLengthJpaService lengthService;

	@Override
	public void onMessage(ActiveMQBytesMessage message, Session session) throws JMSException {
		try { 
			RecordingBean bean = MessageListenerHelper.processMessage(message, gson, RecordingBean.class);
			log.info(bean.toJson());
			validateAndSave(bean);
		}catch (Exception ex) { 
			log.error("Error processing Recording Bean Messae" , ex);
		}
	}

	@Transactional
	private void validateAndSave(@Valid RecordingBean bean) {
		Recording recording = transform(bean);
		jpaService.save(recording);
		if (recording.getRecordingLenght() !=null)
			lengthService.save(recording.getRecordingLenght());
	}
	
	@Override
	public Recording transform(RecordingBean bean) {
		return transformer.transform(bean);
	}
}

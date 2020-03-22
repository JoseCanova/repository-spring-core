package org.nanotek.apachemq.listener;

import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.Message;
import org.apache.activemq.util.ByteSequence;
import org.nanotek.beans.csv.ArtistCreditNameBean;
import org.nanotek.beans.entity.ArtistCreditName;
import org.nanotek.service.tranformer.ArtistCreditNameTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

@Service
public class ArtistCreditNameBeanJmsListener implements SessionAwareMessageListener<ActiveMQBytesMessage>{

	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private Gson gson;

	@Autowired 
	private ArtistCreditNameTransformer transformer;

//	@Autowired 
//	private ArtistCreditNameJpaService jpaService;

	@Override
	@Async
	public void onMessage(ActiveMQBytesMessage message, Session session) throws JMSException {
		Message innerMessage = message.getMessage();
		ByteSequence sequence = innerMessage.getContent();
		String payload = new String (sequence.data);
		ArtistCreditNameBean artistCreditName = gson.fromJson(payload , ArtistCreditNameBean.class);
		log.info(payload);
		ArtistCreditName acn  = null;
		try { 
			acn = transformer.transform(artistCreditName);
		}catch (Exception ex) { 
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
		if(acn != null) { 
			try { 
				saveArtistNameCreditRel(artistCreditName);
			}catch (Exception ex) { 
				ex.printStackTrace();
				log.info(ex.getMessage());
			}
		}
	}

	@Transactional
	private void saveArtistNameCreditRel(ArtistCreditNameBean ab) {
//		jpaService.saveFlatArtistCreditName(new FlatArtistCreditName(ab.getId() , ab.getArtistCreditId() , ab.getPosition() , ab.getArtistId() , ab.getName() , ab.getJoinPhrase()));
//		jpaService.saveFlatArtistNameCreditRel(new FlatArtistNameCreditRel(ab.getArtistId() , ab.getArtistCreditId()));
	}

}

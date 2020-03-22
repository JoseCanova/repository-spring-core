package org.nanotek.apachemq.listener;

import java.util.Optional;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.validation.Valid;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.nanotek.BaseException;
import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.ArtistComment;
import org.nanotek.service.jpa.ArtistCommentJpaService;
import org.nanotek.service.jpa.ArtistJpaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.google.gson.Gson;

@Service
@Validated
@Qualifier(value="ArtistBeanJmsListener")
public class ArtistBeanJmsListener<K extends Artist<K>> implements SessionAwareMessageListener<ActiveMQBytesMessage>{

	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private Gson gson;

	@Autowired
	private ArtistJpaService<K> artistJpaService;
	
	@Autowired
	private ArtistCommentJpaService artistCommentJpaService;

	@Override
	@Async
	public void onMessage(ActiveMQBytesMessage message, Session session) throws JMSException {
		ArtistBean artistBean = MessageListenerHelper.processMessage(message, gson, ArtistBean.class);
		try {
			validateAndSave(artistBean);
		}catch (Exception ex) {
			log.error("error - on processing artist" , ex);
		}
	}

	
	private void validateAndSave(@Valid ArtistBean artistBean) { 
		Optional<K> optArtist = artistJpaService.findByArtistId(artistBean.getArtistId());
		if (optArtist.isPresent()) { 
			verifyArtistComment(optArtist.get() , artistBean);
		}else { 
			throw new BaseException();
		}
	}

	@Transactional
	private void verifyArtistComment(Artist artist , ArtistBean artistBean) {
		if (artist.getArtistComment() == null) { 
			if (!"".equals(artistBean.getComment()) && !isNull(artistBean.getComment()))
			{ 
				ArtistComment comment = new ArtistComment(artistBean.getComment() , artist);
				artist.setArtistComment(comment);
				saveArtistComment(comment);
			}
		}
	}

	private void saveArtistComment(ArtistComment comment) {
		artistCommentJpaService.save(comment);
	}

	private boolean isNull(String comment) {
		return comment == null || "\\N".equalsIgnoreCase(comment);
	}

	private  K save(K artist) {
		return artistJpaService.save(artist);
	}
}

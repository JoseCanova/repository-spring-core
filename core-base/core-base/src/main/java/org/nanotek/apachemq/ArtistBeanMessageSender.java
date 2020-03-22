package org.nanotek.apachemq;

import javax.jms.Queue;

import org.nanotek.beans.csv.ArtistBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ArtistBeanMessageSender<K extends ArtistBean<?,?> , ID extends ArtistBeanMessageSender<K,ID> > extends JmsMessageSender<K,ID>  {

	public ArtistBeanMessageSender(@Autowired JmsMessagingTemplate jmsTemplate ,
										@Autowired @Qualifier("artistNameQueue")Queue queue) {
		super(jmsTemplate,queue);
	}

}

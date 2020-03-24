package org.nanotek.service.mq;

import org.nanotek.Dispatcher;
import org.nanotek.apachemq.AsyncBaseSender;
import org.nanotek.beans.csv.ArtistCreditBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ArtistCreditBeanJmsDispatcher<K extends ArtistCreditBean<K,?>> implements Dispatcher<K>{

	@Autowired
	@Qualifier("ArtistCreditBeanMessageSender")
	AsyncBaseSender<K,?> sender;
	
	public ArtistCreditBeanJmsDispatcher() {
	}

	public void dispatch(K bean) {
		sender.sendAsync(bean);
	}


}

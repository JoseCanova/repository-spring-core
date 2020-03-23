package org.nanotek.service.http;

import org.nanotek.apachemq.AsyncBaseSender;
import org.nanotek.beans.csv.ArtistCreditBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ArtistCreditAsyncHttpClientServices {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	final String uri = "http://localhost:8080/csv/artist_credit/next";
	
	@Autowired
	@Qualifier("ArtistCreditBeanMessageSender")
	AsyncBaseSender<ArtistCreditBean> sender;

	@Autowired
	@Qualifier("serviceTaskExecutor")
	private ThreadPoolTaskExecutor taskExecutor;

	//	@Async("threadPoolTaskExecutor")
	public void process() {
		log.info("started");
		RestTemplate restTemplate = new RestTemplate();
		ArtistCreditBean artistCredit = null;
		do {
			artistCredit = restTemplate.getForObject(uri, ArtistCreditBean.class);
			if (artistCredit !=null)
				sender.sendAsync(artistCredit);
		}while((artistCredit != null));

	}
}

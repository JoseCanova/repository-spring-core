package org.nanotek.service.http;

import org.nanotek.apachemq.BaseBeanSender;
import org.nanotek.beans.csv.ArtistCreditNameBean;
import org.nanotek.service.validator.ArtistCreditNameBeanValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


//TODO: Refactor this class.
@Service
public class ArtistCreditNameAsyncHttpClientServices {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	final String uri = "http://localhost:8080/artist_credit_name_bean/next";

	/*
	 * @Autowired private ArtistCreditNameJpaService jpaService;
	 * 
	 * @Autowired private ArtistCreditNameTransformer transformer;
	 */
	
	@Autowired
	private BaseBeanSender<ArtistCreditNameBean> sender;

	@Autowired
	@Qualifier("serviceTaskExecutor")
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private ArtistCreditNameBeanValidator validator;

	@Async("threadPoolTaskExecutor")
	public void process() {
		taskExecutor.execute(new Runnable() {
			public void run() {
				log.info("started");
				RestTemplate restTemplate = new RestTemplate();
				ArtistCreditNameBean testValue;
				do {
					ArtistCreditNameBean artistCreditName;
					artistCreditName = restTemplate.getForObject(uri, ArtistCreditNameBean.class);
					testValue = artistCreditName;
					if (artistCreditName !=null) { 
						if (validator.test(artistCreditName)) {
							sender.send(artistCreditName);
							log.info("ArtistCreditName " + artistCreditName);
						}
					}

				}while((testValue != null));
			}
		});
	}

}

package org.nanotek.service.http;

import java.util.concurrent.Future;

import org.nanotek.beans.csv.ArtistBean;
import org.nanotek.service.mq.ArtistDispatcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AsyncHttpClientServices {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	final String uri = "http://localhost:8080/csv/artist_bean/next";

	@Autowired
	ArtistDispatcherService dispatcher;

	@Async("threadPoolTaskExecutor")
	public Future<String> process() throws InterruptedException {
		RestTemplate restTemplate = new RestTemplate();
		ArtistBean artist;
		log.info("started");
		do {
			artist = restTemplate.getForObject(uri, ArtistBean.class);
			if (artist !=null) {
				dispatcher.dispatch(artist);
			}
		}while((artist != null));
		return new AsyncResult<>("finished : " + Thread.currentThread().getId());
	}

}

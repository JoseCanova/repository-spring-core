package org.nanotek.service.http;

import java.util.concurrent.Future;

import org.nanotek.beans.csv.TrackBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TrackBeanHttpClientService {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	  
    final String uri = "http://localhost:8080/track_bean/next";

    @Async("threadPoolTaskExecutor")
	public Future<TrackBean> process(){ 
    	RestTemplate restTemplate = new RestTemplate();
    	TrackBean track = null;
    	log.info("started");
    	track = restTemplate.getForObject(uri, TrackBean.class);
    	return new AsyncResult<>(track);
    }
}

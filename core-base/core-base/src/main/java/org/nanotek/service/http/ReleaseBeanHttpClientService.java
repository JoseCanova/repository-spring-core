package org.nanotek.service.http;

import java.util.concurrent.Future;

import org.nanotek.beans.csv.ReleaseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReleaseBeanHttpClientService {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	  
    final String uri = "http://localhost:8080/release_bean/next";

    @Async("threadPoolTaskExecutor")
	public Future<ReleaseBean> process(){ 
    	RestTemplate restTemplate = new RestTemplate();
    	ReleaseBean release = null;
    	log.info("started");
    	release = restTemplate.getForObject(uri, ReleaseBean.class);
    	return new AsyncResult<>(release);
    }
}

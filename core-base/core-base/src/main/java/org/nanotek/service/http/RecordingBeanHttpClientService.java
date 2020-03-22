package org.nanotek.service.http;

import java.util.concurrent.Future;

import org.nanotek.beans.csv.RecordingBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecordingBeanHttpClientService {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	  
    final String uri = "http://localhost:8080/csv/recording_bean/next";
    
    @Async("threadPoolTaskExecutor")
   	public Future<RecordingBean> process(){ 
       	RestTemplate restTemplate = new RestTemplate();
       	RecordingBean recording = null;
       	log.info("started");
       	recording  = restTemplate.getForObject(uri, RecordingBean.class);
       	return new AsyncResult<>(recording);
       }
    
}

package org.nanotek.controller.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.nanotek.beans.csv.TrackBean;
import org.nanotek.service.http.TrackBeanHttpClientService;
import org.nanotek.service.mq.TrackBeanDispatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class TrackBeanAsyncController{

	@Autowired
	TrackBeanHttpClientService asyncTrackHttpClientService;

	@Autowired
	TrackBeanDispatcherService sender;

	@RequestMapping("/track_bean/run")
	public String run() throws InterruptedException, ExecutionException	{

		StringBuffer log = new StringBuffer();

		Future<TrackBean> process1 = null;
		do{
			process1 = asyncTrackHttpClientService.process();
			while(!process1.isDone()){
				Thread.sleep(5);
			}
			if ( process1.get() !=null) { 
				sender.dispatch(process1.get());
			}
		}while (process1 !=null && process1.get() != null);
		log.append("<br />Process 1 finished");
		return log.toString();
	}
}

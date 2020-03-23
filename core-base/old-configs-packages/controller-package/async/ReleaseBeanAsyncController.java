package org.nanotek.controller.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.nanotek.apachemq.ReleaseBeanMessageSender;
import org.nanotek.beans.csv.ReleaseBean;
import org.nanotek.service.http.ReleaseBeanHttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ReleaseBeanAsyncController {

	@Autowired
	ReleaseBeanHttpClientService asyncReleaseHttpClientService;

	@Autowired
	ReleaseBeanMessageSender sender;

	@RequestMapping("/release_bean/run")
	public String run() throws InterruptedException, ExecutionException	{

		StringBuffer log = new StringBuffer();

		Future<ReleaseBean> process1 = null;
		do{
			process1 = asyncReleaseHttpClientService.process();
			while(!process1.isDone()){
				Thread.sleep(5);
			}
			if ( process1.get() !=null) { 
				sender.send(process1.get());
			}
		}while (process1 !=null && process1.get() != null);
		log.append("<br />Process 1 finished");
		return log.toString();
	}
}

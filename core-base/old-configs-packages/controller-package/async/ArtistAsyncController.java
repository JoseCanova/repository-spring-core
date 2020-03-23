package org.nanotek.controller.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.nanotek.service.http.AsyncHttpClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ArtistAsyncController {

	@Autowired
	AsyncHttpClientServices asyncHttpClientServices;

	@RequestMapping("/artist/run")
	public String run() throws InterruptedException, ExecutionException	{

		StringBuffer log = new StringBuffer();

		Future<String> process1 = asyncHttpClientServices.process();

	    while(!process1.isDone()){
	        Thread.sleep(20);
	      }

	    log.append("Process is DONE!");
	    // Log results
	    log.append("<br />Process 1: " + process1.get());

		return log.toString();
	}
}

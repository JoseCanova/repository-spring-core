package org.nanotek.service.http;

import javax.annotation.Nullable;

import org.nanotek.service.report.CsvEntitiesTaskReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Qualifier(value = "BrainzController")
@RequestMapping(path={"/report"},produces = MediaType.APPLICATION_JSON_VALUE)
@PresentationHandler
public class CsvEntitiesTaskHandler {

	private GetHandler<?,?> handler = (request) -> handleRequest(request);
	
	private 
	CsvEntitiesTaskReport csvEntitiesTaskReport;
	
	@Autowired
	public CsvEntitiesTaskHandler(CsvEntitiesTaskReport csvEntitiesTaskHandler2) {
		this.csvEntitiesTaskReport = csvEntitiesTaskHandler2;
	}
	
	private Object handleRequest(@Nullable Object request) {
		return csvEntitiesTaskReport.generateTasklist();
	}

	@GetMapping(path = "/tasks")
	public ResponseEntity<?> tasks(){
		return new ResponseEntity<>(handler.apply(null) ,HttpStatus.OK);
	}
	
	
}

package org.nanotek.service.http;

import java.util.List;
import java.util.concurrent.Callable; // Import Callable

import org.nanotek.service.report.CsvEntitiesTaskReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Qualifier(value = "BrainzController")
@RequestMapping(path={"/report"},produces = MediaType.APPLICATION_JSON_VALUE)
@PresentationHandler
public class CsvEntitiesTaskHandler {

	// GetHandler still works as a Supplier
	private GetHandler<List<?>> handler = () -> handleRequest();
	
	private CsvEntitiesTaskReport csvEntitiesTaskReport;
	
	@Autowired
	public CsvEntitiesTaskHandler(CsvEntitiesTaskReport csvEntitiesTaskHandler2) {
		this.csvEntitiesTaskReport = csvEntitiesTaskHandler2;
	}
	
	private List<?> handleRequest() {
		// This method will now be called within the Callable's execution thread
		return csvEntitiesTaskReport.generateTasklist();
	}

	@GetMapping(path = "/tasks")
	public Callable<List<?>> tasks(){ // Changed return type to Callable<List<?>>
		// The lambda for Callable is executed by a Spring-managed task executor.
		// handler.get() (and thus generateTasklist()) will run in that separate thread.
		return () -> handler.get();
	}
	
}
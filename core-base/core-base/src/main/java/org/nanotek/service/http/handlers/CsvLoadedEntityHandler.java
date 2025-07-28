package org.nanotek.service.http.handlers;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.validation.constraints.NotNull;

import org.nanotek.BaseException;
import org.nanotek.opencsv.service.LoadedEntitiesReport;
import org.nanotek.service.http.GetFunctionHandler;
import org.nanotek.service.http.PresentationHandler;
import org.nanotek.service.report.LoadEntityReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * REST Controller for handling requests related to CSV loaded entity reports.
 * This class exposes endpoints to asynchronously retrieve reports on loaded entities.
 * The {@code @PresentationHandler} annotation likely marks this class as a component
 * responsible for presentation-layer logic within the application.
 */
@Qualifier(value = "CsvLoadedEntityHandler")
@RequestMapping(path={"/report"},produces = MediaType.APPLICATION_JSON_VALUE)
@PresentationHandler
public class CsvLoadedEntityHandler {

	/**
	 * A functional handler that encapsulates the logic for processing an entity key
	 * and returning a {@link LoadedEntitiesReport}. This uses a lambda expression
	 * to delegate to the private {@code handleRequest} method.
	 */
	private GetFunctionHandler<String, LoadedEntitiesReport> handler = (entityKey) -> handleRequest(entityKey);
	
	/**
	 * The service responsible for loading entity reports asynchronously.
	 * It is injected via constructor injection by Spring.
	 * The wildcard generic types indicate that this handler works with
	 * a generic report loading service that can handle various underlying
	 * strategy and bean types.
	 */
	private LoadEntityReport<?,?,?,?> loadEntityReport;
	
	/**
	 * Constructs a new CsvLoadedEntityHandler and injects the
	 * {@link LoadEntityReport} dependency.
	 * @param loadEntityReport1 The service for loading entity reports.
	 */
	@Autowired
	public CsvLoadedEntityHandler(@NotNull LoadEntityReport<?,?,?,?> loadEntityReport1) {
		this.loadEntityReport = loadEntityReport1;
	}
	
	/**
	 * Handles GET requests to retrieve a report for a specific entity key.
	 * This method returns a {@link Callable}, allowing Spring MVC to execute
	 * the request asynchronously in a separate thread managed by a task executor.
	 * This frees up the Servlet container thread, improving responsiveness
	 * for long-running operations.
	 *
	 * @param entityKey The key identifying the entity for which the report is requested.
	 * This value is extracted from the URL path.
	 * @return A {@link Callable} that, when executed, will return the
	 * {@link LoadedEntitiesReport} for the specified entity key.
	 */
	@GetMapping(path = "/tasks/{entityKey}")
	public Callable<LoadedEntitiesReport> tasks(@PathVariable(name = "entityKey") String entityKey){
		// The lambda for Callable is executed by a Spring-managed task executor.
		// handler.get() (and thus handleRequest()) will run in that separate thread.
		return ()-> handler.apply(entityKey);
	}
	
	/**
	 * Private helper method to handle the actual request processing and
	 * report retrieval. This method calls the {@link LoadEntityReport}
	 * service to asynchronously load the report and then waits for its completion.
	 *
	 * Note: There's a potential logical issue here:
	 * 1. `loadEntityReport.loadEntityReport(entityKey);` is called but its
	 * returned `CompletableFuture` is not captured or used.
	 * 2. Subsequently, `loadEntityReport.loadEntityReport("artistcredit");`
	 * is called with a hardcoded key, and its `CompletableFuture` is then
	 * used. This means the report returned will always be for "artistcredit",
	 * regardless of the `entityKey` passed in the URL path.
	 *
	 * @param entityKey The key identifying the entity for which the report is requested.
	 * @return The {@link LoadedEntitiesReport} for the specified entity.
	 * @throws BaseException if an error occurs during the asynchronous operation,
	 * such as InterruptedException, ExecutionException, or TimeoutException.
	 */
	private LoadedEntitiesReport handleRequest(String entityKey) {
		// Potential issue: The following line calls the service but its result is not used.
		// The key 'entityKey' passed here from the URL is effectively ignored for the actual report retrieval.
		loadEntityReport.loadEntityReport(entityKey); 
		
		// Wait for the asynchronous operation to complete and retrieve the result.
		// A timeout of 10 seconds is set to prevent indefinite waiting.
		// Current logic uses a hardcoded key "artistcredit" for the actual report retrieval.
		try {
			// This call uses a hardcoded entity key, "artistcredit", overriding the @PathVariable value.
			CompletableFuture<LoadedEntitiesReport> future = loadEntityReport.loadEntityReport(entityKey);
			LoadedEntitiesReport report =  future.get(10, TimeUnit.SECONDS);
			assert(report !=null);
			System.err.println(report);
			return report;
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			// Print stack trace for debugging purposes and rethrow as a custom BaseException.
			e.printStackTrace();
			throw new BaseException(e);
		}
		
		// Return the report, ensuring it's not null. If it somehow becomes null, throw an exception.
//		return Optional.ofNullable(report).orElseThrow(() -> new BaseException("LoadedEntitiesReport was null."));
	}

}
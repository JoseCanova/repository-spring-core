package org.nanotek.service.report;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Test;
import org.nanotek.opencsv.service.LoadedEntitiesReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Integration test class for {@link LoadEntityReport}.
 * This test is designed to run within a full Spring Boot application context,
 * allowing the {@link LoadEntityReport} service and its dependencies to be
 * fully autowired and exercised in a manner similar to how they would behave
 * in a running application.
 *
 * It validates the basic functionality of asynchronously loading an entity report.
 * For this test to pass successfully, all required Spring beans (e.g.,
 * CsvStrategyCategorizer, DatabaseEntityLoadReportService and their underlying
 * configurations like CSV file paths or database access) must be properly
 * configured and operational within the test environment.
 */
@SpringBootTest
public class LoadEntityReportTest {

	/**
	 * The {@link LoadEntityReport} service, which is automatically
	 * injected by the Spring Boot test context. The wildcard generic
	 * types {@code <?, ?, ?, ?>} are used as this test focuses on
	 * the overall report loading mechanism rather than specific
	 * type-bound behaviors of the underlying strategies or beans.
	 */
	@Autowired
	LoadEntityReport<?,?,?,?> loadEntityReport;
	
	/**
	 * Tests the asynchronous loading of a {@link LoadedEntitiesReport}
	 * for a specific entity key.
	 *
	 * This test performs the following verifications:
	 * 1.  Asserts that the {@code loadEntityReport} service bean is successfully
	 * autowired by the Spring context and is not null.
	 * 2.  Invokes the asynchronous {@code loadEntityReport(String entityKey)} method
	 * with a predefined entity key (e.g., "artistcredit").
	 * 3.  Waits for the {@link CompletableFuture} to complete within a specified
	 * timeout, retrieving the {@link LoadedEntitiesReport} result.
	 * 4.  Asserts that the retrieved {@link LoadedEntitiesReport} is not null,
	 * indicating that the report generation process completed successfully
	 * and returned a valid report for the given entity key.
	 *
	 * Note: The success of this test heavily relies on the application's
	 * configuration providing a valid strategy for "artistcredit" (or
	 * whatever entity key is used) and the underlying data services
	 * being able to generate a corresponding report.
	 *
	 * @throws InterruptedException if the current thread is interrupted
	 * while waiting for the report to complete.
	 * @throws ExecutionException if the computation threw an exception.
	 * @throws TimeoutException if the wait timed out.
	 */
	@Test
	void test() throws InterruptedException, ExecutionException, TimeoutException {
		// Verify that the LoadEntityReport bean has been successfully autowired by Spring.
		assertNotNull(loadEntityReport, "The LoadEntityReport bean should not be null after autowiring.");
		
		// Call the asynchronous method to load the entity report for a specific key.
		// "artistcredit" is used as an example entity key. Replace with a key relevant
		// to your application's configured CSV strategies and database entities.
		CompletableFuture<LoadedEntitiesReport>  future = loadEntityReport.loadEntityReport("artistcredit");
		
		// Wait for the asynchronous operation to complete and retrieve the result.
		// A timeout of 10 seconds is set to prevent indefinite waiting.
		LoadedEntitiesReport report = future.get(10, TimeUnit.SECONDS);
		
		// Assert that the returned LoadedEntitiesReport is not null,
		// indicating that the process completed successfully and a report was generated.
		assertNotNull(report, "The LoadedEntitiesReport should not be null for a valid entity key.");
	}
	
}
package org.nanotek.service.http.handlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch; // NEW: Import asyncDispatch
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;  // NEW: Import request matchers

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles; // Optional: If you use Spring profiles
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult; // NEW: Import MvcResult

/**
 * Integration test for the {@link CsvLoadedEntityHandler} REST controller using MockMvc.
 * This test runs within a full Spring Boot application context, meaning all beans
 * (including {@link CsvLoadedEntityHandler} and its {@link LoadEntityReport} dependency,
 * and LoadEntityReport's own dependencies) are autowired and their real implementations
 * are used.
 *
 * It simulates an HTTP GET request to the controller's endpoint and asserts the
 * HTTP response, including status, content type, and the presence of a report.
 *
 * IMPORTANT: For this test to pass successfully, your Spring application must be
 * configured such that:
 * 1. All dependencies of {@link LoadEntityReport} (e.g., CsvStrategyCategorizer,
 * DatabaseEntityLoadReportService) are correctly configured as Spring beans.
 * 2. The `CsvStrategyCategorizer` can find a strategy for the hardcoded key
 * "artistcredit" within the `handleRequest` method of `CsvLoadedEntityHandler`.
 * 3. The `DatabaseEntityLoadReportService` can generate a report for that entity
 * within the 10-second timeout.
 * 4. Your application context can load fully without errors.
 */
@SpringBootTest // Loads the full Spring application context
@AutoConfigureMockMvc // Configures MockMvc for testing web controllers
// @ActiveProfiles("test") // Uncomment and configure if you use specific profiles for testing
public class CsvLoadedEntityHandlerIntegrationTest {

    @Autowired
    private MockMvc mockMvc; // Injected MockMvc for making HTTP requests

    /**
     * Tests the GET endpoint for retrieving an entity report asynchronously.
     * This test simulates an HTTP request to `/report/tasks/{entityKey}` and
     * verifies that the response is successful and contains JSON content.
     *
     * To properly test the asynchronous `Callable` return type, `MockMvc`
     * performs an initial request to start the async processing, then dispatches
     * the result once the async task has completed.
     *
     * Note: The `entityKey` passed in the URL path here ("artistcredit") is
     * currently overridden by the hardcoded "artistcredit" inside the controller's
     * `handleRequest` method for the actual report generation.
     *
     * @throws Exception if an error occurs during the MockMvc request or assertion.
     */
    @Test
    void testGetEntityReport_Success() throws Exception {
        // Define the entity key for the URL path.
        String entityKeyInUrl = "artistcredit"; 

        // Step 1: Perform the initial GET request and assert that async processing has started.
        // Capture the MvcResult to dispatch later.
        MvcResult mvcResult = mockMvc.perform(get("/report/tasks/{entityKey}", entityKeyInUrl)
                .accept(MediaType.APPLICATION_JSON_VALUE)) // Request JSON response
                .andExpect(request().asyncStarted()) // Assert that the async request has started
                .andReturn(); // Return the MvcResult for async dispatch

        // Step 2: Perform an asynchronous dispatch to complete the request and apply final assertions.
        // This will wait for the Callable to complete its execution.
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk()) // Expect HTTP 200 OK status after async completion
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Expect JSON content type after async completion
                .andExpect(content().string(org.hamcrest.Matchers.not(org.hamcrest.Matchers.emptyString()))) // Expect non-empty JSON response
                // Further assertions can be added here if you know the exact JSON structure
                // For example: .andExpect(jsonPath("$.reportId").exists())
                ;
    }

    // Additional integration tests could be added here for different scenarios (e.g.,
    // expecting an error status if the internal service throws an exception), but
    // these would require specific configuration of the real application beans
    // to trigger those error conditions.
}
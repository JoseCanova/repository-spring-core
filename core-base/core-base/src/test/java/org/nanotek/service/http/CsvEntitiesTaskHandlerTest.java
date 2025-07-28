package org.nanotek.service.http;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch; // NEW: Import asyncDispatch
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;  // NEW: Import request matchers

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult; // NEW: Import MvcResult

// Configure the full Spring Boot context for the test.
// This requires all dependencies (CsvEntitiesTaskReport, CsvTaskOutcomeReportService,
// CsvProcessingCommandService, CsvParsingTaskProvider, CsvStrategyCategorizer, etc.)
// to be discoverable and properly configured as Spring beans without any mocks.
@Configuration
@ComponentScan(basePackages = {"org.nanotek"}) // Ensure all relevant packages are scanned
class CsvEntitiesTaskHandlerTestConfig {
    // This class sets up the Spring context for the test.
    // It assumes all necessary beans for the full dependency chain are present
    // and properly wired in the 'org.nanotek' package or its sub-packages.
}

@SpringBootTest
@AutoConfigureMockMvc // Automatically configures MockMvc for web layer testing
public class CsvEntitiesTaskHandlerTest {

    @Autowired
    private MockMvc mockMvc; // Injected to perform HTTP requests in a test environment

    @Test
    void testGetTasksEndpointReturnsOkAndJsonStructure() throws Exception {
        // Step 1: Perform the initial GET request and assert that async processing has started.
        // Capture the MvcResult to dispatch later.
        MvcResult mvcResult = mockMvc.perform(get("/report/tasks")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(request().asyncStarted()) // Assert that the async request has started
                // .andExpect(request().asyncResult(notNullValue())) // Optional: assert that async result is not null if you want to check the raw result before dispatch
                .andReturn(); // Return the MvcResult for async dispatch

        // Step 2: Perform an asynchronous dispatch to complete the request and apply final assertions.
        mockMvc.perform(asyncDispatch(mvcResult))
                // Assert that the HTTP status is OK (200) after async completion
                .andExpect(status().isOk())
                
                // Assert that the content type is application/json after async completion
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                
                // Assert that the root element is a JSON array
                .andExpect(jsonPath("$").isArray())
                
                // Assert that the array has exactly two elements (for BaseType and RegularType)
                .andExpect(jsonPath("$.length()").value(2))
                
                // Assert that the first element is a JSON object
                .andExpect(jsonPath("$[0]").isMap())
                
                // Assert that the second element is a JSON object
                .andExpect(jsonPath("$[1]").isMap());

        // Further assertions on specific JSON content (e.g., specific keys or values within the maps)
        // would require a highly predictable configuration of the underlying CsvProcessingCommandService
        // and its dependencies, ensuring they return consistent, known task maps
        // in a non-mocked Spring context. For example, if you know a "task1" with "BaseType"
        // will always be present, you could add:
        // .andExpect(jsonPath("$[0].TaskName").value("BaseType")); // Assuming 'TaskName' is a key in the map
    }
}
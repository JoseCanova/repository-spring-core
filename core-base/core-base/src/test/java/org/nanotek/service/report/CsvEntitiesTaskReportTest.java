package org.nanotek.service.report;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CsvEntitiesTaskReportTest {

    @Autowired
    private CsvEntitiesTaskReport csvEntitiesTaskReport;

    @Test
    void testContextLoadsAndServiceNotNull() {
        // Verify that the Spring context loads and CsvEntitiesTaskReport is wired.
        assertNotNull(csvEntitiesTaskReport, "CsvEntitiesTaskReport should be autowired and not null.");
    }

    @Test
    void testGenerateTasklistCreatesCorrectStructure() {
        // Act
        // Call the new public method that triggers the report generation
        List<?> reportList = csvEntitiesTaskReport.generateTasklist();

        // Assert
        assertNotNull(reportList, "The generated report list should not be null.");
        assertEquals(2, reportList.size(), "The report list should contain two entries (BaseType and RegularType).");

        // Verify the structure and type of the elements in the list
        assertTrue(reportList.get(0) instanceof Map, "First element should be a Map.");
        assertTrue(reportList.get(1) instanceof Map, "Second element should be a Map.");

        // Type cast for more specific assertions and suppress warnings for unchecked cast
        @SuppressWarnings("unchecked")
        Map<String, String> baseTypeMap = (Map<String, String>) reportList.get(0);
        @SuppressWarnings("unchecked")
        Map<String, String> regularTypeMap = (Map<String, String>) reportList.get(1);

        // Assert that the 'type' value is correctly applied for elements in the maps.
        // Without mocks, the actual keys in these maps depend on the real behavior of
        // CsvProcessingCommandService and its dependencies (CsvParsingTaskProvider, CsvStrategyCategorizer).
        // If those services return empty maps (e.g., in a default test setup with no specific tasks configured),
        // then baseTypeMap and regularTypeMap will also be empty, but the foreach loop will still pass.
        baseTypeMap.values().forEach(value -> assertEquals("BaseType", value, "All values in BaseType map should be 'BaseType'."));
        regularTypeMap.values().forEach(value -> assertEquals("RegularType", value, "All values in RegularType map should be 'RegularType'."));

        // To add more specific assertions about map keys and content without mocks,
        // you would need to ensure that the underlying CsvProcessingCommandService
        // and its dependencies (CsvParsingTaskProvider, CsvStrategyCategorizer)
        // are configured in your Spring application context to provide specific,
        // predictable sets of tasks. This often involves defining @Bean methods
        // in your test configuration that return controlled instances or data.
    }
}
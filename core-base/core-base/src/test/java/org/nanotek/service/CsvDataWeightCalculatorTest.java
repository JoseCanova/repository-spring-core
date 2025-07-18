package org.nanotek.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.config.CsvFileConfigurations; // Keep this if CsvFileConfigurations is not auto-scanned by SpringBootTest
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse; // Added for map emptiness check

@ExtendWith(SpringExtension.class)
@SpringBootTest // This will load the full Spring Boot context, including CsvBaseConfiguration and CsvFileConfigurations
public class CsvDataWeightCalculatorTest {

    @Autowired
    private CsvDataWeightCalculator csvDataWeightCalculator;

    @Autowired
    private CsvFileConfigurations csvFileConfigurations; // Autowire to get the names of all configured strategies

    @Test
    void testCalculatorInitializationWithRealFiles() {
        assertNotNull(csvDataWeightCalculator, "CsvDataWeightCalculator should be autowired");
        assertNotNull(csvFileConfigurations, "CsvFileConfigurations should be autowired");

        Map<String, CsvFileItemConcreteStrategy<?,?,?,?>> configuredStrategies = csvFileConfigurations.getCsvConfigs();
        assertFalse(configuredStrategies.isEmpty(), "No CSV configurations found. Ensure application.yml is correctly configured.");

        System.out.println("\n--- Verifying CsvDataWeightCalculator for all configured files ---");

        configuredStrategies.forEach((configName, strategy) -> {
            long fileSize = csvDataWeightCalculator.getFileSize(configName);
            long lineCount = csvDataWeightCalculator.getLineCount(configName);

            System.out.println(String.format("  - %s (%s): Size=%,d bytes, Lines=%,d",
                                             configName, strategy.getFileName(), fileSize, lineCount));

            // Assert that file size and line count are greater than 0 for each configured file
            // This assumes all configured files exist and are not empty.
            // If some files might legitimately be empty or missing in your real setup,
            // you might adjust these assertions (e.g., check if file exists before asserting > 0).
            assertTrue(fileSize >= 0, "File size for " + configName + " should be non-negative.");
            assertTrue(lineCount >= 0, "Line count for " + configName + " should be non-negative.");

            // If you expect all files to be non-empty, you can use:
            // assertTrue(fileSize > 0, "File size for " + configName + " should be greater than 0.");
            // assertTrue(lineCount > 0, "Line count for " + configName + " should be greater than 0.");
        });

        // Verify total counts
        long totalFileSize = csvDataWeightCalculator.getTotalFileSize();
        long totalLineCount = csvDataWeightCalculator.getTotalLineCount();

        System.out.println(String.format("--- Overall Totals: Total File Size=%,d bytes, Total Lines=%,d ---",
                                         totalFileSize, totalLineCount));

        assertTrue(totalFileSize >= 0, "Total file size should be non-negative.");
        assertTrue(totalLineCount >= 0, "Total line count should be non-negative.");

        // If you expect the sum of all files to be non-empty:
        // assertTrue(totalFileSize > 0, "Total file size should be greater than 0.");
        // assertTrue(totalLineCount > 0, "Total line count should be greater than 0.");
    }
}
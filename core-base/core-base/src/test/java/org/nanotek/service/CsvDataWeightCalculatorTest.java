package org.nanotek.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.config.CsvFileConfigurations;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.nanotek.opencsv.service.CsvDataWeightCalculator3;
import org.nanotek.opencsv.service.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CsvDataWeightCalculatorTest {

    @Autowired
    private CsvDataWeightCalculator3 csvDataWeightCalculator;

    @Autowired
    private CsvFileConfigurations csvFileConfigurations; // Still useful for getting config names and strategy details

    private static final long ONE_KB = 1024;
    private static final long ONE_MB = ONE_KB * 1024;
    private static final long ONE_GB = ONE_MB * 1024;

    /**
     * Converts bytes to a human-readable format (KB, MB, GB).
     *
     * @param bytes The size in bytes.
     * @return A formatted string (e.g., "1.23 MB").
     */
    private String formatBytes(long bytes) {
        if (bytes < ONE_KB) {
            return bytes + " bytes";
        } else if (bytes < ONE_MB) {
            return String.format("%.2f KB", (double) bytes / ONE_KB);
        } else if (bytes < ONE_GB) {
            return String.format("%.2f MB", (double) bytes / ONE_MB);
        } else {
            return String.format("%.2f GB", (double) bytes / ONE_GB);
        }
    }

    @Test
    void generatePhysicalWeightReport() {
        assertNotNull(csvDataWeightCalculator, "CsvDataWeightCalculator should be autowired.");
        assertNotNull(csvFileConfigurations, "CsvFileConfigurations should be autowired.");
        csvDataWeightCalculator.initialize();
        // --- CRITICAL CHANGE: Explicitly call the calculation method ---
        Map<String, Pair<Long, Long>> allFileWeights = csvDataWeightCalculator.calculateAllFileWeights();
        // --- END CRITICAL CHANGE ---

        assertFalse(allFileWeights.isEmpty(), "No file weights calculated. Ensure application.yml is correctly configured and files exist.");

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US); // For comma separation

        StringBuilder report = new StringBuilder();
        report.append("\n======================================================\n");
        report.append("          CSV Data Physical Weight Report           \n");
        report.append("======================================================\n\n");

        report.append(String.format("%-25s %-15s %-15s %s\n", "Configuration Name", "File Size", "Line Count", "File Path"));
        report.append(String.format("%-25s %-15s %-15s %s\n", "-------------------------", "---------------", "---------------", "--------------------------------------------------------"));

        // Iterate through the calculated weights map
        allFileWeights.forEach((configName, weightPair) -> {
            long fileSize = weightPair.getFirst();
            long lineCount = weightPair.getSecond();
            String formattedFileSize = formatBytes(fileSize);
            String formattedLineCount = numberFormat.format(lineCount);

            // Retrieve the strategy from csvFileConfigurations to get the full path
            // This assumes that all configName keys in allFileWeights also exist in csvFileConfigurations.getCsvConfigs()
            String fileLocation = "N/A";
            String fileName = "N/A";
            Map<String , CsvFileItemConcreteStrategy<?, ?,?,?>> strategies =  csvFileConfigurations.getCsvConfigs();
            if (csvFileConfigurations.getCsvConfigs().containsKey(configName)) {
                fileLocation = strategies.get(configName).getFileLocation();
                fileName = strategies.get(configName).getFileName();
            }


            report.append(String.format("%-25s %-15s %-15s %s/%s\n",
                                         configName,
                                         formattedFileSize,
                                         formattedLineCount,
                                         fileLocation,
                                         fileName));

            // Assertions for individual files:
            assertTrue(fileSize >= 0, "File size for " + configName + " should be non-negative.");
            assertTrue(lineCount >= 0, "Line count for " + configName + " should be non-negative.");
        });

        report.append(String.format("\n%-25s %-15s %-15s %s\n", "-------------------------", "---------------", "---------------", "--------------------------------------------------------"));

        // Calculate totals from the allFileWeights map
        long totalFileSize = allFileWeights.values().stream().mapToLong(Pair::getFirst).sum();
        long totalLineCount = allFileWeights.values().stream().mapToLong(Pair::getSecond).sum();

        report.append(String.format("%-25s %-15s %-15s %s\n",
                                     "TOTALS:",
                                     formatBytes(totalFileSize),
                                     numberFormat.format(totalLineCount),
                                     ""));

        report.append("\n======================================================\n");

        // Print the report to the console
        System.out.println(report.toString());

        // Assertions for overall totals:
        assertTrue(totalFileSize >= 0, "Total file size should be non-negative.");
        assertTrue(totalLineCount >= 0, "Total line count should be non-negative.");

        // If you expect the sum of all files to be non-empty:
        // assertTrue(totalFileSize > 0, "Total file size should be greater than 0.");
        // assertTrue(totalLineCount > 0, "Total line count should be greater than 0.");
    }
}
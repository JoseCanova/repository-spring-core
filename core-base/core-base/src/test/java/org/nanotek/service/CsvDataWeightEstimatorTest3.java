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
public class CsvDataWeightEstimatorTest3 {

    @Autowired
    private CsvDataWeightCalculator3 csvDataWeightCalculator;

    @Autowired
    private CsvFileConfigurations csvFileConfigurations;

    private static final long ONE_KB = 1024;
    private static final long ONE_MB = ONE_KB * 1024;
    private static final long ONE_GB = ONE_MB * 1024;
    private static final int ESTIMATION_SAMPLE_SIZE = 2000; // Define the sample size for estimation

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
    void generateEstimatedPhysicalWeightReport() {
        assertNotNull(csvDataWeightCalculator, "CsvDataWeightCalculator should be autowired.");
        assertNotNull(csvFileConfigurations, "CsvFileConfigurations should be autowired.");

        // --- CRITICAL CHANGE: Call the new estimation method ---
        Map<String, Pair<Long, Long>> allFileEstimatedWeights =
                csvDataWeightCalculator.calculateAllFileEstimatedWeights(ESTIMATION_SAMPLE_SIZE);
        // --- END CRITICAL CHANGE ---

        assertFalse(allFileEstimatedWeights.isEmpty(), "No file weights estimated. Ensure application.yml is correctly configured and files exist.");

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US); // For comma separation

        StringBuilder report = new StringBuilder();
        report.append("\n======================================================\n");
        report.append("       CSV Data Estimated Physical Weight Report      \n");
        report.append(String.format("         (Based on %d-line sample for line count)         \n", ESTIMATION_SAMPLE_SIZE));
        report.append("======================================================\n\n");

        report.append(String.format("%-25s %-15s %-15s %s\n", "Configuration Name", "File Size", "Est. Line Count", "File Path"));
        report.append(String.format("%-25s %-15s %-15s %s\n", "-------------------------", "---------------", "---------------", "--------------------------------------------------------"));

        // Iterate through the estimated weights map
        allFileEstimatedWeights.forEach((configName, weightPair) -> {
            long fileSize = weightPair.getFirst();
            long estimatedLineCount = weightPair.getSecond();
            String formattedFileSize = formatBytes(fileSize);
            String formattedLineCount = numberFormat.format(estimatedLineCount);

            // Retrieve the strategy from csvFileConfigurations to get the full path
            Map<String, CsvFileItemConcreteStrategy<?, ?, ?, ?>> strategies = csvFileConfigurations.getCsvConfigs();
            String fileLocation = "N/A";
            String fileName = "N/A";
            if (strategies.containsKey(configName)) {
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
            assertTrue(estimatedLineCount >= 0, "Estimated line count for " + configName + " should be non-negative.");
        });

        report.append(String.format("\n%-25s %-15s %-15s %s\n", "-------------------------", "---------------", "---------------", "--------------------------------------------------------"));

        // Calculate totals from the estimated weights map
        long totalFileSize = allFileEstimatedWeights.values().stream().mapToLong(Pair::getFirst).sum();
        long totalEstimatedLineCount = allFileEstimatedWeights.values().stream().mapToLong(Pair::getSecond).sum();

        report.append(String.format("%-25s %-15s %-15s %s\n",
                                     "TOTALS:",
                                     formatBytes(totalFileSize),
                                     numberFormat.format(totalEstimatedLineCount),
                                     ""));

        report.append("\n======================================================\n");

        // Print the report to the console
        System.out.println(report.toString());

        // Assertions for overall totals:
        assertTrue(totalFileSize >= 0, "Total file size should be non-negative.");
        assertTrue(totalEstimatedLineCount >= 0, "Total estimated line count should be non-negative.");
    }
}

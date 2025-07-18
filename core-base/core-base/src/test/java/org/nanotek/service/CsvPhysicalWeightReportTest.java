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
import org.nanotek.opencsv.service.CsvDataWeightCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CsvPhysicalWeightReportTest {

    @Autowired
    private CsvDataWeightCalculator csvDataWeightCalculator;

    @Autowired
    private CsvFileConfigurations csvFileConfigurations;

    private static final long ONE_KB = 1024;
    private static final long ONE_MB = ONE_KB * 1024;
    private static final long ONE_GB = ONE_MB * 1024;

    /**
     * Converts bytes to a human-readable format (KB, MB, GB).
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

        Map<String, CsvFileItemConcreteStrategy<?,?,?,?>> configuredStrategies = csvFileConfigurations.getCsvConfigs();
        assertFalse(configuredStrategies.isEmpty(), "No CSV configurations found. Cannot generate report.");

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US); // For comma separation

        StringBuilder report = new StringBuilder();
        report.append("\n======================================================\n");
        report.append("          CSV Data Physical Weight Report           \n");
        report.append("======================================================\n\n");

        report.append(String.format("%-25s %-15s %-15s %s\n", "Configuration Name", "File Size", "Line Count", "File Path"));
        report.append(String.format("%-25s %-15s %-15s %s\n", "-------------------------", "---------------", "---------------", "--------------------------------------------------------"));

        configuredStrategies.forEach((configName, strategy) -> {
            long fileSize = csvDataWeightCalculator.getFileSize(configName);
            long lineCount = csvDataWeightCalculator.getLineCount(configName);
            String formattedFileSize = formatBytes(fileSize);
            String formattedLineCount = numberFormat.format(lineCount);

            report.append(String.format("%-25s %-15s %-15s %s/%s\n",
                                         configName,
                                         formattedFileSize,
                                         formattedLineCount,
                                         strategy.getFileLocation(),
                                         strategy.getFileName()));
        });

        report.append(String.format("\n%-25s %-15s %-15s %s\n", "-------------------------", "---------------", "---------------", "--------------------------------------------------------"));

        long totalFileSize = csvDataWeightCalculator.getTotalFileSize();
        long totalLineCount = csvDataWeightCalculator.getTotalLineCount();

        report.append(String.format("%-25s %-15s %-15s %s\n",
                                     "TOTALS:",
                                     formatBytes(totalFileSize),
                                     numberFormat.format(totalLineCount),
                                     ""));

        report.append("\n======================================================\n");

        // Print the report to the console
        System.out.println(report.toString());

        // Assertions to ensure the test passes (minimal, as the goal is output)
        assertTrue(totalFileSize >= 0);
        assertTrue(totalLineCount >= 0);
    }
}
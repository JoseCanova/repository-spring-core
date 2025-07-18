package org.nanotek.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.config.CsvFileConfigurations;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy; // Import for explicit type
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AreaCsvDataWeightCalculatorTest {

    @Autowired
    private CsvDataWeightCalculator csvDataWeightCalculator;

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
    void testAreaCsvFileWeightCalculation() {
        assertNotNull(csvDataWeightCalculator, "CsvDataWeightCalculator should be autowired.");
        assertNotNull(csvFileConfigurations, "CsvFileConfigurations should be autowired.");

        // Call the new calculateFileWeight method for 'area'
        Pair<Long, Long> areaWeightPair = csvDataWeightCalculator.calculateFileWeight("area");

        assertNotNull(areaWeightPair, "Weight pair for 'area' should not be null. Check if 'area' config exists.");

        long areaFileSize = areaWeightPair.getFirst();
        long areaLineCount = areaWeightPair.getSecond();

        // --- CRITICAL CHANGE: Assign to a local variable with explicit generics for clarity and LSP ---
        Map<String, CsvFileItemConcreteStrategy<?, ?, ?, ?>> strategies = csvFileConfigurations.getCsvConfigs();

        String fileLocation = "N/A";
        String fileName = "N/A";
        // Ensure the 'area' config actually exists in the loaded configurations
        if (strategies.containsKey("area")) {
            fileLocation = strategies.get("area").getFileLocation(); // Now correctly typed
            fileName = strategies.get("area").getFileName();         // Now correctly typed
        }
        // --- END CRITICAL CHANGE ---

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

        StringBuilder report = new StringBuilder();
        report.append("\n======================================================\n");
        report.append("          Area CSV Data Weight Report           \n");
        report.append("======================================================\n\n");

        report.append(String.format("%-25s %-15s %-15s %s\n", "Configuration Name", "File Size", "Line Count", "File Path"));
        report.append(String.format("%-25s %-15s %-15s %s\n", "-------------------------", "---------------", "---------------", "--------------------------------------------------------"));

        report.append(String.format("%-25s %-15s %-15s %s/%s\n",
                                     "area",
                                     formatBytes(areaFileSize),
                                     numberFormat.format(areaLineCount),
                                     fileLocation,
                                     fileName));

        report.append("\n======================================================\n");

        // Print the report to the console
        System.out.println(report.toString());

        // Assertions for 'area' specific file:
        assertTrue(areaFileSize > 0, "File size for 'area' should be greater than 0.");
        assertTrue(areaLineCount > 0, "Line count for 'area' should be greater than 0.");
    }
}
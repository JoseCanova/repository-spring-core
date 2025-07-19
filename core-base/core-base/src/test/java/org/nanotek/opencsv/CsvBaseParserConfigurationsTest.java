package org.nanotek.opencsv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.BaseException; // Import BaseException
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.io.IOException; // Import IOException
import java.util.List; // Import List
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail; // Import fail for explicit test failure

// This annotation loads the full Spring Boot application context for the test.
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CsvBaseParserConfigurationsTest<M extends BaseMap<K,I,B>,
K extends AnyBase<K,String> ,
I extends AnyBase<I,Integer>
, B extends BaseBean<?,?>> {

    // Autowire the component under test
    @Autowired
    private CsvBaseParserConfigurations<M,K,I,B> csvBaseParserConfigurations;

    /**
     * This test verifies that the CsvBaseParserConfigurations component correctly
     * loads and initializes all CsvFileItemConcreteStrategy beans configured
     * in the application.yml.
     *
     * It checks if the strategies' internal readers are set up, which implies
     * their `afterPropertiesSet()` method was called during the initialization
     * within CsvBaseParserConfigurations.
     *
     * IMPORTANT: For this test to pass meaningfully, your application.yml
     * must define at least one CsvFileItemConcreteStrategy bean.
     * For example:
     *
     * csv:
     * configurations:
     * areatype:
     * filePath: "classpath:/csv/areatype.csv"
     * # ... other properties for AreaTypeStrategy
     *
     * And your CsvFileItemConcreteStrategy implementation for 'areatype'
     * must correctly set up its BufferedReader when afterPropertiesSet() is called.
     */
    @Test
    void testStrategiesAreCorrectlyInitialized() {
        assertNotNull(csvBaseParserConfigurations, "CsvBaseParserConfigurations should be autowired by Spring.");

        Map<String, CsvFileItemConcreteStrategy<M,K,I,B>> strategies =
                csvBaseParserConfigurations.getCsvStrategies();

        assertFalse(strategies.isEmpty(), "Expected CSV strategies to be loaded from application.yml for this test.");

        System.out.println("\n--- CsvBaseParserConfigurations Test: Initialized Strategies Status ---");

        // Iterate through all strategies and verify their BufferedReader is not null
        boolean allReadersInitialized = true;
        String firstStrategyName = null; // Store a name to pick one for a specific check

        for (Map.Entry<String, CsvFileItemConcreteStrategy<M,K,I,B>> entry : strategies.entrySet()) {
            String strategyName = entry.getKey();
            CsvFileItemConcreteStrategy<M,K,I,B> strategy = entry.getValue();

            if (firstStrategyName == null) {
                firstStrategyName = strategyName; // Capture the first strategy name for a specific assertion
            }

            BufferedReader reader = strategy.getCSVReader();
            if (reader != null) {
                System.out.println(String.format("  - Strategy '%s': CSVReader is initialized (OK)", strategyName));
            } else {
                System.err.println(String.format("  - Strategy '%s': CSVReader is NULL (ERROR)", strategyName));
                allReadersInitialized = false;
            }
        }

        assertTrue(allReadersInitialized, "All CSV strategies' BufferedReaders should be initialized.");

        // Optional: Pick a specific strategy (e.g., "areatype") if you know it's configured
        // Replace "areatype" with an actual strategy name from your application.yml
        String knownStrategyName = "areatype"; // Example: assuming 'areatype' is configured

        Optional<CsvFileItemConcreteStrategy<M,K,I,B>> specificStrategyOpt =
                csvBaseParserConfigurations.getStrategy(knownStrategyName);

        assertTrue(specificStrategyOpt.isPresent(), String.format("Expected strategy '%s' to be found.", knownStrategyName));
        assertNotNull(specificStrategyOpt.get().getCSVReader(), String.format("CSVReader for strategy '%s' should not be null.", knownStrategyName));

        System.out.println("\n--- CsvBaseParserConfigurations Test: Complete ---");
    }

    /**
     * This test case verifies that if no CSV configurations are present in application.yml,
     * the CsvBaseParserConfigurations component still initializes correctly with an empty map.
     *
     * To run this test effectively, you might need to use a separate Spring profile
     * or a `@TestPropertySource` annotation to simulate an empty configuration.
     * Otherwise, it will just run against your default application.yml.
     */
    @Test
    void testWithEmptyConfigurations() {
        // This test's behavior depends on whether your application.yml actually provides configs.
        // If it does, this test will effectively just confirm the map is not empty.
        // If you want to simulate truly empty configs, you'd need a test profile.
        System.out.println("\n--- CsvBaseParserConfigurations Test: Empty Configurations Scenario ---");
        Map<String, CsvFileItemConcreteStrategy<M,K,I,B>> strategies =
                csvBaseParserConfigurations.getCsvStrategies();

        // If your application.yml has configs, this will be false.
        // If you run with a profile that has NO csv.configurations, this will be true.
        System.out.println("Are strategies empty? " + strategies.isEmpty());
        // Assert that the map is not null, even if empty
        assertNotNull(strategies, "Strategies map should not be null.");
        // If you specifically want to assert it's empty, uncomment the line below and ensure no configs are loaded.
        // assertTrue(strategies.isEmpty(), "Strategies map should be empty if no configurations are provided.");
        System.out.println("--- CsvBaseParserConfigurations Test: Complete ---");
    }

    /**
     * This new test method verifies the CsvBaseParser's ability to read a CSV file
     * and counts the lines, performing an integrity check.
     *
     * IMPORTANT: You MUST replace `EXPECTED_AREATYPE_LINES` with the actual number
     * of lines (excluding header, if any, based on how your CsvBaseParser consumes)
     * in your `areatype.csv` file.
     * Example: If 'areatype.csv' has 5 data lines and no header consumed by parser, set this to 5.
     */
    @Test
    void testCsvFileReadingAndLineCount() {
        System.out.println("\n--- CsvBaseParserConfigurations Test: CSV File Reading and Line Count Check ---");

        String strategyToTest = "areatype"; // The strategy name for the file we want to read
        // TODO: IMPORTANT: Replace 100 with the actual expected number of data lines in your 'areatype.csv' file.
        // Adjust this value based on your specific 'areatype.csv' content and how headers are handled.
        final int EXPECTED_AREATYPE_LINES = 9; 

        // 1. Retrieve the specific strategy from the configurations
        Optional<CsvFileItemConcreteStrategy<M, K, I, B>> specificStrategyOpt =
                csvBaseParserConfigurations.getStrategy(strategyToTest);

        assertTrue(specificStrategyOpt.isPresent(), String.format("Expected strategy '%s' to be found for reading test.", strategyToTest));
        CsvFileItemConcreteStrategy<M, K, I, B> strategy = specificStrategyOpt.get();

        // Ensure the BufferedReader is ready
        assertNotNull(strategy.getCSVReader(), String.format("CSVReader for strategy '%s' must be initialized before parsing.", strategyToTest));

        // 2. Create an instance of CsvBaseParser with the retrieved strategy
        CsvBaseParser<M, K, I, B> parser = new CsvBaseParser<>(strategy);

        int lineCount = 0;
        List<?> lineData;

        try {
            // 3. Iterate over the lines using the parser
            while ((lineData = parser.readNext()) != null) {
                lineCount++;
                // Optional: You can add assertions here to check content of a line
                // For example: assertNotNull(lineData, "Parsed line data should not be null.");
                // assertFalse(lineData.isEmpty(), "Parsed line data should not be empty.");
            }
        } catch (BaseException ex) {
            fail(String.format("Error reading CSV file for strategy '%s': %s", strategyToTest, ex.getMessage()), ex);
        } finally {
            // It's good practice to close the BufferedReader, although Spring/resource management
            // might handle it if CsvFileItemConcreteStrategy opens it as a managed resource.
            // For this test, we assume the reader is managed by the strategy and might be closed
            // automatically or upon context shutdown. Direct closing here might break subsequent tests
            // if the same reader instance is expected to be used.
        }

        System.out.println(String.format("  - Strategy '%s': Read %d lines.", strategyToTest, lineCount));

        // 4. Verify the line count for integrity check
        assertTrue(lineCount > 0, String.format("Expected to read more than 0 lines from '%s.csv'", strategyToTest));
        assertTrue(lineCount == EXPECTED_AREATYPE_LINES,
                   String.format("Line count for '%s.csv' mismatch. Expected %d, but got %d. Please adjust EXPECTED_AREATYPE_LINES if correct.",
                                 strategyToTest, EXPECTED_AREATYPE_LINES, lineCount));

        System.out.println("--- CsvBaseParserConfigurations Test: CSV File Reading and Line Count Check Complete ---");
    }
}
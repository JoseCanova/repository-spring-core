package org.nanotek.opencsv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.BaseException;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// The generic type parameters for the test class itself
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CsvBaseParserConfigurationsTest<M extends BaseMap<K,I,B>,
K extends AnyBase<K,String> ,
I extends AnyBase<I,Integer>
, B extends BaseBean<?,?>> {

    // Autowire the component under test using the test class's generic parameters
    @Autowired
    private CsvBaseParserConfigurations<M,K,I,B> csvBaseParserConfigurations;

    /**
     * This test verifies that the CsvBaseParserConfigurations component correctly
     * loads and initializes all CsvBaseParser instances, ensuring their underlying
     * CsvFileItemConcreteStrategy's BufferedReader is set up.
     *
     * IMPORTANT: For this test to pass meaningfully, your application.yml
     * must define at least one CsvFileItemConcreteStrategy bean.
     */
    @Test
    void testParsersAreCorrectlyInitialized() {
        assertNotNull(csvBaseParserConfigurations, "CsvBaseParserConfigurations should be autowired by Spring.");

        Map<String, CsvBaseParser<M,K,I,B>> parsers =
                csvBaseParserConfigurations.getCsvStrategies(); // Now returns Map<String, CsvBaseParser>

        assertFalse(parsers.isEmpty(), "Expected CSV parser strategies to be loaded from application.yml for this test.");

        System.out.println("\n--- CsvBaseParserConfigurations Test: Initialized Parsers Status ---");

        boolean allReadersInitialized = true;

        for (Map.Entry<String, CsvBaseParser<M,K,I,B>> entry : parsers.entrySet()) {
            String strategyName = entry.getKey();
            CsvBaseParser<M,K,I,B> parser = entry.getValue(); // Get the CsvBaseParser instance

            // Now, we need to get the underlying CsvFileItemConcreteStrategy from the parser
            CsvFileItemConcreteStrategy<M,K,I,B> strategy = parser.getBaseMapColumnStrategy();
            assertNotNull(strategy, String.format("CsvFileItemConcreteStrategy for parser '%s' should not be null.", strategyName));

            BufferedReader reader = strategy.getCSVReader();
            if (reader != null) {
                System.out.println(String.format("  - Parser '%s': CSVReader is initialized (OK)", strategyName));
            } else {
                System.err.println(String.format("  - Parser '%s': CSVReader is NULL (ERROR)", strategyName));
                allReadersInitialized = false;
            }
        }

        assertTrue(allReadersInitialized, "All CSV parser's underlying BufferedReaders should be initialized.");

        // Optional: Pick a specific parser (e.g., "areatype") if you know it's configured
        String knownStrategyName = "areatype"; // Example: assuming 'areatype' is configured

        Optional<CsvBaseParser<M,K,I,B>> specificParserOpt =
                csvBaseParserConfigurations.getStrategy(knownStrategyName); // Now returns Optional<CsvBaseParser>

        assertTrue(specificParserOpt.isPresent(), String.format("Expected parser '%s' to be found.", knownStrategyName));
        assertNotNull(specificParserOpt.get().getBaseMapColumnStrategy().getCSVReader(),
                      String.format("CSVReader for parser '%s' should not be null.", knownStrategyName));

        System.out.println("\n--- CsvBaseParserConfigurations Test: Complete ---");
    }

    /**
     * This test case verifies that if no CSV configurations are present in application.yml,
     * the CsvBaseParserConfigurations component still initializes correctly with an empty map.
     */
    @Test
    void testWithEmptyConfigurations() {
        System.out.println("\n--- CsvBaseParserConfigurations Test: Empty Configurations Scenario ---");
        Map<String, CsvBaseParser<M,K,I,B>> parsers =
                csvBaseParserConfigurations.getCsvStrategies();

        System.out.println("Are parsers empty? " + parsers.isEmpty());
        assertNotNull(parsers, "Parsers map should not be null.");
        System.out.println("--- CsvBaseParserConfigurations Test: Complete ---");
    }

    /**
     * This new test method verifies the CsvBaseParser's ability to read a CSV file
     * and counts the lines, performing an integrity check.
     *
     * IMPORTANT: The `EXPECTED_AREATYPE_LINES` constant has been updated to 9.
     */
    @Test
    void testCsvFileReadingAndLineCount() {
        System.out.println("\n--- CsvBaseParserConfigurations Test: CSV File Reading and Line Count Check ---");

        String strategyToTest = "areatype";
        final int EXPECTED_AREATYPE_LINES = 9; // Confirmed by user

        // 1. Retrieve the specific CsvBaseParser instance from the configurations
        // It's already created and stored in the map by CsvBaseParserConfigurations
        Optional<CsvBaseParser<M, K, I, B>> parserOpt =
                csvBaseParserConfigurations.getStrategy(strategyToTest);

        assertTrue(parserOpt.isPresent(), String.format("Expected parser for strategy '%s' to be found.", strategyToTest));
        CsvBaseParser<M, K, I, B> parser = parserOpt.get();

        // Ensure the BufferedReader inside the parser's strategy is ready
        assertNotNull(parser.getCSVReader(),
                      String.format("CSVReader for parser '%s' must be initialized before parsing.", strategyToTest));

        int lineCount = 0;
        List<?> lineData;

        try {
            // 2. Iterate over the lines using the retrieved parser
            while ((lineData = parser.readNext()) != null) {
                lineCount++;
            }
        } catch (BaseException ex) {
            fail(String.format("Error reading CSV file for parser '%s': %s", strategyToTest, ex.getMessage()), ex);
        }

        System.out.println(String.format("  - Parser '%s': Read %d lines.", strategyToTest, lineCount));

        // 3. Verify the line count for integrity check
        assertTrue(lineCount > 0, String.format("Expected to read more than 0 lines from '%s.csv'", strategyToTest));
        assertTrue(lineCount == EXPECTED_AREATYPE_LINES,
                   String.format("Line count for '%s.csv' mismatch. Expected %d, but got %d.",
                                 strategyToTest, EXPECTED_AREATYPE_LINES, lineCount));

        System.out.println("--- CsvBaseParserConfigurations Test: CSV File Reading and Line Count Check Complete ---");
    }
}
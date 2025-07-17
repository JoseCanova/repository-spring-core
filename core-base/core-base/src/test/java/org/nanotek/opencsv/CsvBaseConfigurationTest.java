package org.nanotek.opencsv;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse; // Assuming you want to keep this from previous test

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.opencsv.BaseParser; // Import BaseParser
import org.nanotek.opencsv.CsvBaseProcessor; // Import CsvBaseProcessor
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import; // Import the @Import annotation
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
// Use @Import to explicitly load CsvBaseConfiguration for this test context.
// This ensures only the beans defined in CsvBaseConfiguration (and its dependencies) are loaded.
@Import(CsvBaseConfiguration2.class)
public class CsvBaseConfigurationTest { // Renamed test class for clarity, you can keep original if you prefer

    // Autowire the maps you want to test from CsvBaseConfiguration
    @Autowired
    Map<String, CsvFileItemConcreteStrategy<?,?,?,?>> allCsvFileItemConcreteStrategies;

    @Autowired
    Map<String, BaseParser<?,?,?,?>> baseParserMap;

    @Autowired
    Map<String, CsvBaseProcessor<?,?,?,?,?>> csvBaseProcessorMap;

    @Test
    void testCsvBaseConfigurationConformity() {
        // Test allCsvFileItemConcreteStrategies map
        assertNotNull(allCsvFileItemConcreteStrategies, "allCsvFileItemConcreteStrategies map should not be null");
        assertFalse(allCsvFileItemConcreteStrategies.isEmpty(), "allCsvFileItemConcreteStrategies map should not be empty");
        System.out.println("allCsvFileItemConcreteStrategies map size: " + allCsvFileItemConcreteStrategies.size());

        // Test baseParserMap
        assertNotNull(baseParserMap, "baseParserMap should not be null");
        assertFalse(baseParserMap.isEmpty(), "baseParserMap should not be empty");
        System.out.println("baseParserMap size: " + baseParserMap.size());

        // Test csvBaseProcessorMap
        assertNotNull(csvBaseProcessorMap, "csvBaseProcessorMap should not be null");
        assertFalse(csvBaseProcessorMap.isEmpty(), "csvBaseProcessorMap should not be empty");
        System.out.println("csvBaseProcessorMap size: " + csvBaseProcessorMap.size());

        // You can add more specific assertions here, e.g.,
        // - Check if specific keys (e.g., "artist", "area") exist in the maps
        // - Verify properties of a specific BaseParser or CsvBaseProcessor instance
        // For example:
        // CsvBaseProcessor<?,?,?,?,?> artistProcessor = csvBaseProcessorMap.get("artist");
        // assertNotNull(artistProcessor, "Artist processor should be present");
        // assertNotNull(artistProcessor.getBaseParser(), "Artist processor should have a parser");
        // assertNotNull(artistProcessor.getBaseParser().getBaseMapColumnStrategy(), "Artist parser should have a strategy");
        // System.out.println("Artist processor parser strategy file name: " + artistProcessor.getBaseParser().getBaseMapColumnStrategy().getFileName());
    }
}

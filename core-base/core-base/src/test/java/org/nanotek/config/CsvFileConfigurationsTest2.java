package org.nanotek.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy; // Corrected import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CsvFileConfigurationsTest2 {

	@Autowired
	CsvFileConfigurations csvFileConfigurations; // This will now hold the map of strategies

	@Test
	void testCsvFileConfigurations() {
		assertNotNull(csvFileConfigurations);
		Map<String, CsvFileItemConcreteStrategy<?,?,?,?>> csvConfigs = csvFileConfigurations.getCsvConfigs();
		assertNotNull(csvConfigs);
		assertFalse(csvConfigs.isEmpty(), "csvConfigs map should not be empty");

		// Verify that each strategy in the map has its properties bound
		csvConfigs.forEach((name, strategy) -> {
			System.out.println("Verifying strategy: " + name);
			assertNotNull(strategy.getFileLocation(), "fileLocation should not be null for " + name);
			assertNotNull(strategy.getFileName(), "fileName should not be null for " + name);
			assertNotNull(strategy.getImmutable(), "immutable should not be null for " + name);
			assertNotNull(strategy.getBaseMap(), "baseMap should not be null for " + name);
			assertFalse(strategy.getBaseMap().isEmpty(), "baseMap should not be empty for " + name);
			//strategy.processFile(); // Demonstrate that the strategy is configured and callable
		});
	}
}
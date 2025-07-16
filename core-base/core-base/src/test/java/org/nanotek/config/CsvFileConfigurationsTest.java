package org.nanotek.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CsvFileConfigurationsTest {

	
	@Autowired
	CsvFileConfigurations csvFileConfigurations;
	
	@Test
	void testCsvFileConfigurations() {
		assertNotNull(csvFileConfigurations);
		 Map<String, CsvFileItemConcreteStrategy<?,?,?,?>> csvConfigs = csvFileConfigurations.getCsvConfigs();
		 csvConfigs
		 	.values()
		 	.stream()
		 	.forEach(
		 		strategy ->{
		 				strategy.afterPropertiesSet();
		 	});
			assertNotNull(csvFileConfigurations);
		 }
}

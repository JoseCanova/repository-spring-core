package org.nanotek.service.report;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoadedEntitiesCsvReportServiceTest {

	@Autowired
	LoadedEntitiesCsvReportService loadedEntitiesCsvReportService;
	
	@Test
	void test() {
		assertNotNull(loadedEntitiesCsvReportService);
		String reportResult = loadedEntitiesCsvReportService.generateReport();
		assertTrue(!reportResult.isEmpty());
		System.err.println(reportResult);
	}

}

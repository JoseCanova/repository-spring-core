package org.nanotek.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.nanotek.opencsv.service.CsvStrategyCategorizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Integration test class for {@link CsvStrategyCategorizer}.
 * This test uses a Spring Boot application context to autowire the
 * CsvStrategyCategorizer service and verifies the behavior of its
 * categorization logic, specifically focusing on the relationship
 * between all strategies, basetype strategies, and regular strategies.
 * It implicitly confirms that strategies are correctly partitioned
 * into disjoint sets.
 */
@SpringBootTest
public class CsvStrategyCategorizerAllStrategiesTest {

	/**
	 * The CsvStrategyCategorizer service, autowired by the Spring Boot test context.
	 * The generic types are wildcarded as this test focuses on the structural
	 * aspects of categorization rather than specific type interactions.
	 */
	@Autowired 
	CsvStrategyCategorizer<?,?,?,?> csvStrategyCategorizer;
	
	/**
	 * Tests the categorization logic within the {@link CsvStrategyCategorizer}
	 * by comparing the total count of all strategies against the sum of
	 * basetype and regular strategies.
	 * * This test verifies:
	 * 1. The {@code csvStrategyCategorizer} bean is successfully autowired.
	 * 2. The {@code categorizeStrategies()} method returns a non-null result.
	 * 3. The sum of strategies in the 'basetype' and 'regular' categories
	 * is equal to the total number of 'all' strategies. This implicitly
	 * confirms that the strategies are correctly partitioned into two
	 * mutually exclusive (disjoint) sets, and no strategy is duplicated
	 * or missing during the categorization process.
	 */
	@Test
	void testCsvStrategyCategorizerForAllStrategies() {
		// Assert that the categorizer bean was successfully autowired by Spring
		assertNotNull(csvStrategyCategorizer, "CsvStrategyCategorizer should not be null after autowiring.");
		
		// Perform the strategy categorization and retrieve the different views
		// of the categorized strategies.
		Map<?,?> all = csvStrategyCategorizer.categorizeStrategies().allStrategies();
		Map<?,?> base = csvStrategyCategorizer.categorizeStrategies().basetypeStrategies();
		Map<?,?> regular = csvStrategyCategorizer.categorizeStrategies().regularStrategies();
		
		// Assert that the total number of strategies is equal to the sum of
		// basetype and regular strategies. This confirms the correct partitioning.
		assertTrue(all.size() == base.size() + regular.size(), 
				"The total number of strategies should equal the sum of basetype and regular strategies, " +
				"indicating proper partitioning without overlaps or omissions.");
	}
}
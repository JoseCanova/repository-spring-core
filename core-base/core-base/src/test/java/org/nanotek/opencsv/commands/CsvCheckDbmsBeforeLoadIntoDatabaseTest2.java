package org.nanotek.opencsv.commands;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional; // For querying existence
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.nanotek.AnyBase;
import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseEntity;
import org.nanotek.beans.entity.BrainzBaseEntity; // Assuming BrainzBaseEntity exists and is a base for your entities
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.nanotek.opencsv.service.CategorizedCsvStrategies;
import org.nanotek.opencsv.service.CsvStrategyCategorizer; // Assuming this service exists
import org.nanotek.opencsv.service.LoadedEntitiesReport;
import org.nanotek.service.jpa.BrainzPersistenceService; // The attached BrainzPersistenceService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;


/**
 * Purpose of this test:
 * This integration test class is designed to verify the state of entities in the database
 * before a new CSV load operation. Its primary goal is to ensure data integrity and prevent
 * redundant loading or inconsistencies by checking if a specific entity (or set of entities)
 * as defined in the BrainzGraphModel (or similar domain model) has already been fully
 * processed and loaded into the relational database. This helps in managing incremental
 * loads and validating the existing data state.
 */
@SpringBootTest
public class CsvCheckDbmsBeforeLoadIntoDatabaseTest2<T extends BaseMap<A,P,M> ,
A  extends AnyBase<A,String> ,
P   extends AnyBase<P,Integer> ,
M extends BaseBean<?,?>,B extends BrainzBaseEntity<B>,S extends B> {

	// Autowire the CsvStrategyCategorizer to access categorized CSV processing strategies.
	@Autowired
	private CsvStrategyCategorizer<T,A,P,M> csvStrategyCategorizer; // Using wildcards for flexibility

	// Autowire the BrainzPersistenceService for database interaction.
	@Autowired
	private BrainzPersistenceService<B> brainzPersistenceService; // Using wildcard as the entity type varies

	/**
	 * Purpose of this test method:
	 * This method sketches a strategy to dynamically retrieve a specific entity's
	 * class from the CSV processing strategies and then query the database
	 * to check if an instance of that entity already exists. This approach allows
	 * the system to verify the presence of an entity in the RDBMS based on its
	 * definition within the CSV processing pipeline, serving as a pre-loading check.
	 * It demonstrates how to link the CSV processing configuration with database existence checks.
	 */
	@Test
	void sketchCheckEntitiesExistenceUsingStrategy() {
		assertNotNull(csvStrategyCategorizer, "CsvStrategyCategorizer must be autowired for this test.");
		assertNotNull(brainzPersistenceService, "BrainzPersistenceService must be autowired for this test.");

		System.out.println("Attempting to retrieve CSV categorization strategies...");

		// 1. Retrieve the CsvStrategyCategorizer and its categorized strategies.
		// The exact method to get a specific strategy (e.g., for 'Area' or 'Artist')
		// would depend on the implementation of CategorizedCsvStrategies.
		// For sketching, we'll assume we can get a specific strategy's associated BaseBean class.
		CategorizedCsvStrategies<T,A,P,M> categorizedStrategies = csvStrategyCategorizer.categorizeStrategies();
		
		Map<String , CsvFileItemConcreteStrategy<T,A,P,M> > baseStrategies = categorizedStrategies.basetypeStrategies();
		

		List<LoadedEntitiesReport> report = baseStrategies
			.entrySet()	
			.stream()
			.map(entry -> {
				
							assertNotNull(categorizedStrategies, "CategorizedCsvStrategies should not be null.");
							System.out.println("Processing strategy for: " + entry.getKey());
							CsvFileItemConcreteStrategy<T,A,P,M>  strategy = entry.getValue();
							M baseBean;
							try {
								// Instantiate the BaseBean from the strategy's immutable type
								baseBean = strategy.getImmutable().newInstance();
							} catch (Exception e) {
								e.printStackTrace();
								throw new RuntimeException("Failed to instantiate BaseBean from strategy: " + entry.getKey(), e);
							}
							assertNotNull (baseBean, "Instantiated BaseBean should not be null for strategy: " + entry.getKey());
							
							// Get the Base Class from the instantiated BaseBean
							Class<? extends BaseEntity<?,?>> entityClass = (Class<? extends BaseEntity<?, ?>>) baseBean.getBaseClass();
							assertNotNull(entityClass, "Could not determine the entity class from the strategy for: " + entry.getKey());
							System.out.println("Target entity class identified for " + entry.getKey() + ": " + entityClass.getName());
					
							// 3. Cast/treat the BaseBean Class as a BrainzBaseEntity for persistence service.
							// This cast assumes that your BaseBean types used in CSV strategies are
							// compatible with or extend BrainzBaseEntity.
							// Note: This unchecked cast needs careful handling in real production code.
							Class<BrainzBaseEntity<?>> brainzEntityClass = (Class<BrainzBaseEntity<?>>) entityClass;
					
							// 4. Query the RDBMS using BrainzPersistenceService.
							// We're performing a count based on a newly instantiated entity.
							// The count(Example) will typically count all entities of that type if the example is empty,
							// or match by ID if an ID is set on the example.
							Optional<B> entityInstance = (Optional<B>) Base.newInstance(brainzEntityClass);
							
							// Using map for Optional processing
							Optional<LoadedEntitiesReport> currentReport = entityInstance.map(ent-> {
								System.out.println("Attempting to count entities for type " + brainzEntityClass.getSimpleName());
					
								// Example: If you need to count based on specific criteria, set them on 'ent' here.
								// e.g., ent.setName("SpecificName");
								
								// Create an Example from the (potentially empty or partially filled) entity instance
								Example<B> ex = Example.of(ent);
								
								// Perform the count operation
								long result = brainzPersistenceService.count(ex);
								System.out.println("✅ Entity " + brainzEntityClass.getSimpleName() + " number of entities found: " + result + " in the database.");
								
								// Return a new LoadedEntitiesReport
								return new LoadedEntitiesReport(brainzEntityClass, result);
							});
							
							// Assert that the Optional contains a value (meaning Base.newInstance and subsequent map operation succeeded)
							assertTrue(currentReport.isPresent(), "Report for " + entry.getKey() + " should be present.");
							return currentReport.get();
			}).collect(Collectors.toList());
				
		// --- Report Generation and Verification ---
		System.out.println("\n--- Entity Loading Report ---");
		List<Class<?>> notLoadedEntities = new ArrayList<>();

		if (report.isEmpty()) {
			System.out.println("No entities were processed for the report.");
		} else {
			for (LoadedEntitiesReport item : report) {
				String entityName = item.entity().getSimpleName();
				Long count = item.value();

				if (count > 0) {
					System.out.println(String.format("✅ %-30s: %d entities loaded.", entityName, count));
				} else {
					System.out.println(String.format("❌ %-30s: NOT LOADED (0 entities found).", entityName));
					notLoadedEntities.add(item.entity());
				}
			}
		}

		System.out.println("\n--- Summary of Not Loaded Entities ---");
		if (notLoadedEntities.isEmpty()) {
			System.out.println("All processed base entities appear to have been loaded into the database.");
		} else {
			System.out.println("The following base entities were NOT found in the database:");
			for (Class<?> entityClass : notLoadedEntities) {
				System.out.println("  - " + entityClass.getName());
			}
			// Optional: Add an assertion here if you expect all entities to be loaded
			// Or if you expect specific entities NOT to be loaded for a controlled test failure
			// Example: fail("Some entities were not loaded: " + notLoadedEntities.stream().map(Class::getSimpleName).collect(Collectors.joining(", ")));
		}

		System.out.println("\n--- End of Report ---");

		// Final assertion for the test method itself
		// You might want to assert that the 'report' is not empty,
		// or that a minimum number of entities were found, depending on test context.
		assertTrue(!report.isEmpty(), "The loaded entities report should not be empty.");
		// Example: assertTrue(notLoadedEntities.isEmpty(), "Expected all base entities to be loaded.");
	}
}
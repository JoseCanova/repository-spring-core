package org.nanotek.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.config.CsvFileConfigurations;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// This annotation loads the full Spring Boot application context for the test.
// It will instantiate CsvFileConfigurations and CsvStrategyCategorizer as real beans.
@ExtendWith(SpringExtension.class)
@SpringBootTest
class CsvStrategyCategorizerTest<T extends BaseMap<S,P,M> ,
                               S  extends AnyBase<S,String> ,
                               P   extends AnyBase<P,Integer> ,
                               M extends BaseBean<?,?>> {

    // Autowire the service under test. Spring will inject the real bean.
    @Autowired
    private CsvStrategyCategorizer<T,S,P,M> csvStrategyCategorizer;

    // Autowire CsvFileConfigurations. Spring will inject the real bean,
    // populated from application.yml.
    @Autowired
    private CsvFileConfigurations<T,S,P,M> csvFileConfigurations;

    /**
     * This test verifies the categorization logic with actual CSV configurations
     * loaded from application.yml and real JPA entity classes.
     *
     * IMPORTANT: For this test to pass meaningfully, your application.yml
     * must define CsvFileItemConcreteStrategy beans whose 'immutable' property
     * points to actual JPA entity classes in your project.
     *
     * - Some of these entity classes should NOT have @ManyToOne or @OneToOne annotations
     * on their fields (these will be categorized as "basetype").
     * - Others should HAVE @ManyToOne or @OneToOne annotations (these will be "regular").
     *
     * Adjust the specific strategy names in the assertions below (e.g., "areatype", "artist")
     * to match what you actually have configured in your `application.yml` and
     * the structure of your corresponding entity classes.
     */
    @Test
    void testCategorizeStrategies_withRealConfigs() {
        assertNotNull(csvStrategyCategorizer, "CsvStrategyCategorizer should be autowired by Spring.");
        assertNotNull(csvFileConfigurations, "CsvFileConfigurations should be autowired by Spring.");

        // Ensure CsvFileConfigurations has loaded some strategies from application.yml
        Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> allLoadedStrategies = csvFileConfigurations.getCsvConfigs();
        assertFalse(allLoadedStrategies.isEmpty(), "CsvFileConfigurations should load some strategies from application.yml for this test to be meaningful. Please check your application.yml and ensure CSV strategies are configured.");

        // Call the method under test to perform categorization
        CategorizedCsvStrategies<T,S,P,M> categorizedStrategies =
                csvStrategyCategorizer.categorizeStrategies();

        // Basic assertions for the result
        assertNotNull(categorizedStrategies, "CategorizedCsvStrategies result should not be null.");
        assertNotNull(categorizedStrategies.basetypeStrategies(), "Basetype strategies map should not be null.");
        assertNotNull(categorizedStrategies.regularStrategies(), "Regular strategies map should not be null.");

        // Log the results for detailed verification in the console output
        System.out.println("\n--- Categorization Results (Spring Boot Test - Real Configs) ---");
        System.out.println("Basetype Strategies Found:");
        categorizedStrategies.basetypeStrategies().forEach((key, value) -> {
            try {
                // Get the Class<?> from getImmutable(), instantiate it, and get its base class
                Class<?> immutableClass = (Class<?>) value.getImmutable();
                Object instance = immutableClass.getDeclaredConstructor().newInstance();
                Class<?> baseClass = BaseBean.class.cast(instance).getBaseClass();
                System.out.println("  - " + key + " (Entity: " + baseClass.getSimpleName() + ")");
            } catch (Exception e) {
                System.err.println("  - " + key + " (Error getting entity class for logging: " + e.getMessage() + ")");
            }
        });

        System.out.println("\nRegular Strategies Found:");
        categorizedStrategies.regularStrategies().forEach((key, value) -> {
            try {
                // Get the Class<?> from getImmutable(), instantiate it, and get its base class
                Class<?> immutableClass = (Class<?>) value.getImmutable();
                Object instance = immutableClass.getDeclaredConstructor().newInstance();
                Class<?> baseClass = BaseBean.class.cast(instance).getBaseClass();
                System.out.println("  - " + key + " (Entity: " + baseClass.getSimpleName() + ")");
            } catch (Exception e) {
                System.err.println("  - " + key + " (Error getting entity class for logging: " + e.getMessage() + ")");
            }
        });

        // --- Specific Assertions based on your application.yml and entity structures ---
        // You MUST adjust these `assertTrue` and `assertFalse` calls with
        // the actual names of strategies configured in your `application.yml`
        // and verify their corresponding entity classes meet the structural criteria.

        // Example: Asserting a known basetype (e.g., 'areatype' or 'artisttype')
        // This checks if the 'areatype' strategy exists in the basetype map.
        // It implies that the entity associated with 'areatype' (e.g., org.nanotek.beans.entity.AreaType)
        // must NOT have @ManyToOne or @OneToOne fields.
        assertTrue(categorizedStrategies.basetypeStrategies().containsKey("areatype"),
                   "Expected 'areatype' to be categorized as a basetype strategy. " +
                   "Please ensure 'areatype' is configured in application.yml and its entity has no @ManyToOne/@OneToOne relationships.");

        // Example: Asserting a known regular type (e.g., 'artist' or 'recording')
        // This checks if the 'artist' strategy exists in the regular map.
        // It implies that the entity associated with 'artist' (e.g., org.nanotek.beans.entity.Artist)
        // MUST have at least one @ManyToOne or @OneToOne relationship.
        assertTrue(categorizedStrategies.regularStrategies().containsKey("artist"),
                   "Expected 'artist' to be categorized as a regular strategy. " +
                   "Please ensure 'artist' is configured in application.yml and its entity has @ManyToOne/@OneToOne relationships.");

        // General checks: Ensure both categories are populated if you expect them to be
        assertTrue(categorizedStrategies.basetypeStrategies().size() > 0, "Should find at least one basetype strategy.");
        assertTrue(categorizedStrategies.regularStrategies().size() > 0, "Should find at least one regular strategy.");
    }

    /**
     * This test case provides general scenario coverage and primarily ensures that the categorizer
     * doesn't throw unexpected errors. Its outcome depends entirely on your application.yml.
     */
    @Test
    void testCategorizeStrategies_generalScenarioCoverage() {
        System.out.println("\n--- Running testCategorizeStrategies_generalScenarioCoverage (Relies on application.yml) ---");
        CategorizedCsvStrategies<T,S,P,M> categorizedStrategies =
                csvStrategyCategorizer.categorizeStrategies();

        assertNotNull(categorizedStrategies);
        System.out.println("Basetype count: " + categorizedStrategies.basetypeStrategies().size());
        System.out.println("Regular count: " + categorizedStrategies.regularStrategies().size());
    }
}
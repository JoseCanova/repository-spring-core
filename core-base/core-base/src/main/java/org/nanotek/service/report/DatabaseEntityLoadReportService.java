package org.nanotek.service.report;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional; // Added for completeness, if used in service methods
import java.util.function.BiFunction;
import java.util.stream.Collectors; // Added for completeness, if used in service methods

import javax.validation.constraints.NotNull;

import org.nanotek.AnyBase;
import org.nanotek.Base; // Added for completeness, if Base.newInstance is used
import org.nanotek.BaseBean;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy; // Added for completeness
import org.nanotek.opencsv.service.CategorizedCsvStrategies; // Added for completeness
import org.nanotek.opencsv.service.CsvStrategyCategorizer;
import org.nanotek.opencsv.service.LoadedEntitiesReport;
import org.nanotek.service.jpa.BrainzPersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example; // Added for completeness, if count(Example) is used


/**
 * Service responsible for generating comprehensive reports on the loading status of entities
 * into the database. This class provides an "overall status" of BaseTypes and RegularTypes,
 * indicating which entities have been successfully persisted and their respective counts.
 *
 * <p>It embodies the production-ready implementation of the database entity load verification
 * logic initially sketched and tested within {@code CsvCheckDbmsBeforeLoadIntoDatabaseTest2}.</p>
 *
 * <p>Adherence to SOLID Principles:</p>
 * <ul>
 * <li><b>Single Responsibility Principle (S):</b> This service strictly adheres to the Single
 * Responsibility Principle. Its sole purpose is to provide reporting on the status of
 * entities loaded into the database. It does not concern itself with the actual process
 * of loading CSV data, transforming beans, or persisting entities; it only queries and reports
 * on their presence and quantity.</li>
 * <li><b>Liskov Substitution Principle (L):</b> This service is designed to ensure that any
 * future subtype or implementation can seamlessly replace it without altering the correctness
 * of the program. All methods exposed by this service, such as {@code generateLoadReport()},
 * are expected to maintain their predefined contract and behavior, allowing clients to
 * interact with any substitute consistently and without unexpected side effects.</li>
 * </ul>
 *
 * <p>This service is annotated with {@link DatabaseReport}, a custom stereotype annotation that
 * further clarifies its role as a report-generating service specifically focused on database-related
 * loading information.</p>
 */
@DatabaseReport
public class DatabaseEntityLoadReportService<T extends BaseMap<A,P,M> ,
A  extends AnyBase<A,String> ,
P   extends AnyBase<P,Integer> ,
M extends BaseBean<?,B>,B extends BrainzBaseEntity<B>,S extends B> {
	
		private static Logger logger = LoggerFactory.getLogger(DatabaseEntityLoadReportService.class);
		
		// Autowire the CsvStrategyCategorizer to access categorized CSV processing strategies.
		// Using field injection for @Autowired
		private CsvStrategyCategorizer<T,A,P,M> csvStrategyCategorizer; 

		// Autowire the BrainzPersistenceService for database interaction.
		// Using field injection for @Autowired
		private BrainzPersistenceService<B> brainzPersistenceService; 

	    private BiFunction<Class<?>, Long, LoadedEntitiesReport> reportFactory = LoadedEntitiesReport::new;

		/**
		 * Constructs a new {@code DatabaseEntityLoadReportService} with the necessary dependencies.
		 * Spring's dependency injection will provide the required categorizer and persistence services.
		 *
		 * @param csvStrategyCategorizer2 The categorizer for CSV strategies, providing access to entity types. Must not be null.
		 * @param brainzPersistenceService2 The persistence service for interacting with the database. Must not be null.
		 */
		@Autowired // Constructor injection is generally preferred for mandatory dependencies
		public DatabaseEntityLoadReportService(
		    @NotNull CsvStrategyCategorizer<T,A,P,M> csvStrategyCategorizer2,
		    @NotNull BrainzPersistenceService<B> brainzPersistenceService2) {
		    this.csvStrategyCategorizer = csvStrategyCategorizer2;
		    this.brainzPersistenceService = brainzPersistenceService2;
		    

		}
		
	 /**
	  * Returns the CSV strategy categorizer instance used by this service.
	  * This categorizer provides access to different CSV processing strategies
	  * and their associated entity types.
	  *
	  * @return The {@link CsvStrategyCategorizer} instance.
	  */
	 public CsvStrategyCategorizer<T, A, P, M> getCsvStrategyCategorizer() {
			return csvStrategyCategorizer;
		}

	 /**
	  * Returns the Brainz persistence service instance used by this service for database interactions.
	  * This service provides methods for querying and persisting Brainz-related entities in the database.
	  *
	  * @return The {@link BrainzPersistenceService} instance.
	  */
		public BrainzPersistenceService<B> getBrainzPersistenceService() {
			return brainzPersistenceService;
		}

    /**
     * Generates a comprehensive report on the loading status of various entity types
     * categorized by {@link CsvStrategyCategorizer}.
     * It queries the database for counts of both BaseType entities and RegularType entities
     * based on their respective CSV strategies and combines the results.
     *
     * @return A {@code List} of {@link LoadedEntitiesReport} objects, each containing
     * the class of the entity and the count of its instances found in the database.
     * Returns an empty list if no entities are found or categorized.
     */
     public List<LoadedEntitiesReport> generateLoadReport() { 
    	 
 		 CategorizedCsvStrategies<T, A, P, M> categorizedStrategies = csvStrategyCategorizer.categorizeStrategies();
 		 
 		 Map<String , CsvFileItemConcreteStrategy<T,A,P,M> > baseTypeStrategies = categorizedStrategies.basetypeStrategies();
 		 Map<String , CsvFileItemConcreteStrategy<T,A,P,M> > regularStrategies = categorizedStrategies.basetypeStrategies();
    	 
 		List<LoadedEntitiesReport> resultForBaseTypes = baseTypeStrategies.values().stream()
 					.map(strategy -> collectStrategyLoadResult(strategy))
 					.map(ler -> ler.get()).collect(Collectors.toList());
 		
 		List<LoadedEntitiesReport> resultForRegularTypes = regularStrategies.values().stream()
					.map(strategy -> collectStrategyLoadResult(strategy))
					.map(ler -> ler.get()).collect(Collectors.toList());
 		List<List<LoadedEntitiesReport>> listOfLists = Arrays.asList(resultForBaseTypes, resultForRegularTypes);
 		 return  listOfLists.stream()
                 .flatMap(List::stream) // Flatmap each inner list to its stream
                 .collect(Collectors.toList());
     }
     
     public LoadedEntitiesReport generateLoadReport(CsvFileItemConcreteStrategy<T,A,P,M> strategy) { 
    	 return collectStrategyLoadResult(strategy).orElseThrow();
     }
     
    /**
     * Collects the load result for a given CSV file item strategy by dynamically
     * instantiating the associated {@code BaseBean} and {@code BrainzBaseEntity},
     * and then querying the database for the count of that entity type.
     *
     * @param strategy The {@link CsvFileItemConcreteStrategy} for which to collect the load report.
     * @return An {@link Optional} containing a {@link LoadedEntitiesReport} if the entity
     * class can be instantiated and counted, otherwise an empty Optional.
     */
    @SuppressWarnings("unchecked")
	private Optional<LoadedEntitiesReport> collectStrategyLoadResult(CsvFileItemConcreteStrategy<T, A, P, M> strategy) {
			Class<M> baseBeanClass = strategy.getImmutable();
			Optional<M> obaseBeanInstance = Base.<M>newInstance(baseBeanClass);
			// Assuming getBaseClass() returns Class<? extends BrainzBaseEntity<B>>
				return obaseBeanInstance
				.map(baseBean -> {
					Class<? extends B> baseEntityClass = baseBean.getBaseClass();
					Optional<S> oemptyBase = (Optional<S>) Base.newInstance(baseEntityClass); // Cast might be unsafe without proper type checks
					Optional<Long> oresult =  countEntitiesByType(oemptyBase);
					return oresult.map(or -> reportFactory.apply(baseEntityClass, or)).orElseThrow();
				});
			}

    /**
     * Counts the number of entities of a specific type in the database using Spring Data JPA's
     * Query By Example functionality.
     *
     * @param oemptyBase An {@link Optional} containing an empty instance of the entity ({@code S})
     * whose count is to be determined. This instance is used to create an
     * {@link Example} for the count query.
     * @return An {@link Optional} containing the count of entities of the specified type.
     * Returns an empty Optional if the provided {@code oemptyBase} is empty, or
     * if an error occurs during counting (handled by the persistence service).
     */
	 public Optional<Long> countEntitiesByType(Optional<S> oemptyBase) { 
    	 return oemptyBase.map(s ->{
    		 Example<S> theExample = Example.of(s);
    		 return brainzPersistenceService.count(theExample);
    	 });
    	 
     }
}
package org.nanotek.opencsv.manager;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.CsvBaseParserConfigurations;
import org.nanotek.opencsv.CsvBaseParserProcessor;
import org.nanotek.opencsv.CsvResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * CsvProcessingManagerService orchestrates the processing of various CSV files
 * based on configured parser types, incorporating priority and categorization logic.
 * It acts as a singleton Spring Service.
 */
@Service
public class CsvProcessingManagerService <T extends BaseMap<S,P,M> ,
S  extends AnyBase<S,String> ,
P   extends AnyBase<P,Integer> ,
M extends BaseBean<?,?>,
R extends CsvResult<?,?>>{

    private static final Logger log = LoggerFactory.getLogger(CsvProcessingManagerService.class);

    // The processor that produces Callable tasks, each with its own parser instance.
    @Autowired
    private CsvBaseParserProcessor<T,S,P,M,R> csvBaseParserProcessor;

    // The configurations that provide definitions for different parser types and their metadata.
    // This is used to discover all available parser types.
    @Autowired
    private CsvBaseParserConfigurations<T,S,P,M> csvParserConfigurations;

    // A map to define processing priorities for each parser type.
    // Example: "high_priority_file": 1, "low_priority_file": 10
    // This can be injected from application.properties/yml, e.g., using:
    // nanotek.opencsv.manager.parserPriorities={"parserA":1, "parserB":2}
    @Value("#{${nanotek.opencsv.manager.parserPriorities:{}}}")
    private Map<String, Integer> parserPriorities;

    // A map to categorize parser types (e.g., "BaseType", "RegularType").
    // Example: "base_data_parser": "BaseType", "audit_log_parser": "RegularType"
    // This can be injected from application.properties/yml, e.g., using:
    // nanotek.opencsv.manager.parserCategories={"parserA":"BaseType", "parserB":"RegularType"}
    @Value("#{${nanotek.opencsv.manager.parserCategories:{}}}")
    private Map<String, String> parserCategories;

    // A map to keep track of ongoing processing for each parser type, storing their ListenableFutures.
    private ConcurrentMap<String, ListenableFuture<CsvResult<?,?>>> ongoingProcesses = new ConcurrentHashMap<>();

    // Constructor for explicit dependency injection, though @Autowired fields suffice.
    public CsvProcessingManagerService() {
    }

    /**
     * Initiates processing for all configured CSV parser types.
     * This method applies priority and categorization logic before submitting tasks.
     * This method is marked @Async, meaning Spring will run it in a separate thread,
     * allowing the caller to return immediately.
     */
    @Async
    public void startProcessingAllConfiguredFiles() {
        log.info("Manager initiated processing for all configured CSV files.");

        // Get the names of all configured parser types from CsvBaseParserConfigurations.
        // It's assumed CsvBaseParserConfigurations provides a method like getParserConfigurationMap()
        // which returns a Map whose keys are the parser names.
        Set<String> parserNames = csvParserConfigurations.getCsvStrategies().keySet();

        if (parserNames.isEmpty()) {
            log.warn("No CSV parser configurations found. Manager cannot start processing.");
            return;
        }

        // Apply Priority and Categorization Logic:
        // 1. Filter by category (e.g., to only process "BaseType" parsers if required)
        // 2. Sort by priority (lower integer value typically means higher priority)
        List<String> prioritizedParserNames = parserNames.stream()
            .filter(name -> {
                // Example: Only process parsers categorized as "BaseType"
                // String category = getCategory(name);
                // return "BaseType".equals(category);
                return true; // By default, process all categories
            })
            .sorted(Comparator.comparing(this::getPriority)) // Sort based on the priority assigned to each parser name
            .collect(Collectors.toList());

        log.info("Processing order for parser types: {}", prioritizedParserNames);

        // Iterate through the prioritized list and request a processing task for each.
        for (String parserName : prioritizedParserNames) {
            log.info("Manager requesting processing for parser type: {}", parserName);
            try {
                // Request a ListenableFutureTask from CsvBaseParserProcessor for the specific parser type.
                // The CsvBaseParserProcessor.getNext(String parserName) method is designed to provide
                // a new, dedicated CsvBaseParser instance for this task.
                ListenableFuture<CsvResult<?,?>> future = csvBaseParserProcessor.getNext(parserName);
                ongoingProcesses.put(parserName, future); // Store the future to track its progress

                // Add callbacks to handle the completion (success or failure) of each individual task.
                future.addCallback(
                    result -> {
                        log.info("Processing for parser type '{}' completed successfully.", parserName);
                        ongoingProcesses.remove(parserName); // Remove from tracking map on success
                    },
                    ex -> {
                        log.error("Processing for parser type '{}' failed: {}", parserName, ex.getMessage(), ex);
                        ongoingProcesses.remove(parserName); // Remove from tracking map on failure
                    }
                );

            } catch (Exception e) {
                log.error("Failed to initiate processing for parser type {}: {}", parserName, e.getMessage(), e);
            }
        }
    }

    /**
     * Helper method to retrieve the priority for a given parser name.
     * Priorities are fetched from the injected 'parserPriorities' map.
     * Defaults to a very low priority (999) if the parser name is not explicitly configured,
     * ensuring unconfigured items are processed last.
     */
    private int getPriority(String parserName) {
        return parserPriorities.getOrDefault(parserName, 999);
    }

    /**
     * Helper method to retrieve the category for a given parser name.
     * Categories are fetched from the injected 'parserCategories' map.
     * Defaults to "RegularType" if the parser name is not explicitly configured.
     */
    private String getCategory(String parserName) {
        return parserCategories.getOrDefault(parserName, "RegularType");
    }

    /**
     * Allows external components to monitor or wait for a specific processing task.
     * @param parserName The name of the parser type.
     * @return The ListenableFuture associated with the task, or null if not found.
     */
    public ListenableFuture<CsvResult<?,?>> getOngoingProcess(String parserName) {
        return ongoingProcesses.get(parserName);
    }

    /**
     * Blocks the current thread until all currently tracked processing tasks complete.
     * This method iterates through all futures in 'ongoingProcesses' and calls their .get() method.
     */
    public void waitForAllProcessesToComplete() {
        log.info("Manager waiting for all initiated CSV processes to complete.");
        // Iterate over a copy to avoid ConcurrentModificationException if callbacks modify 'ongoingProcesses'
        new ConcurrentHashMap<>(ongoingProcesses).forEach((name, future) -> {
            try {
                future.get(); // Blocks until task completes or throws an exception
                log.info("Process for parser type '{}' completed.", name);
            } catch (Exception e) {
                log.error("Process for parser type '{}' failed during wait: {}", name, e.getMessage());
            }
        });
        ongoingProcesses.clear(); // Clear the map once all are awaited
        log.info("All initiated CSV processes completed.");
    }
}
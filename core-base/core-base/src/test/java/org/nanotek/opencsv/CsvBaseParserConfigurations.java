package org.nanotek.opencsv;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.config.CsvFileConfigurations;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A Spring component responsible for holding and initializing all
 * CsvFileItemConcreteStrategy instances configured in the application.
 * It ensures that the 'afterPropertiesSet' method is called on each strategy
 * upon application startup, preparing them for use by CsvBaseParser instances.
 */
@Component // Declare as a Spring component
public class CsvBaseParserConfigurations<T extends BaseMap<S,P,M> ,
S  extends AnyBase<S,String> ,
P   extends AnyBase<P,Integer> ,
M extends BaseBean<?,?>> {

    private final Map<String, CsvBaseParser<T,S,P,M>> csvStrategies;

    /**
     * Constructor that autowires the CsvFileConfigurations and
     * initializes all contained CsvFileItemConcreteStrategy instances.
     *
     * @param csvFileConfigurations The configurations holding all CSV strategies.
     */
    public CsvBaseParserConfigurations(@Autowired CsvFileConfigurations<T,S,P,M> csvFileConfigurations) {
        // Get the raw map of strategies
        Map<String, CsvFileItemConcreteStrategy<T,S,P,M>> rawStrategies = csvFileConfigurations.getCsvConfigs();
        // Initialize strategies by calling afterPropertiesSet on each
        if (rawStrategies != null && !rawStrategies.isEmpty()) {
            System.out.println("\n--- CsvBaseParserConfigurations: Initializing CSV Strategies ---");
            csvStrategies = rawStrategies.entrySet()
            .stream().map( v -> {
                try {
                    // Call afterPropertiesSet on each strategy to ensure reader is set up
                    v.getValue().afterPropertiesSet();
                    System.out.println("  - Initialized strategy: " + v.getKey());
                } catch (Exception e) {
                    System.err.println("  - Error initializing strategy " + v.getKey() + ": " + e.getMessage());
                    e.printStackTrace();
                }
                CsvBaseParser<T,S,P,M> csvBaseParser = new CsvBaseParser<T,S,P,M>(v.getValue());
                return  Map.entry(v.getKey() ,  csvBaseParser);
            }).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
            System.out.println("--- CsvBaseParserConfigurations: All Strategies Initialized ---");
        } else {
            System.out.println("--- CsvBaseParserConfigurations: No CSV strategies found to initialize. ---");
            csvStrategies = Collections.emptyMap();
        }
    }

    /**
     * Returns an unmodifiable map of all initialized CSV strategies.
     * Other services can inject this component and retrieve specific strategies.
     * @return An unmodifiable map of CSV strategies.
     */
    public Map<String, CsvBaseParser<T,S,P,M>> getCsvStrategies() {
        return csvStrategies;
    }

    /**
     * Retrieves a specific CsvFileItemConcreteStrategy by its name.
     * @param strategyName The name of the strategy to retrieve.
     * @return An Optional containing the strategy, or empty if not found.
     */
    public Optional<CsvBaseParser<T,S,P,M>> getStrategy(String strategyName) {
        return Optional.ofNullable(csvStrategies.get(strategyName));
    }
}
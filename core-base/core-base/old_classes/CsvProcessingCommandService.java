package org.nanotek.opencsv.commands;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.CsvParsingTaskProvider;
import org.nanotek.opencsv.CsvResult;
import org.nanotek.opencsv.service.CsvStrategyCategorizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureTask;

/**
 * CsvProcessingCommandService acts as a command entry point for orchestrating
 * CSV processing tasks. It provides methods to retrieve categorized tasks
 * (e.g., base type or regular tasks) from the CsvParsingTaskProvider,
 * leveraging the CsvStrategyCategorizer.
 */
@Service
public class CsvProcessingCommandService<T extends BaseMap<S,P,M> ,
S  extends AnyBase<S,String> ,
P   extends AnyBase<P,Integer> ,
M extends BaseBean<?,?>,
R extends CsvResult<?,?>> {

    // Inject the CsvParsingTaskProvider with specific generic types
    @Autowired
    private CsvParsingTaskProvider<T,S,P,M,R> csvParsingTaskProvider;

    // Inject the CsvStrategyCategorizer with specific generic types
    @Autowired
    private CsvStrategyCategorizer<T,S,P,M> csvStrategyCategorizer;
    
    /**
     * Retrieves a map of ListenableFutures for tasks categorized as "base type".
     * These tasks are obtained from the CsvParsingTaskProvider.
     * @return A Map where keys are parser names (String) and values are ListenableFuture instances.
     */
    public Map<String, ListenableFutureTask<R>> getBaseTypeTasks () { 
    	return csvStrategyCategorizer
    	.categorizeStrategies()
    	.basetypeStrategies() // Assumes CsvStrategyCategorizer.CategorizedCsvStrategies has this method
    	.keySet()
    	.stream()
    	.map(k ->{
    		ListenableFutureTask<R> task = csvParsingTaskProvider.getListenableFutureTask()
    		.get(k); // Retrieves the specific task future for key 'k'
    		return Map.entry(k, task);
    	}).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }
    
    /**
     * Retrieves a map of ListenableFutures for tasks categorized as "regular types".
     * These tasks are obtained from the CsvParsingTaskProvider.
     * @return A Map where keys are parser names (String) and values are ListenableFuture instances.
     */
    public Map<String, ListenableFuture<R>> getRegularTasks () { 
    	return csvStrategyCategorizer
    	.categorizeStrategies()
    	.regularStrategies() // Assumes CsvStrategyCategorizer.CategorizedCsvStrategies has this method
    	.keySet()
    	.stream()
    	.map(k ->{
    		ListenableFuture<R> task = csvParsingTaskProvider.getListenableFutureTask()
    		.get(k); // Retrieves the specific task future for key 'k'
    		return Map.entry(k, task);
    	}).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }
    
}
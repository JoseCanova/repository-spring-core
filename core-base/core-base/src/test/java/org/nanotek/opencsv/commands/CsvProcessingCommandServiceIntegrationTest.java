package org.nanotek.opencsv.commands;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.CsvResult;
import org.nanotek.opencsv.service.CsvProcessingCommandService;
import org.nanotek.opencsv.service.CsvProcessorThreadRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Spring Boot integration test for CsvProcessingCommandService and CsvProcessorThreadRunner.
 * This test verifies that the Spring application context loads correctly and that
 * the essential service beans are successfully autowired.
 */
@SpringBootTest
class CsvProcessingCommandServiceIntegrationTest <T extends BaseMap<S,P,M> ,
S  extends AnyBase<S,String> ,
P   extends AnyBase<P,Integer> ,
M extends BaseBean<?,?>,
R extends CsvResult<?,?>> {

    // Autowire the CsvProcessingCommandService.
    @Autowired
    private CsvProcessingCommandService<T,S,P,M,R> csvProcessingCommandService;

    // Autowire the CsvProcessorThreadRunner.
    @Autowired
    private CsvProcessorThreadRunner<T,S,P,M,R> csvProcessorThreadRunner;

    /**
     * A simple test method to ensure the Spring application context loads without errors
     * and that the autowired dependencies are successfully injected.
     */
    @Test
    void contextLoads() {
    	
    	// If this method executes without throwing an exception, it means:
        // 1. The Spring context started successfully.
        // 2. CsvProcessingCommandService bean was created and made available.
        // 3. CsvProcessorThreadRunner bean was created and made available.
        // 4. All their dependencies (like CsvParsingTaskProvider, CsvStrategyCategorizer,
        //    ThreadPoolTaskExecutor, CsvProcessorCallBack) were also successfully resolved and injected.
    	assertNotNull(csvProcessingCommandService);
    	assertNotNull(csvProcessorThreadRunner);
        // You can add assertions here if you want to verify that the beans are not null:
        // assertNotNull(csvProcessingCommandService);
        // assertNotNull(csvProcessorThreadRunner);
    }
    
    @Test
    void executeBaseTypesTask()
    {
    	assertNotNull(csvProcessingCommandService);
    	assertNotNull(csvProcessorThreadRunner);
    	csvProcessingCommandService.getBaseTypeTasks()
    	.entrySet()
    	.stream()
    	.filter(k -> !k.getKey().equals("artistcredit"))
    	.forEach(k ->  csvProcessorThreadRunner.startProcessorThread( k.getValue()));
    }

}
package org.nanotek.opencsv.service; // Or a more suitable package like org.nanotek.opencsv.thread.runner

import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.CsvResult;
import org.nanotek.opencsv.task.CsvLoggerProcessorCallBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureTask;

/**
 * A singleton service responsible for launching a dedicated monitoring thread
 * for a given {@link CsvBaseParserProcessor}. This thread continuously
 * requests and processes tasks from the processor until it is no longer active
 * or indicates no more tasks are available.
 *
 * This class encapsulates the thread creation and management logic.
 */
@Service // Marks this class as a singleton Spring service
public class CsvProcessorThreadRunner <T extends BaseMap<S,P,M> ,
S  extends AnyBase<S,String> ,
P   extends AnyBase<P,Integer> ,
M extends BaseBean<?,?>,
R extends CsvResult<?,?>> {

    private static final Logger log = LoggerFactory.getLogger(CsvProcessorThreadRunner.class);

    @Autowired ThreadPoolTaskExecutor serviceTaskExecutor; 
    
    @Autowired CsvLoggerProcessorCallBack<R,?> callBack;
    
    // Default constructor for Spring's dependency injection
    public CsvProcessorThreadRunner() {
        // No dependencies are directly injected into this service's constructor,
        // as it operates on a 'given' processor passed to its method.
    }

    /**
     * Starts a new daemon thread that continuously polls the provided
     * {@link CsvBaseParserProcessor} for new processing tasks.
     * The thread will continue to run as long as the processor is active
     * and its {@code getNext()} method returns a non-null task result.
     *
     * @param <T> The type parameter for BaseMap in CsvBaseParserProcessor.
     * @param <S> The type parameter for AnyBase<String> in CsvBaseParserProcessor.
     * @param <P> The type parameter for AnyBase<Integer> in CsvBaseParserProcessor.
     * @param <M> The type parameter for BaseBean in CsvBaseParserProcessor.
     * @param <R> The type parameter for CsvResult in CsvBaseParserProcessor.
     * @param processor The specific {@link CsvBaseParserProcessor} instance
     * that this thread will monitor and retrieve tasks from.
     */
    public
    void startProcessorThread(ListenableFutureTask<R> listenableFutureTask) {

    	
    	listenableFutureTask.addCallback(callBack);
    	serviceTaskExecutor.submit(listenableFutureTask);
        // Create a new Thread using a lambda expression for its run() method.
        Thread processingThread = new Thread(() -> {
        	
            R result = null; // Initialize to null

            try {
                // Loop to continuously get and process tasks
                do {
                	result = listenableFutureTask.get();
                	if (result !=null)
                		System.err.println("result " + result.toString());
                    try {
                        Thread.sleep(1); // Small pause to yield CPU and prevent tight loop
                    } catch (InterruptedException ie) {
                        // Restore the interrupted status and break out of the loop
                        Thread.currentThread().interrupt(); 
                        log.warn("Monitoring thread for processor {} was interrupted during sleep. Exiting loop.", result.getClass().getName());
                        break; 
                    }

                // Continue as long as the processor reports itself as active AND
                // a task was successfully retrieved (futureTask is not null) AND
                // the result of the task (once completed) is not null (indicating content)
                } while (result != null);

            } catch (Exception e) {
                log.error("Monitoring thread for processor {} encountered an error: {}. Thread will terminate.", 
                		listenableFutureTask.getClass().getName(), e.getMessage(), e);
                // The original snippet had `throw new BaseException(e);`.
                // You cannot throw checked exceptions out of Thread.run().
                // Instead, log the error and ensure the thread terminates gracefully.
                // If you need to propagate errors, consider using an uncaught exception handler
                // or returning a status. For this scenario, logging is typically sufficient.
            }
        });

        processingThread.setDaemon(true); // Set as a daemon thread so it won't prevent JVM shutdown
        processingThread.start(); // Start the thread
        log.info("Monitoring thread '{}' launched for processor: {}", processingThread.getName(), listenableFutureTask.getClass().getName());
    }
}
package org.nanotek.opencsv.task;

import org.nanotek.AnyBase;
import org.nanotek.BaseBean;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.CsvBaseProcessor;
import org.nanotek.opencsv.CsvResult;
import org.nanotek.beans.entity.BrainzBaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable; // Or Runnable, depending on whether we need a return value

// This class wraps a CsvBaseProcessor and its priority for parallel execution.
// It implements Comparable to allow prioritization in a PriorityBlockingQueue.
public class CsvProcessTask<T extends BaseMap<S,P,M>,
                            S extends AnyBase<S,String>,
                            P extends AnyBase<P,Integer>,
                            M extends BaseBean<?,?>,
                            R extends CsvResult<?, ?>>
        implements Callable<String>, Comparable<CsvProcessTask<?,?,?,?,?>> { // Using Callable to potentially return status

    private static final Logger log = LoggerFactory.getLogger(CsvProcessTask.class);

    private final CsvBaseProcessor<T,S,P,M,R> processor;
    private final long priorityWeight; // This will be the Movement Weight (W(Vertex)mov)
    private final String configName; // Name of the configuration for logging/identification

    public CsvProcessTask(String configName, CsvBaseProcessor<T,S,P,M,R> processor, long priorityWeight) {
        this.configName = configName;
        this.processor = processor;
        this.priorityWeight = priorityWeight;
    }

    @Override
    public String call() throws Exception {
        log.info("Starting processing task for: {} with priority: {}", configName, priorityWeight);
        long processedCount = 0;
        try {
            // CsvBaseProcessor needs a method to process a batch or all lines.
            // Assuming getNext() processes one line and returns Optional.empty() when done.
            // We'll loop until the processor indicates no more lines.
//            while (processor.getNext().isPresent()) {
//                processedCount++;
//                // In a real scenario, you might add batching logic here,
//                // or have CsvBaseProcessor.processAll() method.
//            }
            log.info("Finished processing task for: {}. Processed {} records.", configName, processedCount);
            return "SUCCESS: " + configName + " processed " + processedCount + " records.";
        } catch (Exception e) {
            log.error("Error processing task for: " + configName, e);
            throw e; // Re-throw to be caught by Future.get() or ListenableFutureCallback
        }
    }

    @Override
    public int compareTo(CsvProcessTask<?,?,?,?,?> other) {
        // Higher priorityWeight means higher priority (processed first).
        // So, we want to order in descending order of priorityWeight.
        return Long.compare(other.priorityWeight, this.priorityWeight);
    }

    public String getConfigName() {
        return configName;
    }

    public long getPriorityWeight() {
        return priorityWeight;
    }
}
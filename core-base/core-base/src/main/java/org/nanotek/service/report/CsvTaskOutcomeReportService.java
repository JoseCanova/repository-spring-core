package org.nanotek.service.report;

import java.util.Map;

import org.nanotek.opencsv.CsvResult;
import org.nanotek.opencsv.service.CsvProcessingCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // Keep @Service or define @DatabaseReport as a stereotype
import org.springframework.util.concurrent.ListenableFutureTask;

/**
 * CsvTaskOutcomeReportService acts as an intermediary component responsible
 * for monitoring and processing the outcomes of asynchronous CSV parsing tasks.
 * Its primary responsibility is to transform the raw ListenableFuture results
 * provided by the CsvProcessingCommandService into a structured, user-friendly
 * format that indicates the success or failure status of each task.
 * This service adheres to the Single Responsibility Principle by focusing
 * solely on advising the status of CSV task execution, decoupling this concern
 * from the command execution itself or direct controller interaction.
 *
 * <p>Annotated with {@code @DatabaseReport}, this service is specialized for
 * generating reports derived from database operations or data processing outcomes.</p>
 */
@Service // Assuming @DatabaseReport is a meta-annotation that includes @Service or @Component
public class CsvTaskOutcomeReportService<R extends CsvResult<?,?>> {

    private final CsvProcessingCommandService<?, ?, ?, ?, R> csvProcessingCommandService;

    /**
     * Constructs a new CsvTaskOutcomeReportService with the necessary dependencies.
     * @param csvProcessingCommandService The service responsible for providing CSV processing tasks as ListenableFutures.
     */
    @Autowired
    public CsvTaskOutcomeReportService(
            CsvProcessingCommandService<?, ?, ?, ?, R> csvProcessingCommandService) {
        this.csvProcessingCommandService = csvProcessingCommandService;
    }

    /**
     * Delegates to the CsvProcessingCommandService to retrieve a map of
     * ListenableFutureTasks for CSV parsing tasks categorized as "base type".
     * These tasks represent foundational or initial data processing operations.
     *
     * @return A Map where keys are parser names (String) and values are ListenableFutureTask instances
     * representing the asynchronous base type CSV processing tasks.
     */
    public Map<String, ListenableFutureTask<R>> getBaseTypeTasks() {
        return this.csvProcessingCommandService.getBaseTypeTasks();
    }

    /**
     * Delegates to the CsvProcessingCommandService to retrieve a map of
     * ListenableFutures for CSV parsing tasks categorized as "regular types".
     * These tasks typically represent standard or general CSV data processing operations.
     *
     * @return A Map where keys are parser names (String) and values are ListenableFuture instances
     * representing the asynchronous regular type CSV processing tasks.
     */
    public Map<String, ListenableFutureTask<R>> getRegularTasks() {
        return this.csvProcessingCommandService.getRegularTasks();
    }
}
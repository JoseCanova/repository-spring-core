package org.nanotek.opencsv.manager;

import org.nanotek.opencsv.CsvParsingTaskProvider; // Import the new class name
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CsvProcessingManagerService will orchestrate CSV processing based on commands.
 * This class serves as a placeholder for future command-driven logic.
 */
@Service
public class CsvProcessingManagerService {

    // Inject the new CsvParsingTaskProvider
    @Autowired
    private CsvParsingTaskProvider<?, ?, ?, ?, ?> csvParsingTaskProvider; // Use wildcards for generics for now

}
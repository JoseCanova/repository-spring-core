package org.nanotek.service;

import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Service
public class CsvDataWeightCalculator {

    private final Map<String, CsvFileItemConcreteStrategy<?,?,?,?>> allCsvFileItemConcreteStrategies;

    // To store calculated weights for quick access after initialization
    private final Map<String, Long> fileSizesBytes = new ConcurrentHashMap<>();
    private final Map<String, Long> lineCounts = new ConcurrentHashMap<>();
    private long totalFileSize = 0;
    private long totalLineCount = 0;

    @Autowired
    public CsvDataWeightCalculator(Map<String, CsvFileItemConcreteStrategy<?,?,?,?>> allCsvFileItemConcreteStrategies) {
        this.allCsvFileItemConcreteStrategies = allCsvFileItemConcreteStrategies;
    }

    @PostConstruct
    public void initializeWeights() {
    	initializeStrategies();
        System.out.println("\n--- CsvDataWeightCalculator: Initializing File Weights ---");
        allCsvFileItemConcreteStrategies.forEach((configName, strategy) -> {
            Path filePath = Paths.get(strategy.getFileLocation(), strategy.getFileName());
            File file = filePath.toFile();

            if (file.exists() && file.isFile()) {
                try {
                    long size = Files.size(filePath);
                    long lines = 0;
                    try (Stream<String> lineStream = Files.lines(filePath)) {
                        lines = lineStream.count();
                    }

                    fileSizesBytes.put(configName, size);
                    lineCounts.put(configName, lines);
                    totalFileSize += size;
                    totalLineCount += lines;

                    System.out.println(String.format("  - %s: Size=%,d bytes, Lines=%,d", configName, size, lines));
                } catch (IOException e) {
                    System.err.println("  - Error reading file for " + configName + " at " + filePath + ": " + e.getMessage());
                }
            } else {
                System.err.println("  - File not found or not a regular file for " + configName + " at " + filePath);
            }
        });
        System.out.println(String.format("--- CsvDataWeightCalculator: Total File Size=%,d bytes, Total Lines=%,d ---", totalFileSize, totalLineCount));
    }

    private void initializeStrategies() {
    	allCsvFileItemConcreteStrategies
		 	.values()
		 	.stream()
		 	.forEach(
		 		strategy ->{
		 				strategy.afterPropertiesSet();
		 	});		
	}

	/**
     * Returns the size of a specific CSV file in bytes.
     * @param configName The name of the CSV configuration (e.g., "artist").
     * @return The file size in bytes, or 0 if not found/error.
     */
    public long getFileSize(String configName) {
        return fileSizesBytes.getOrDefault(configName, 0L);
    }

    /**
     * Returns the number of lines (records) in a specific CSV file.
     * @param configName The name of the CSV configuration.
     * @return The line count, or 0 if not found/error.
     */
    public long getLineCount(String configName) {
        return lineCounts.getOrDefault(configName, 0L);
    }

    /**
     * Returns a map of all file sizes by configuration name.
     * @return An unmodifiable map of file sizes.
     */
    public Map<String, Long> getAllFileSizes() {
        return Map.copyOf(fileSizesBytes); // Java 10+ for Map.copyOf
    }

    /**
     * Returns a map of all line counts by configuration name.
     * @return An unmodifiable map of line counts.
     */
    public Map<String, Long> getAllLineCounts() {
        return Map.copyOf(lineCounts); // Java 10+ for Map.copyOf
    }

    /**
     * Returns the total estimated file size across all configured CSVs.
     * @return Total file size in bytes.
     */
    public long getTotalFileSize() {
        return totalFileSize;
    }

    /**
     * Returns the total estimated number of lines across all configured CSVs.
     * @return Total line count.
     */
    public long getTotalLineCount() {
        return totalLineCount;
    }

    // You could also add methods to calculate a combined "physical effort" score
    // based on a formula combining size, line count, and other factors.
    // For example:
    // public long getPhysicalEffort(String configName) {
    //     long size = getFileSize(configName);
    //     long lines = getLineCount(configName);
    //     // Simple example: 1 byte = 1 unit effort, 1 line = 100 units effort
    //     return size + (lines * 100);
    // }
}
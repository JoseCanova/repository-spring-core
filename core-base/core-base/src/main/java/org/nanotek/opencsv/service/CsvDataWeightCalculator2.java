package org.nanotek.opencsv.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap; // Import for ConcurrentHashMap
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nanotek.config.CsvFileConfigurations;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// Removed generic parameters from CsvDataWeightCalculator itself
public class CsvDataWeightCalculator2 {

    // Autowire the CsvFileConfigurations with wildcards, as it holds strategies of varying generics
    @Autowired
    private CsvFileConfigurations<?,?,?,?> csvFileConfigurations;

    private Map<String, Pair<Long, Long>> fileWeightsMap;

    // Default constructor is fine, Spring will use it for @Service
    public CsvDataWeightCalculator2() {
    }

    // This method is for explicitly triggering afterPropertiesSet on strategies if needed.
    // It's separate from the weight calculation.
    public void initialize() {
        this.csvFileConfigurations
                .getCsvConfigs()
                .values()
                .forEach(v -> v.afterPropertiesSet());
    }

    /**
     * Calculates the file size and line count for all configured CSV files.
     * This method will be called explicitly when the weights are needed.
     *
     * @return A Map where key is configName (String) and value is Pair<FileSizeInBytes, LineCount>.
     */
    public Map<String, Pair<Long, Long>> calculateAllFileWeights() {
        System.out.println("\n--- CsvDataWeightCalculator: Calculating All File Weights ---");

        Map<String, Pair<Long, Long>> calculatedWeights = csvFileConfigurations
                .getCsvConfigs()
                .entrySet().stream()
                .map(entry -> {
                    String configName = entry.getKey();
                    CsvFileItemConcreteStrategy<?,?,?,?> strategy = entry.getValue();

                    String fileLocation = strategy.getFileLocation();
                    String fileName = strategy.getFileName();

                    System.out.println("DEBUG: Processing config: " + configName);
                    System.out.println("DEBUG: Raw fileLocation (from config): '" + fileLocation + "' (length: " + fileLocation.length() + ")");
                    System.out.println("DEBUG: Raw fileName (from config): '" + fileName + "' (length: " + fileName.length() + ")");

                    String cleanedFileLocation = fileLocation.replaceAll("[^\\p{Print}\\p{Space}]", "").trim();
                    String cleanedFileName = fileName.replaceAll("[^\\p{Print}\\p{Space}]", "").trim();

                    System.out.println("DEBUG: Cleaned fileLocation: '" + cleanedFileLocation + "' (length: " + cleanedFileLocation.length() + ")");
                    System.out.println("DEBUG: Cleaned fileName: '" + cleanedFileName + "' (length: " + cleanedFileName.length() + ")");

                    Path filePath = Paths.get(cleanedFileLocation, cleanedFileName);
                    File file = filePath.toFile();

                    System.out.println("DEBUG: Resolved Path (from Java): " + filePath.toAbsolutePath());
                    System.out.println("DEBUG: File Exists: " + file.exists());
                    System.out.println("DEBUG: Is File: " + file.isFile());
                    System.out.println("DEBUG: Can Read: " + file.canRead());
                    System.out.println("DEBUG: Is Absolute: " + filePath.isAbsolute());
                    System.out.println("DEBUG: Parent Path: " + (filePath.getParent() != null ? filePath.getParent().toAbsolutePath() : "N/A"));

                    long size = 0;
                    long lines = 0;

                    if (file.exists() && file.isFile() && file.canRead()) {
                        try {
                            size = Files.size(filePath);
                            try (Stream<String> lineStream = Files.lines(filePath)) {
                                lines = lineStream.count();
                            }
                            System.err.println(String.format("  - %s: Size=%,d bytes, Lines=%,d", configName, size, lines));
                        } catch (IOException e) {
                            System.err.println("  - Error reading file for " + configName + " at " + filePath + ": " + e.getMessage());
                            e.printStackTrace();
                        }
                    } else {
                        System.err.println("  - File not found, not a regular file, or not readable for " + configName + " at " + filePath);
                    }
                    return new AbstractMap.SimpleEntry<>(configName, Pair.of(size, lines));
                })
                // Corrected to use the 4-argument toMap for ConcurrentHashMap and merge function
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, // Merge function: if duplicate key, keep the old value
                        ConcurrentHashMap::new)); // Use ConcurrentHashMap for thread-safe map

        this.fileWeightsMap = calculatedWeights;
        return calculatedWeights;
    }

    public long getFileSize(String configName) {
        return Optional.ofNullable(fileWeightsMap)
                .map(map -> map.get(configName))
                .map(Pair::getFirst)
                .orElse(0L);
    }

    public long getLineCount(String configName) {
        return Optional.ofNullable(fileWeightsMap)
                .map(map -> map.get(configName))
                .map(Pair::getSecond)
                .orElse(0L);
    }

    public Map<String, Pair<Long, Long>> getAllFileWeights() {
        return Optional.ofNullable(fileWeightsMap)
                .map(Map::copyOf)
                .orElse(Map.of());
    }

    public long getTotalFileSize() {
        return Optional.ofNullable(fileWeightsMap)
                .map(map -> map.values().stream().mapToLong(Pair::getFirst).sum())
                .orElse(0L);
    }

    public long getTotalLineCount() {
        return Optional.ofNullable(fileWeightsMap)
                .map(map -> map.values().stream().mapToLong(Pair::getSecond).sum())
                .orElse(0L);
    }
}
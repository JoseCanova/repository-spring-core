package org.nanotek.opencsv.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nanotek.config.CsvFileConfigurations;
import org.nanotek.opencsv.file.CsvFileItemConcreteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsvDataWeightCalculator {

    @Autowired
    private CsvFileConfigurations<?,?,?,?> csvFileConfigurations;

    // This map will store the calculated weights (size and lines)
    // It will be populated when calculateAllFileWeights() is called.
    private Map<String, Pair<Long, Long>> fileWeightsMap;

    public CsvDataWeightCalculator() {
    }

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
                    // Delegate the actual single file calculation to the new method
                    return new AbstractMap.SimpleEntry<>(configName, calculateFileWeight(configName));
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        ConcurrentHashMap::new));

        this.fileWeightsMap = calculatedWeights;
        return calculatedWeights;
    }

    /**
     * Calculates the file size and line count for a specific CSV configuration.
     * This method is designed to be called for a single configuration.
     *
     * @param configName The key of the CSV configuration (e.g., "area", "artist").
     * @return A Pair<FileSizeInBytes, LineCount> for the specified configuration.
     * Returns Pair.of(0L, 0L) if the configuration is not found or the file cannot be read.
     */
    public Pair<Long, Long> calculateFileWeight(String configName) {
        CsvFileItemConcreteStrategy<?,?,?,?> strategy = csvFileConfigurations.getCsvConfigs().get(configName);

        if (strategy == null) {
            System.err.println("ERROR: Configuration '" + configName + "' not found in CsvFileConfigurations.");
            return Pair.of(0L, 0L);
        }

        String fileLocation = strategy.getFileLocation();
        String fileName = strategy.getFileName();

        System.out.println("DEBUG: Calculating weight for specific config: " + configName);
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
        return Pair.of(size, lines);
    }

    /**
     * Returns the size of a specific CSV file in bytes from the last calculation.
     * This method relies on `calculateAllFileWeights()` having been called.
     * @param configName The name of the CSV configuration.
     * @return The file size in bytes, or 0 if not found/error or not yet calculated.
     */
    public long getFileSize(String configName) {
        return Optional.ofNullable(fileWeightsMap)
                .map(map -> map.get(configName))
                .map(Pair::getFirst)
                .orElse(0L);
    }

    /**
     * Returns the number of lines (records) in a specific CSV file from the last calculation.
     * This method relies on `calculateAllFileWeights()` having been called.
     * @param configName The name of the CSV configuration.
     * @return The line count, or 0 if not found/error or not yet calculated.
     */
    public long getLineCount(String configName) {
        return Optional.ofNullable(fileWeightsMap)
                .map(map -> map.get(configName))
                .map(Pair::getSecond)
                .orElse(0L);
    }

    /**
     * Returns a map of all file weights (size and lines) from the last calculation.
     * @return An unmodifiable map of file weights, or empty map if not yet calculated.
     */
    public Map<String, Pair<Long, Long>> getAllFileWeights() {
        return Optional.ofNullable(fileWeightsMap)
                .map(Map::copyOf)
                .orElse(Map.of());
    }

    /**
     * Returns the total estimated file size across all configured CSVs from the last calculation.
     * @return Total file size in bytes, or 0 if not yet calculated.
     */
    public long getTotalFileSize() {
        return Optional.ofNullable(fileWeightsMap)
                .map(map -> map.values().stream().mapToLong(Pair::getFirst).sum())
                .orElse(0L);
    }

    /**
     * Returns the total estimated number of lines across all configured CSVs from the last calculation.
     * @return Total line count, or 0 if not yet calculated.
     */
    public long getTotalLineCount() {
        return Optional.ofNullable(fileWeightsMap)
                .map(map -> map.values().stream().mapToLong(Pair::getSecond).sum())
                .orElse(0L);
    }
}
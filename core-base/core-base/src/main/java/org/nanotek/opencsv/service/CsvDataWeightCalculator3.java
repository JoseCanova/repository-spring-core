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
public class CsvDataWeightCalculator3 {

    @Autowired
    private CsvFileConfigurations<?,?,?,?> csvFileConfigurations;

    // This map will store the calculated weights (size and lines)
    // It will be populated when calculateAllFileWeights() is called.
    private Map<String, Pair<Long, Long>> fileWeightsMap;

    public CsvDataWeightCalculator3() {
    }

    public void initialize() {
        this.csvFileConfigurations
                .getCsvConfigs()
                .values()
                .forEach(v -> v.afterPropertiesSet());
    }

    /**
     * Calculates the file size and line count for all configured CSV files (exact measure).
     * This method will be called explicitly when the weights are needed.
     *
     * @return A Map where key is configName (String) and value is Pair<FileSizeInBytes, LineCount>.
     */
    public Map<String, Pair<Long, Long>> calculateAllFileWeights() {
        System.out.println("\n--- CsvDataWeightCalculator: Calculating All File Weights (Exact) ---");

        Map<String, Pair<Long, Long>> calculatedWeights = csvFileConfigurations
                .getCsvConfigs()
                .entrySet().stream()
                .map(entry -> {
                    String configName = entry.getKey();
                    // Delegate the actual single file calculation to the exact method
                    return new AbstractMap.SimpleEntry<>(configName, calculateFileWeight(configName));
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        ConcurrentHashMap::new));

        this.fileWeightsMap = calculatedWeights; // Store the result for subsequent calls if needed
        return calculatedWeights;
    }

    /**
     * Calculates the file size and line count for a specific CSV configuration (exact measure).
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

        System.out.println("DEBUG: Calculating exact weight for specific config: " + configName);
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
     * Calculates the file size and ESTIMATED line count for all configured CSV files.
     * This method uses a sample of lines to estimate the total line count.
     *
     * @param sampleSize The number of lines to read for calculating average line size.
     * @return A Map where key is configName (String) and value is Pair<FileSizeInBytes, EstimatedLineCount>.
     */
    public Map<String, Pair<Long, Long>> calculateAllFileEstimatedWeights(int sampleSize) {
        System.out.println(String.format("\n--- CsvDataWeightCalculator: Calculating All File Weights (Estimated with %d lines sample) ---", sampleSize));

        Map<String, Pair<Long, Long>> estimatedWeights = csvFileConfigurations
                .getCsvConfigs()
                .entrySet().stream()
                .map(entry -> {
                    String configName = entry.getKey();
                    // Delegate the actual single file estimation to the new method
                    return new AbstractMap.SimpleEntry<>(configName, calculateFileEstimatedWeight(configName, sampleSize));
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        ConcurrentHashMap::new));

        // Note: We are not storing estimated weights in 'fileWeightsMap' as it's for exact values.
        // If you need to cache estimated weights, create a separate map for them.
        return estimatedWeights;
    }

    /**
     * Calculates the file size and ESTIMATED line count for a specific CSV configuration.
     * This method uses a sample of lines to estimate the total line count.
     *
     * @param configName The key of the CSV configuration.
     * @param sampleSize The number of lines to read for calculating average line size.
     * @return A Pair<FileSizeInBytes, EstimatedLineCount> for the specified configuration.
     * Returns Pair.of(0L, 0L) if the configuration is not found or the file cannot be read.
     */
    public Pair<Long, Long> calculateFileEstimatedWeight(String configName, int sampleSize) {
        CsvFileItemConcreteStrategy<?,?,?,?> strategy = csvFileConfigurations.getCsvConfigs().get(configName);

        if (strategy == null) {
            System.err.println("ERROR: Configuration '" + configName + "' not found for estimation.");
            return Pair.of(0L, 0L);
        }

        String fileLocation = strategy.getFileLocation();
        String fileName = strategy.getFileName();

        System.out.println("DEBUG: Estimating weight for specific config: " + configName);
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

        long totalFileSize = 0;
        long estimatedLineCount = 0;

        if (file.exists() && file.isFile() && file.canRead()) {
            try {
                totalFileSize = Files.size(filePath); // Get total file size quickly

                long sampleBytes = 0;
                long actualLinesInSample = 0;

                // Read a sample of lines to calculate average line size
                try (Stream<String> lineStream = Files.lines(filePath)) {
                    for (String line : lineStream.limit(sampleSize).collect(Collectors.toList())) { // Collect to list to iterate
                        sampleBytes += line.getBytes().length + System.lineSeparator().getBytes().length; // Include line separator
                        actualLinesInSample++;
                    }
                }

                if (actualLinesInSample > 0 && sampleBytes > 0) {
                    double averageLineSize = (double) sampleBytes / actualLinesInSample;
                    estimatedLineCount = (long) (totalFileSize / averageLineSize);
                } else {
                    // Handle cases where sample is empty or contains only empty lines
                    estimatedLineCount = 0;
                }

                // If the file is smaller than the sample size, the estimate is the actual count
                if (totalFileSize > 0 && actualLinesInSample > 0 && totalFileSize < sampleBytes) {
                    try (Stream<String> fullLineStream = Files.lines(filePath)) {
                        estimatedLineCount = fullLineStream.count();
                    }
                } else if (totalFileSize == 0) {
                    estimatedLineCount = 0;
                }


                System.err.println(String.format("  - %s: Total Size=%,d bytes, Estimated Lines=%,d (Sampled %d lines)",
                                                 configName, totalFileSize, estimatedLineCount, actualLinesInSample));

            } catch (IOException e) {
                System.err.println("  - Error reading file for estimation of " + configName + " at " + filePath + ": " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.err.println("  - File not found, not a regular file, or not readable for estimation of " + configName + " at " + filePath);
        }
        return Pair.of(totalFileSize, estimatedLineCount);
    }

    // The following methods will now rely on calculateAllFileWeights() being called
    // or you can call calculateAllFileEstimatedWeights() and use its returned map.
    // For simplicity, I'll keep them relying on fileWeightsMap, meaning you'd call
    // calculateAllFileWeights() if you want exact, or manage the estimated map separately.

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
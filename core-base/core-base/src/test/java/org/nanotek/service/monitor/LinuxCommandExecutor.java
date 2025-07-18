package org.nanotek.service.monitor; // A new package for monitoring utilities

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class to execute Linux command-line commands and capture their output.
 * It uses ProcessBuilder for robust external process execution.
 */
public class LinuxCommandExecutor {

    /**
     * Executes a given command and returns its standard output as a list of strings.
     * Errors are redirected to standard output for easier capture.
     *
     * @param command The command and its arguments as an array of strings.
     * @return A list of strings, where each string is a line from the command's output.
     * @throws IOException If an I/O error occurs while starting or reading from the process.
     * @throws InterruptedException If the current thread is interrupted while waiting for the process to terminate.
     */
    public static List<String> executeCommand(String... command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(command);
        processBuilder.redirectErrorStream(true); // Redirect error stream to standard output for unified capture

        Process process = processBuilder.start();
        List<String> output = new ArrayList<>();

        // Read the output from the command's standard output stream
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            output = reader.lines().collect(Collectors.toList()); // Efficiently collect all lines
        }

        int exitCode = process.waitFor(); // Wait for the command to complete and get its exit code
        if (exitCode != 0) {
            // Log or handle non-zero exit codes (command failed)
            System.err.println("Command exited with error code: " + exitCode + " for command: " + Arrays.toString(command));
            // Optionally, you might want to throw a custom exception here
            // throw new RuntimeException("Command failed: " + Arrays.toString(command) + ", Exit Code: " + exitCode);
        }
        return output;
    }

    // Main method for simple standalone testing of the executor (not for Spring context)
    public static void main(String[] args) {
        try {
            System.out.println("--- Executing 'df -B1' ---");
            List<String> dfOutput = executeCommand("df", "-B1");
            dfOutput.forEach(System.out::println);

            System.out.println("\n--- Executing 'lsblk -o NAME,SIZE,MOUNTPOINT' ---");
            List<String> lsblkOutput = executeCommand("lsblk", "-o", "NAME,SIZE,MOUNTPOINT");
            lsblkOutput.forEach(System.out::println);

            // Example of a command that might fail (e.g., non-existent command)
            System.out.println("\n--- Executing 'nonexistent_command' (expected to fail) ---");
            List<String> failOutput = executeCommand("nonexistent_command");
            failOutput.forEach(System.out::println);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

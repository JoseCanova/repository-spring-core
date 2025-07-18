package org.nanotek.service.monitor; // Test package for monitoring utilities

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest; // Use SpringBootTest for full context if needed, or just regular @Test
import org.springframework.test.context.junit.jupiter.SpringExtension; // For SpringBootTest

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

// @ExtendWith(SpringExtension.class) // Uncomment if you need Spring context for other parts of the test
// @SpringBootTest // Uncomment if you need Spring context for other parts of the test
public class LinuxDriveMetricsIntegrationTest {

    private static final Logger log = LoggerFactory.getLogger(LinuxDriveMetricsIntegrationTest.class);

    /**
     * Tests the execution of 'df -B1' command and parsing its output.
     * This test is enabled only on Linux operating systems.
     */
    @Test
    @EnabledOnOs(OS.LINUX) // This annotation ensures the test only runs on Linux environments
    void testDfCommandAndParsing() throws IOException, InterruptedException {
        log.info("Running testDfCommandAndParsing on Linux.");

        // 1. Execute the 'df -B1' command
        List<String> dfOutput = LinuxCommandExecutor.executeCommand("df", "-B1");

        // Assert that output is not null and contains at least a header and one data line
        assertNotNull(dfOutput, "df command output should not be null.");
        assertFalse(dfOutput.isEmpty(), "df command output should not be empty.");
        assertTrue(dfOutput.size() > 1, "df command output should contain at least a header and one filesystem.");

        log.info("Raw 'df -B1' output lines: {}", dfOutput.size());
        dfOutput.forEach(line -> log.debug("DF Raw: {}", line)); // Log raw output for debugging

        // 2. Parse the output using DiskSpaceParser
        Map<String, Long> mountPointSizes = DiskSpaceParser.parseDfOutput(dfOutput);

        // Assert that the parsed map is not null and not empty (assuming a typical Linux system has mounted filesystems)
        assertNotNull(mountPointSizes, "Parsed mount point sizes map should not be null.");
        assertFalse(mountPointSizes.isEmpty(), "Parsed mount point sizes map should not be empty. Check 'df -B1' output and parser regex.");

        log.info("Parsed Disk Space Metrics:");
        mountPointSizes.forEach((mountPoint, size) -> {
            log.info("  Mount Point: {}, Total Size: {} bytes ({} GB)", mountPoint, size, String.format("%.2f", (double) size / (1024 * 1024 * 1024)));
            assertTrue(size > 0, "Filesystem " + mountPoint + " should have a size greater than 0.");
        });

        // Optional: Assert for specific known mount points if your test environment is consistent
        // For example, check for root filesystem
        // assertTrue(mountPointSizes.containsKey("/"), "Root filesystem '/' should be present.");
        // assertTrue(mountPointSizes.get("/") > 0, "Root filesystem size should be greater than 0.");
    }

    /**
     * Tests the execution of 'lsblk' command.
     * This test is enabled only on Linux operating systems.
     */
    @Test
    @EnabledOnOs(OS.LINUX)
    void testLsblkCommand() throws IOException, InterruptedException {
        log.info("Running testLsblkCommand on Linux.");

        // Execute the 'lsblk -o NAME,SIZE,MOUNTPOINT' command
        List<String> lsblkOutput = LinuxCommandExecutor.executeCommand("lsblk", "-o", "NAME,SIZE,MOUNTPOINT");

        assertNotNull(lsblkOutput, "lsblk command output should not be null.");
        assertFalse(lsblkOutput.isEmpty(), "lsblk command output should not be empty.");
        assertTrue(lsblkOutput.size() > 1, "lsblk command output should contain at least a header and one device.");

        log.info("Raw 'lsblk' output lines: {}", lsblkOutput.size());
        lsblkOutput.forEach(line -> log.debug("LSBLK Raw: {}", line)); // Log raw output for debugging

        // You would typically parse this output further to extract specific device info.
        // For this test, we just verify successful execution and non-empty output.
    }

    /**
     * Tests the execution of 'iostat' command (requires sysstat package).
     * This test is enabled only on Linux operating systems.
     */
    @Test
    @EnabledOnOs(OS.LINUX)
    void testIostatCommand() throws IOException, InterruptedException {
        log.info("Running testIostatCommand on Linux.");

        // iostat requires the 'sysstat' package to be installed (e.g., sudo apt install sysstat)
        // It also often requires a short delay for statistics to gather, hence '1 2' (1 second interval, 2 reports)
        List<String> iostatOutput = null;
        try {
            iostatOutput = LinuxCommandExecutor.executeCommand("iostat", "-dkx", "1", "2");
        } catch (IOException e) {
            log.warn("Could not execute 'iostat'. Is 'sysstat' package installed? Error: {}", e.getMessage());
            // If iostat is not installed, this test will gracefully skip assertions for its output.
            return; // Skip further assertions if command failed to execute
        }

        assertNotNull(iostatOutput, "iostat command output should not be null.");
        assertFalse(iostatOutput.isEmpty(), "iostat command output should not be empty.");
        assertTrue(iostatOutput.size() > 5, "iostat command output should contain multiple sections."); // Expect more lines for iostat

        log.info("Raw 'iostat' output lines: {}", iostatOutput.size());
        iostatOutput.forEach(line -> log.debug("IOSTAT Raw: {}", line)); // Log raw output for debugging

        // Further parsing of iostat output would be complex and specific to metrics needed.
        // For this test, we just verify successful execution and non-empty output.
    }
}

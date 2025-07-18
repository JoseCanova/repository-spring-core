package org.nanotek.service.monitor; // A new package for monitoring utilities

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for parsing the output of Linux 'df -B1' command.
 * It extracts mount point and total size in bytes.
 */
public class DiskSpaceParser {

    // Regex pattern to match lines from 'df -B1' output.
    // Group 1: Filesystem name (e.g., /dev/sda1)
    // Group 2: Total size in bytes
    // Group 3: Mounted on path (e.g., /mnt/data)
    private static final Pattern DF_PATTERN = Pattern.compile("^(\\S+)\\s+(\\d+)\\s+\\d+\\s+\\d+\\s+\\d+%\\s+(.+)$");

    /**
     * Parses the output of the 'df -B1' command and extracts total size for each mounted filesystem.
     * It filters out non-physical filesystems like tmpfs and devtmpfs.
     *
     * @param dfOutput A list of strings, where each string is a line from the 'df -B1' command's output.
     * @return A Map where the key is the mount point (String) and the value is the total size (Long) in bytes.
     */
    public static Map<String, Long> parseDfOutput(List<String> dfOutput) {
        Map<String, Long> mountPointSizes = new HashMap<>();
        // Skip the header line (usually the first line)
        for (int i = 1; i < dfOutput.size(); i++) {
            String line = dfOutput.get(i);
            Matcher matcher = DF_PATTERN.matcher(line);
            if (matcher.matches()) {
                String filesystem = matcher.group(1);
                long totalSize = Long.parseLong(matcher.group(2));
                String mountPoint = matcher.group(3);

                // Filter out non-physical filesystems and ensure it's a mounted path
                // This heuristic helps focus on actual disk partitions
                if (mountPoint.startsWith("/") && !filesystem.startsWith("tmpfs") && !filesystem.startsWith("devtmpfs") && !filesystem.startsWith("overlay")) {
                    mountPointSizes.put(mountPoint, totalSize);
                }
            }
        }
        return mountPointSizes;
    }
}

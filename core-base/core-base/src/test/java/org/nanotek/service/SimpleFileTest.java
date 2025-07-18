package org.nanotek.service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class SimpleFileTest {
    public static void main(String[] args) {
        // IMPORTANT: Paste the EXACT "Resolved Path" string from your debug output here
        String filePathString = "/mnt/wwn-part2/musicbrainz/mbdump/artist_alias";
        Path filePath = Paths.get(filePathString);
        File file = filePath.toFile();

        System.out.println("--- SimpleFileTest ---");
        System.out.println("Testing Path: " + filePath.toAbsolutePath());
        System.out.println("File Exists: " + file.exists());
        System.out.println("Is File: " + file.isFile());
        System.out.println("Can Read: " + file.canRead());

        try {
            long size = Files.size(filePath);
            System.out.println("Reported Size: " + size + " bytes");

            long lines = 0;
            try (Stream<String> lineStream = Files.lines(filePath)) {
                lines = lineStream.count();
            }
            System.out.println("Reported Lines: " + lines);

        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("--- Test Complete ---");
    }
}
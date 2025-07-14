import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Collections; // For potential shuffling if desired, not used here but good to know

public class AlphabeticUUIDGenerator2 {

    public static void main(String[] args) {

        List<UUID> allUuids = new ArrayList<>();

        System.out.println("--- Generating UUIDs ---");
        for (char ch = 'a'; ch <= 'z'; ch++) {
            UUID uuid = generateUUID(ch);
            if (uuid != null) {
                System.out.println("UUID for '" + ch + "': " + uuid);
                allUuids.add(uuid);
            }
        }

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            UUID uuid = generateUUID(ch);
            if (uuid != null) {
                System.out.println("UUID for '" + ch + "': " + uuid);
                allUuids.add(uuid);
            }
        }

        System.out.println("\n--- Calculating Levenshtein Distances Between All Pairs ---");

        // Iterate through all unique pairs of UUIDs
        for (int i = 0; i < allUuids.size(); i++) {
            for (int j = i + 1; j < allUuids.size(); j++) {
                UUID uuid1 = allUuids.get(i);
                UUID uuid2 = allUuids.get(j);

                String s1 = uuid1.toString();
                String s2 = uuid2.toString();

                int distance = calculateLevenshteinDistance(s1, s2);

                // Optional: You might want to map back to the original character for clarity
                // This would require storing the character along with the UUID in a Map or custom class
                // For now, we'll just print the UUIDs.

                System.out.printf("Distance between %s and %s: %d%n",
                                  s1, s2, distance);
            }
        }
    }

    /**
     * Generates a deterministic UUID for a given character by serializing its char[] representation.
     *
     * @param character The character for which to generate the UUID.
     * @return The generated UUID, or null if an error occurs.
     */
    private static UUID generateUUID(char character) {
        UUID uuid = null;
        try (ByteArrayOutputStream bao = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bao)) {

            // Serialize the character as a char array, matching your original sample
            oos.writeObject(new char[]{character});
            oos.flush();

            uuid = UUID.nameUUIDFromBytes(bao.toByteArray());

        } catch (Exception ex) {
            System.err.println("Error generating UUID for '" + character + "': " + ex.getMessage());
            ex.printStackTrace();
        }
        return uuid;
    }

    /**
     * Calculates the Levenshtein distance (edit distance) between two strings.
     * This implementation uses a dynamic programming approach.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return The Levenshtein distance between s1 and s2.
     */
    public static int calculateLevenshteinDistance(String s1, String s2) {
        // s1 is the source, s2 is the target
        int m = s1.length();
        int n = s2.length();

        // dp[i][j] stores the Levenshtein distance between
        // the first i characters of s1 and the first j characters of s2
        int[][] dp = new int[m + 1][n + 1];

        // Initialize base cases:
        // Distance from empty string to s1 (i characters) is i insertions
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        // Distance from s2 (j characters) to empty string is j deletions
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;

                dp[i][j] = Math.min(
                    dp[i - 1][j] + 1,       // Deletion from s1
                    Math.min(
                        dp[i][j - 1] + 1,   // Insertion into s1
                        dp[i - 1][j - 1] + cost // Substitution (or match)
                    )
                );
            }
        }

        // The bottom-right cell contains the Levenshtein distance
        return dp[m][n];
    }
}
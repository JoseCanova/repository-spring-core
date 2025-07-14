/**
 * To generate a Type 7 UUID in Java, you'll need to combine a current Unix Epoch timestamp with random bits, following the structure defined in RFC 9562.

Here's a conceptual algorithm and a basic Java implementation. Keep in mind that a production-grade library would handle details like clock synchronization, potential clock regressions, and more robust random number generation for the specific bit ranges with higher precision.

Type 7 UUID Algorithm (Conceptual Steps):
Get Timestamp: Obtain the current Unix Epoch timestamp in milliseconds. This will form the most significant 48 bits of the UUID.

Generate Random Bits: Generate enough cryptographically secure random bits for the remaining parts of the UUID (12 bits for rand_a and 62 bits for rand_b).

Construct mostSigBits (64-bit Long):

Shift the 48-bit timestamp to the most significant position.

Set the Version bits to 0111 (binary, which is 7 in hexadecimal). This occupies 4 bits.

Combine with the rand_a bits to fill the remaining 12 bits of the first 64-bit segment.

Construct leastSigBits (64-bit Long):

Set the Variant bits to 10 (binary, which corresponds to 8, 9, A, or B in the first hex digit of the fourth group). This occupies 2 bits.

Combine with the rand_b bits to fill the remaining 62 bits of the second 64-bit segment.

Create UUID Object: Instantiate java.util.UUID using the constructed mostSigBits and leastSigBits.
 */
import java.security.SecureRandom;
import java.util.UUID;

public class UUIDv7Generator {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    /**
     * Generates a Type 7 UUID based on RFC 9562.
     * Combines a Unix Epoch timestamp in milliseconds with random bits.
     *
     * @return A new UUID of Type 7.
     */
    public static UUID generateType7UUID() {
        // 1. Get current Unix Epoch timestamp in milliseconds (48 bits)
        long unixEpochMs = System.currentTimeMillis();

        // 2. Generate 62 cryptographically secure random bits for rand_b
        //    and 12 for rand_a.
        //    Using nextLong() which gives 64 bits, then mask/shift as needed.
        long randA_and_B = SECURE_RANDOM.nextLong(); // Provides 64 random bits

        // --- Construct Most Significant Bits (MSB - 64 bits) ---
        // Layout:  48-bit time_ms | 4-bit version | 12-bit rand_a

        long mostSigBits = 0L;

        // Apply time_ms (most significant 48 bits)
        // Shift left by 16 to make room for version (4 bits) and rand_a (12 bits)
        mostSigBits |= (unixEpochMs & 0xFFFFFFFFFFFFL) << 16; // Mask to 48 bits, then shift

        // Apply Version (4 bits - '0111' for UUIDv7)
        // The version bits are at positions 12-15 from the right of the MSB
        mostSigBits |= (0x7L << 12); // 0x7 is binary 0111

        // Apply rand_a (12 bits)
        // Take the lower 12 bits from our random long and place them
        // at the right-most 12 bits of the MSB
        mostSigBits |= (randA_and_B >>> 52); // Use top 12 bits of randA_and_B for rand_a


        // --- Construct Least Significant Bits (LSB - 64 bits) ---
        // Layout:  2-bit variant | 62-bit rand_b

        long leastSigBits = 0L;

        // Apply Variant (2 bits - '10' for RFC 4122 variant)
        // The variant bits are at positions 62-63 from the right of the LSB
        leastSigBits |= (0x2L << 62); // 0x2 is binary 10, shift to correct position

        // Apply rand_b (62 bits)
        // Take the remaining 62 bits from our random long
        leastSigBits |= (randA_and_B & 0x3FFFFFFFFFFFFFFFL); // Mask to lower 62 bits

        return new UUID(mostSigBits, leastSigBits);
    }

    public static void main(String[] args) {
        System.out.println("Generating 5 Type 7 UUIDs:");
        for (int i = 0; i < 5; i++) {
            UUID uuid = generateType7UUID();
            System.out.println(uuid);
        }

        // You can verify the timestamp component (first 12 hex digits roughly)
        // and that the 13th hex digit is '7' (version).
        // The 17th hex digit (first of 4th group) should be 8, 9, a, or b (variant).
    }
}
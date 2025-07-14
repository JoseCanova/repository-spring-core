import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public interface UUID8{
	public static UUID generateType8UUID(String dataToHash) throws NoSuchAlgorithmException {
		long unixEpochMs = System.currentTimeMillis();
		byte[] hash = MessageDigest.getInstance("MD5").digest(dataToHash.getBytes());

		// Convert hash bytes to long values (simplified for example)
		long dataHashLong1 = 0L;
		for (int i = 0; i < 8; i++) { // First 8 bytes for data_hash_a and part of data_hash_b
			dataHashLong1 = (dataHashLong1 << 8) | (hash[i] & 0xFF);
		}
		long dataHashLong2 = 0L;
		for (int i = 8; i < 16; i++) { // Remaining 8 bytes for data_hash_b
			dataHashLong2 = (dataHashLong2 << 8) | (hash[i] & 0xFF);
		}

		// --- Construct Most Significant Bits (MSB - 64 bits) ---
		long mostSigBits = 0L;
		mostSigBits |= (unixEpochMs & 0xFFFFFFFFFFFFL) << 16; // Time_ms (48 bits)
		mostSigBits |= (0x8L << 12); // Custom "Type 8" version (or use 7 if you stick to RFC)
		mostSigBits |= (dataHashLong1 >>> 52); // Use 12 bits from hash for data_hash_a

		// --- Construct Least Significant Bits (LSB - 64 bits) ---
		long leastSigBits = 0L;
		leastSigBits |= (0x2L << 62); // RFC 4122 Variant
		leastSigBits |= ( (dataHashLong1 & 0x3FFFFFFFFFFFFFFFL) << 12 ) | (dataHashLong2 >>> 52); // Use remaining 62 bits from hash for data_hash_b

		return new UUID(mostSigBits, leastSigBits);
	}
}
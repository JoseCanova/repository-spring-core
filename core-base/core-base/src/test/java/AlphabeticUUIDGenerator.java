import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.UUID;

public class AlphabeticUUIDGenerator {

    public static void main(String[] args) {

        System.out.println("--- UUIDs for Lowercase Letters (a-z) ---");
        for (char ch = 'a'; ch <= 'z'; ch++) {
            generateAndPrintUUID(ch);
        }

        System.out.println("\n--- UUIDs for Uppercase Letters (A-Z) ---");
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            generateAndPrintUUID(ch);
        }
    }

    private static void generateAndPrintUUID(char character) {
        UUID uuid = null;
        try (ByteArrayOutputStream bao = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bao)) {

            // Serialize the character as a char array, matching your original sample
            oos.writeObject(new char[]{character});
            oos.flush();

            uuid = UUID.nameUUIDFromBytes(bao.toByteArray());
            System.out.println("UUID for '" + character + "': " + uuid);

        } catch (Exception ex) {
            System.err.println("Error generating UUID for '" + character + "': " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
import java.util.*;

public class PasswordGenerator {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS   = "0123456789";
    private static final String SPECIAL   = "!@#$%^&*()-_=+[]{}|;:,.<>?";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🔐 Password Generator 🔐");

        System.out.print("Enter password length: ");
        int length = scanner.nextInt();

        System.out.print("Include lowercase letters? (y/n): ");
        boolean useLower = scanner.next().equalsIgnoreCase("y");

        System.out.print("Include uppercase letters? (y/n): ");
        boolean useUpper = scanner.next().equalsIgnoreCase("y");

        System.out.print("Include numbers? (y/n): ");
        boolean useNumbers = scanner.next().equalsIgnoreCase("y");

        System.out.print("Include special characters? (y/n): ");
        boolean useSpecial = scanner.next().equalsIgnoreCase("y");

        String charPool = "";
        if (useLower) charPool += LOWERCASE;
        if (useUpper) charPool += UPPERCASE;
        if (useNumbers) charPool += NUMBERS;
        if (useSpecial) charPool += SPECIAL;

        if (charPool.isEmpty()) {
            System.out.println("❌ You must select at least one character type.");
            return;
        }

        String password = generatePassword(length, charPool);
        System.out.println("✅ Generated Password: " + password);
    }

    private static String generatePassword(int length, String charPool) {
        StringBuilder password = new StringBuilder();
        Random rand = new Random();

        for (int i = 0; i < length; i++) {
            int idx = rand.nextInt(charPool.length());
            password.append(charPool.charAt(idx));
        }

        return password.toString();
    }
}

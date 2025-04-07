import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class UserAuthSystem {
    private static final String USERS_FILE = "users.txt";
    private static final Map<String, String> users = new HashMap<>();

    public static void main(String[] args) {
        loadUsers();
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("🔐 User Authentication System");
        do {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. View All Users (Admin 👀)");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // eat newline

            switch (choice) {
                case 1 -> register(sc);
                case 2 -> login(sc);
                case 3 -> printUsers();
                case 0 -> System.out.println("👋 Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
        sc.close();
        saveUsers();
    }

    private static void register(Scanner sc) {
        System.out.print("Choose a username: ");
        String username = sc.nextLine();
        if (users.containsKey(username)) {
            System.out.println("❌ Username already exists.");
            return;
        }

        System.out.print("Enter a password: ");
        String password = sc.nextLine();
        String hash = hashPassword(password);

        users.put(username, hash);
        saveUsers();
        System.out.println("✅ Registered successfully!");
    }

    private static void login(Scanner sc) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        String storedHash = users.get(username);
        if (storedHash != null && storedHash.equals(hashPassword(password))) {
            System.out.println("✅ Login successful!");
        } else {
            System.out.println("❌ Invalid credentials.");
        }
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return password; // fallback
        }
    }

    private static void loadUsers() {
        File file = new File(USERS_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2)
                    users.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error loading users.");
        }
    }

    private static void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (var entry : users.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error saving users.");
        }
    }

    private static void printUsers() {
        System.out.println("📄 Registered Users:");
        for (String user : users.keySet()) {
            System.out.println("- " + user);
        }
    }
}

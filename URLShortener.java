import java.io.*;
import java.util.*;

public class URLShortener {
    private static final String DATA_FILE = "urls.txt";
    private static final String DOMAIN = "http://short.ly/";
    private static final Map<String, String> shortToLong = new HashMap<>();
    private static final Map<String, String> longToShort = new HashMap<>();
    private static final Random random = new Random();

    public static void main(String[] args) {
        loadDatabase();
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("🔗 URL Shortener");
        do {
            System.out.println("\n1. Shorten a URL");
            System.out.println("2. Retrieve a URL");
            System.out.println("3. View All URLs");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter long URL: ");
                    String longUrl = scanner.nextLine();
                    String shortUrl = shortenURL(longUrl);
                    System.out.println("Shortened: " + shortUrl);
                }
                case 2 -> {
                    System.out.print("Enter short URL: ");
                    String shortUrl = scanner.nextLine();
                    String longUrl = retrieveURL(shortUrl);
                    System.out.println(longUrl == null ? "❌ Not found!" : "Original: " + longUrl);
                }
                case 3 -> {
                    System.out.println("📄 All Stored URLs:");
                    for (String s : shortToLong.keySet()) {
                        System.out.println(DOMAIN + s + " -> " + shortToLong.get(s));
                    }
                }
                case 0 -> System.out.println("👋 Goodbye!");
                default -> System.out.println("❓ Invalid option");
            }
        } while (choice != 0);
        scanner.close();
        saveDatabase();
    }

    private static String shortenURL(String longUrl) {
        if (longToShort.containsKey(longUrl)) {
            return DOMAIN + longToShort.get(longUrl);
        }

        String shortId;
        do {
            shortId = generateRandomID();
        } while (shortToLong.containsKey(shortId));

        shortToLong.put(shortId, longUrl);
        longToShort.put(longUrl, shortId);
        return DOMAIN + shortId;
    }

    private static String retrieveURL(String shortUrl) {
        if (!shortUrl.startsWith(DOMAIN)) return null;
        String shortId = shortUrl.substring(DOMAIN.length());
        return shortToLong.get(shortId);
    }

    private static String generateRandomID() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private static void loadDatabase() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    shortToLong.put(parts[0], parts[1]);
                    longToShort.put(parts[1], parts[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error reading file.");
        }
    }

    private static void saveDatabase() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (String shortId : shortToLong.keySet()) {
                writer.write(shortId + "," + shortToLong.get(shortId));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error saving file.");
        }
    }
}

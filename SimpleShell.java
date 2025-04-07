import java.io.*;
import java.util.*;

public class SimpleShell {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line;

        System.out.println("🖥️ Welcome to SimpleShell");
        System.out.println("Type 'exit' to quit\n");

        while (true) {
            System.out.print("> ");
            line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("exit")) {
                System.out.println("👋 Exiting SimpleShell...");
                break;
            }

            if (line.isEmpty()) continue;

            try {
                List<String> parts = List.of(line.split("\\s+"));
                ProcessBuilder pb = new ProcessBuilder(parts);
                pb.redirectErrorStream(true);
                Process process = pb.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String outputLine;
                while ((outputLine = reader.readLine()) != null) {
                    System.out.println(outputLine);
                }

                process.waitFor();
            } catch (IOException e) {
                System.out.println("❌ Command not found or failed: " + e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("⚠️ Command interrupted.");
            }
        }

        scanner.close();
    }
}

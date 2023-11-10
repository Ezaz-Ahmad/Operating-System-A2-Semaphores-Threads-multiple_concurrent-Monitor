import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class P1 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the filename as a command-line argument.");
            return;
        }

        String filename = args[0];
        int E = 0;
        int P = 0;
        int N = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); 
            if (line != null) {
                String[] parts = line.split(",");
                for (String part : parts) {
                    if (part.trim().startsWith("E=")) {
                        E = Integer.parseInt(part.split("=")[1].trim());
                    } else if (part.trim().startsWith("P=")) {
                        P = Integer.parseInt(part.split("=")[1].trim());
                    } else if (part.trim().startsWith("N=")) {
                        N = Integer.parseInt(part.split("=")[1].trim());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        for (int i = 1; i <= E; i++) {
            new Thread(new Traveler("E_H" + i, "E", N)).start();
        }

        for (int i = 1; i <= P; i++) {
            new Thread(new Traveler("P_A" + i, "P", N)).start();
        }
    }
}

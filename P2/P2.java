import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class P2 {

    public static void main(String[] args) {
        // Initializing the server
        System.out.println("Server initialised...");

        String filename = args[0];
        List<String> taskFiles = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    taskFiles.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        taskFiles.forEach(taskFile -> {
            String[] parts = taskFile.split("\\s+");
            String taskFileName = parts[0];
            int processors = -1;
            try {
                // Retrieve processor count, which is expected to be the last element
                processors = Integer.parseInt(parts[parts.length - 1]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid processor count in: " + taskFile);
                return;
            }

            // List to store inputs from each task file
            List<Integer> inputs = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(taskFileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {

                        try {
                            inputs.add(Integer.parseInt(line));
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid integer in file: " + taskFileName);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Iterate over each input, create a thread and start processing
            for (int i = 0; i < inputs.size(); i++) {
                int input = inputs.get(i);
                // Creating a thread for each input
                Thread taskThread = new TaskThread(input, i, taskFileName);
                taskThread.start();
                try {
                    // Waittinng for the thread to complete its execution
                    taskThread.join(); // Wait for the thread to finish
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("All files processed.");
    }
}

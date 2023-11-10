import java.nio.file.*;
import java.io.*;
import java.util.*;

public class P3 {

    // Main entry point of the program from where the program will run
    public static void main(String[] args) throws IOException {

        // Initializeing the ClusterMonitor which will help in order to to manage the synchronization of tasks
        ClusterMonitor clusterMonitor = new ClusterMonitor();

        // Reading the provided file in order  to getting the tasks details
        String filename = args[0];

        // Reading all lines from the provided file
        List<String> lines = Files.readAllLines(Paths.get(filename));


        List<Task> tasks = new ArrayList<>();

        // extracting task details, and createing Task objects
        for (String line : lines) {
            String[] parts = line.split("\\s+");

            // Checking for valid line format
            if (parts.length != 2) {
                System.out.println("Invalid line format: " + line);
                continue;
            }

            // Extracting task filename and the number of processors required
            String taskFileName = parts[0];
            int processors = Integer.parseInt(parts[1].trim());
            tasks.add(new Task(taskFileName, processors, clusterMonitor));
        }
        System.out.println("Server initialised...");

        // Starting the each task in its own thread
        for (Task task : tasks) {
            new Thread(task).start();
        }
    }
}

import java.nio.file.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Task implements Runnable {
    private String filename;
    private int processors;
    private ClusterMonitor monitor;

    // Constructor to initialize a task with its filename, processors, and cluster monitor.
    public Task(String filename, int processors, ClusterMonitor monitor) {
        this.filename = filename;
        this.processors = processors;
        this.monitor = monitor;
    }

    // Overriding the run method of Runnable to define the logic when the thread starts.
    @Override
    public void run() {
        List<String> lines;
        try {
            // Reading all lines (data) from the specified file.
            lines = Files.readAllLines(Paths.get(filename));

            // Looping through each line to process its data.
            for (String line : lines) {
                int inputData = Integer.parseInt(line.trim());  // Converting the line to  integer.
                processInput(inputData);
            }
        } catch (IOException e) {
            // If there's any error reading the file, it will be print the exact error to the console.
            e.printStackTrace();
        }
    }
    // Private helper method to process the input data and log the result.
    private void processInput(int input) {
        // Calculating the square of the input.
        int output = input * input;
        // Generating a timestamp for logging purposes.
        String timestamp = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").format(new Date());
        // Formatting the log message.
        String message = String.format("Time: %s, Task: %s, Thread No: %d, Input: %d, Result: %d",
                timestamp, filename, Thread.currentThread().getId(), input, output);
        // Requesting the cluster monitor to write this message to the log.
        monitor.writeToLog(message);
    }
}

public class ClusterMonitor {
    private final Object logLock = new Object();
    // Method to write a message to the log (console).
    public void writeToLog(String message) {
        // Synchronizing on the logLock object ensures that only one thread can write to the console at a time.
        synchronized (logLock) {
            // Printing the message to the console.
            System.out.println(message);
        }
    }
}

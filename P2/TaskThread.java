import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;
public class TaskThread extends Thread
{
    private int input;
    private int threadNo;
    private String taskFileName;
    private static Semaphore logSemaphore = new Semaphore(1);
    public TaskThread(int input, int threadNo, String taskFileName)
    {
        this.input = input;
        this.threadNo = threadNo;
        this.taskFileName = taskFileName;
    }
    //This method will be executed when the thread is started
    @Override
    public void run()
    {
        // Calculating the square of the input
        int result = input * input;
        try
        {
            // Making the thread sleep for a duration relative to its input
            Thread.sleep(input * 100);
            log(taskFileName, threadNo, input, result);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    private void log(String taskFileName, int threadNo, int input, int result)
    {
        try {
            logSemaphore.acquire();
            // Formatting the current date and time
            SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
            // Logging the task details
            System.out.println("Time: " + sdf.format(new Date()) + ", Task: " + taskFileName
                    + ", Thread No: " + threadNo + ", Input: " + input + ", Result: " + result);
            // Releaseingg the semaphore for other threads
            logSemaphore.release();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

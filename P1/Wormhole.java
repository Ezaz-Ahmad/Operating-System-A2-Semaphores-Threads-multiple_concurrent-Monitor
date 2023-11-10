import java.util.concurrent.Semaphore;

public class Wormhole {
    static Semaphore wormhole = new Semaphore(1);
    static Semaphore earthTurn = new Semaphore(1);
    static Semaphore proximaTurn = new Semaphore(0);
}

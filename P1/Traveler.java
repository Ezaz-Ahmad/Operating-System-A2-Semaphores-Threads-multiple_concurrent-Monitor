public class Traveler implements Runnable {
    private String id;
    private String origin;
    private int times;
    private static int COUNT = 0;

    public Traveler(String id, String origin, int times) {
        this.id = id;
        this.origin = origin;
        this.times = times;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < times; i++) {
                System.out.println(id + ": Waiting for wormhole. Travelling towards " + (origin.equals("E") ? "Proxima-b" : "Earth"));

                if (origin.equals("E")) {
                    Wormhole.earthTurn.acquire();
                } else {
                    Wormhole.proximaTurn.acquire();
                }

                Wormhole.wormhole.acquire();

                for (int j = 25; j <= 75; j += 25) {
                    Thread.sleep(50);
                    System.out.println(id + ": Crossing wormhole Loading " + j + "%.");
                }

                Wormhole.wormhole.release();

                synchronized (Traveler.class) {
                    COUNT++;
                    System.out.println(id + ": Across the wormhole.");
                    System.out.println("COUNT = " + COUNT);
                }

                if (origin.equals("E")) {
                    Wormhole.proximaTurn.release();
                } else {
                    Wormhole.earthTurn.release();
                }
                Thread.sleep(50);
            }

            System.out.println(id + " Finished.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package concurrencyandthreads;

public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {

        // Case 1: Sleeping thread
        Thread sleepingThread = new Thread(() -> {
            try {
                System.out.println("Sleeping thread: going to sleep for 10s...");
                Thread.sleep(10000);  // will get interrupted
                System.out.println("Sleeping thread: woke up normally (won't run if interrupted).");
            } catch (InterruptedException e) {
                System.out.println("Sleeping thread: Interrupted while sleeping!");
            }
            System.out.println("Sleeping thread: Exiting...");
        }, "SleepingThread");


        // Case 2: Busy thread (no blocking calls)
        Thread busyThread = new Thread(() -> {
            int counter = 0;
            while (true) {
                System.out.println("counter value is :" + counter++);
                // If we don't check isInterrupted(), this loop will run forever
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Busy thread: Detected interrupt flag, stopping work...");
                    break;
                }
            }
            System.out.println("Busy thread: Exiting...");
        }, "BusyThread");


        // Start both threads
        sleepingThread.start();
        busyThread.start();

        // Give them a moment to start
        Thread.sleep(2000);

        System.out.println("\nMain: Interrupting both threads now...");
        sleepingThread.interrupt();
        busyThread.interrupt();

        // Wait for both to finish
        sleepingThread.join();
        busyThread.join();

        System.out.println("\nMain: Both threads have finished.");
    }
}


package concurrencyandthreads;


public class WaitNotifyDemo {

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        // Create 3 worker threads that will wait
        Runnable waitingTask = () -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " is running....");
                System.out.println(Thread.currentThread().getName() + " is waiting...");
                try {
                    lock.wait(); // releases the lock and goes to WAITING state
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " got notified and is resuming work!");
            }
        };

        Thread t1 = new Thread(waitingTask, "Worker-1");
        Thread t2 = new Thread(waitingTask, "Worker-2");
        Thread t3 = new Thread(waitingTask, "Worker-3");

        t1.start();
        t2.start();
        t3.start();

        // Give them a moment to start and enter WAITING state
        Thread.sleep(1000);

        // Notifier thread
        Thread notifier = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Notifier is sending notification...");
                // Try switching between notify() and notifyAll()
                // lock.notify();     // wakes up only one thread
                lock.notifyAll();     // wakes up all waiting threads
            }
        }, "Notifier");

        notifier.start();

        // Wait for all threads to finish
        t1.join();
        t2.join();
        t3.join();
        notifier.join();

        System.out.println("Main thread finished.");
    }
}


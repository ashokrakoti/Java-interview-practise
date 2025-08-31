package concurrencyandthreads;

public class ThreadLifeCycleDemo {

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        // 1️⃣ Thread that sleeps (TIMED_WAITING)
        Thread sleeper = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " going to sleep...");
                Thread.sleep(2000); // TIMED_WAITING
                System.out.println(Thread.currentThread().getName() + " woke up and finishing.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "SleeperThread");

        // 2️⃣ Thread that waits (WAITING)
        Thread waiter = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread().getName() + " waiting for notification...");
                    lock.wait(); // WAITING
                    System.out.println(Thread.currentThread().getName() + " got notified and finishing.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "WaiterThread");

        // 3️⃣ Thread that gets blocked (BLOCKED)
        Thread blocker = new Thread(() -> {
            synchronized (lock) { // will block if waiter holds lock
                System.out.println(Thread.currentThread().getName() + " acquired the lock and finishing.");
            }
        }, "BlockerThread");

        // 🔹 Show NEW state
        System.out.println("Before start: " + sleeper.getState()); // NEW

        // Start all threads
        sleeper.start();
        waiter.start();
        Thread.sleep(100); // small delay so waiter grabs the lock
        blocker.start();

        // Give them time to enter states
        Thread.sleep(500);

        // 🔹 Print their states
        System.out.println("Sleeper state: " + sleeper.getState());   // TIMED_WAITING
        System.out.println("Waiter state: " + waiter.getState());     // WAITING
        System.out.println("Blocker state: " + blocker.getState());   // BLOCKED

        // 🔹 Notify waiter so it can finish
        synchronized (lock) {
            lock.notify();
        }

        // Wait for threads to finish
        sleeper.join();
        waiter.join();
        blocker.join();

        // 🔹 Final states
        System.out.println("Sleeper final state: " + sleeper.getState());   // TERMINATED
        System.out.println("Waiter final state: " + waiter.getState());     // TERMINATED
        System.out.println("Blocker final state: " + blocker.getState());   // TERMINATED
    }
}


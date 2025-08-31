package concurrencyandthreads;

public class WaitNotifyExample {
    private static final Object lock = new Object();
    private static boolean dataReady = false;

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Producer: preparing data...");
                try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
                dataReady = true;
                System.out.println("Producer: data ready, notifying...");
                lock.notify(); // wake up one consumer
            }
        });

        Thread consumer = new Thread(() -> {
            synchronized (lock) {
                while (!dataReady) {  // condition check
                    try {
                        System.out.println("Consumer: waiting for data...");
                        lock.wait(); // releases lock & waits
                    } catch (InterruptedException ignored) {}
                }
                System.out.println("Consumer: got the data!");
            }
        });

        consumer.start();
        producer.start();
    }
}


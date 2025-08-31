package concurrencyandthreads;

public class NoJoinDemo {
    public static void main(String[] args) {
        Thread worker1 = new Thread(() -> {
            System.out.println("Worker 1 started.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("worker 1 interrupted");
            }
            System.out.println("Worker 1 finished.");
        });

        Thread worker2 = new Thread(() -> {
            System.out.println("Worker 2 started.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("worker 2 interrupted");
            }
            System.out.println("Worker 2 finished.");
        });

        worker1.start();
        worker2.start();

        // No join here!
        System.out.println("Main thread ending (workers may still be running).");
    }
}


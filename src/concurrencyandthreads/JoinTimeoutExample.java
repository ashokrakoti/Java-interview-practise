package concurrencyandthreads;

public class JoinTimeoutExample {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            System.out.println("Worker started, doing long task...");
            try {
                Thread.sleep(10000); // simulates 10 sec work
            } catch (InterruptedException e) {
                System.out.println("Worker interrupted!");
            }
            System.out.println("Worker finished task!");
        });

        worker.start();

        System.out.println("Main waiting for worker (max 3 seconds)...");
        worker.join(3000);  // wait max 3 sec

        if (worker.isAlive()) {
            System.out.println("Worker still running... main will continue without waiting further.");
        } else {
            System.out.println("Worker has finished within 3 seconds.");
        }

        System.out.println("Main thread finished its job.");
    }
}


package concurrencyandthreads;

public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
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

        // Main thread waits for worker1 and worker2
        worker1.join();//declares that worker 1 is completed its work.
        worker2.join();//declares that worker 2 is completed its work.

        //this code does not execute until worker1 and worker2 are completed. that is the whole execution of the thread implementation is completed.
        System.out.println("All workers finished. Main thread ending.");
    }
}

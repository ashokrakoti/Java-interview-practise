package concurrencyandthreads;

public class JoinUnderstanding {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            System.out.println("Worker started.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("worker interrupted");
            }
            System.out.println("Worker finished.");
        });

        worker.start();

        System.out.println("Main is waiting for worker...");
        worker.join();  // main is blocked here
        System.out.println("Main resumes after worker is done.");
    }
}


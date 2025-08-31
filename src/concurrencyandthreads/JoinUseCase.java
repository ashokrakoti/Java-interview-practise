package concurrencyandthreads;

public class JoinUseCase {
    public static void main(String[] args) throws InterruptedException {
        final StringBuilder api1Result = new StringBuilder();
        final StringBuilder api2Result = new StringBuilder();

        Thread api1 = new Thread(() -> {
            System.out.println("Fetching data from API 1...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("API 1 fetch interrupted");
            }
            api1Result.append("API 1 Data");
            System.out.println("API 1 fetch complete.");
        });

        Thread api2 = new Thread(() -> {
            System.out.println("Fetching data from API 2...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("API 2 fetch interrupted");
            }
            api2Result.append("API 2 Data");
            System.out.println("API 2 fetch complete.");
        });

        // Start both downloads
        api1.start();
        api2.start();

        // Wait for both to finish
        api1.join();
        api2.join();

        // Process after both are done
        System.out.println("Merging results...");
        String finalData = api1Result + " + " + api2Result;
        System.out.println("Final merged data = " + finalData);
    }
}


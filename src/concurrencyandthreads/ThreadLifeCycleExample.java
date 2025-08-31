package concurrencyandthreads;

import java.util.concurrent.CountDownLatch;

public class ThreadLifeCycleExample {

    public static final Object lock = new Object();
    public static final CountDownLatch latch = new CountDownLatch(2);

    //main method
    public static void main(String[] args) throws InterruptedException {


        System.out.println("main thread started.......");
        Runnable waitingTask = () ->
        {
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread().getName() + " working....");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " going to wait state");
                    latch.countDown();
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + " notification received and woke up and finishing task");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted" + e.getMessage());
                }
            }
        };

        Thread t = new Thread(waitingTask, "MyThread-1");
        Thread t2 = new Thread(waitingTask, "MyThread-2");

        //state of the thread before calling start
        System.out.println(t.getName() + " state before calling start(): " + t.getState());
        System.out.println(t2.getName() + " state before calling start(): " + t2.getState());
        Thread.sleep(2000);
        //start the thread
        t.start();
        t2.start();

        //state of the thread after calling start
        System.out.println(t.getName() + " state after calling start(): " + t.getState());
        System.out.println(t2.getName() + " state after calling start(): " + t2.getState());
        Thread.sleep(2000);

        Thread notifier = new Thread(() ->
        {
            try {
                latch.await();
                System.out.println(Thread.currentThread().getName() + " working.....");
                Thread.sleep(10000);
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " acquired lock for monitor...");
                    System.out.println("Notifier thread is completed and sending notification");
                    //lock.notify();
                    lock.notifyAll();
                }

            } catch (InterruptedException ex) {
                System.out.println("Notifier thread interrupted");
            }

        }, "Notifier");
        notifier.start();

        System.out.println("main thread waiting for child threads to finish......");
        t.join();
        t2.join();
        //main thread hangs here for t thread to finish.
        System.out.println(t.getName() + " state before main thread ending: " + t.getState());
        System.out.println(t2.getName() + " state before main thread ending: " + t2.getState());
        System.out.println(notifier.getName() + " state before main thread ending: " + notifier.getState());
        Thread.sleep(2000);
        System.out.println("main thread finished....");

    }
}

package concurrencyandthreads;

import java.util.concurrent.CountDownLatch;


/*
🛠 Real-World Example: Food Delivery System

Imagine you’re building a small simulation for food delivery:

Chefs (threads) cook dishes.

Delivery guys (threads) wait until food is ready before picking it up.

Main thread coordinates everything.

We’ll use:

synchronized(lock) + wait() → Delivery guys wait until food is cooked.

notifyAll() → Chef tells all delivery guys "Food is ready!"

CountDownLatch → Wait until all chefs finish cooking before notifying.

Thread.sleep() → Simulate cooking time / delivery delay.
 */
public class FoodDeliverySimulation {

    private static final Object lock = new Object();
    private static final CountDownLatch latch = new CountDownLatch(2); // 2 chefs

    public static void main(String[] args) throws InterruptedException {

        // Delivery person task
        Runnable deliveryTask = () -> {
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread().getName() + " is waiting for food...");
                    lock.wait(); // Wait until chef notifies
                    System.out.println(Thread.currentThread().getName() + " got the food and started delivery!");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " delivered the food.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // Chef task
        Runnable chefTask = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " started cooking...");
                Thread.sleep(3000); // Cooking time
                System.out.println(Thread.currentThread().getName() + " finished cooking.");
                latch.countDown(); // signal cooking complete
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // Start chefs
        Thread chef1 = new Thread(chefTask, "Chef-1");
        Thread chef2 = new Thread(chefTask, "Chef-2");

        chef1.start();
        chef2.start();

        // Start delivery guys
        Thread delivery1 = new Thread(deliveryTask, "DeliveryGuy-1");
        Thread delivery2 = new Thread(deliveryTask, "DeliveryGuy-2");
        delivery1.start();
        delivery2.start();

        // Notifier: restaurant manager
        Thread manager = new Thread(() -> {
            try {
                System.out.println("Manager waiting for all chefs to finish cooking...");
                latch.await(); // Wait until all chefs done
                synchronized (lock) {
                    System.out.println("Manager says: All food ready! Notifying delivery guys...");
                    lock.notifyAll(); // Wake up all delivery guys
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Manager");

        manager.start();

        // Wait for all threads
        chef1.join();
        chef2.join();
        delivery1.join();
        delivery2.join();
        manager.join();

        System.out.println("Restaurant closed for the day!");
    }
}




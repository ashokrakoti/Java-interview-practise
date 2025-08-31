package concurrencyandthreads;


class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread is running using Thread class");
    }
}

public class ThreadCreationBasics {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start(); // starts a new thread
    }
}

/*
Each Thread object represents a thread of execution.

You override run() with the logic you want executed in that thread.

You cannot extend any other class because Java does not support multiple inheritance.class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread is running using Runnable interface");
    }
}

public class RunnableExample {
    public static void main(String[] args) {
        MyRunnable task = new MyRunnable();
        Thread t1 = new Thread(task);
        t1.start(); // starts a new thread
    }
}
 */


//2.Implement the runnable interface comment the above code nd run this one. else the public keyword doesnt allow to run
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread is running using Runnable interface");
    }
}

 //public
 class RunnableExample {
    public static void main(String[] args) {
        MyRunnable task = new MyRunnable();
        Thread t1 = new Thread(task);
        t1.start(); // starts a new thread
    }
}
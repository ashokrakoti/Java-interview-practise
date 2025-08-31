

## 🔔 What is `InterruptedException`?

It’s a **checked exception** that is thrown when a thread is **sleeping, waiting, or otherwise blocked**, and *another thread* calls `interrupt()` on it.

Think of it like someone tapping on your shoulder while you’re asleep saying *“wake up, stop waiting!”*.

---

## 📌 Common cases where `InterruptedException` occurs

### 1. **Thread.sleep()**

```java
try {
    Thread.sleep(5000); // sleeping for 5 sec
} catch (InterruptedException e) {
    System.out.println("Sleep was interrupted!");
}
```

* If another thread calls `t.interrupt()` while this thread is sleeping, it immediately wakes up with `InterruptedException`.

---

### 2. **Object.wait()**

```java
synchronized (lock) {
    try {
        lock.wait(); // waiting for notify
    } catch (InterruptedException e) {
        System.out.println("Wait was interrupted!");
    }
}
```

* If another thread calls `interrupt()` while this thread is in `wait()`, the waiting is broken with `InterruptedException`.

---

### 3. **Thread.join()**

```java
try {
    workerThread.join(); // waiting for worker to finish
} catch (InterruptedException e) {
    System.out.println("Join was interrupted!");
}
```

* If a thread is waiting for another to finish, and someone interrupts it, it stops waiting.

---

### 4. **Blocking methods from `java.util.concurrent`**

* `BlockingQueue.take()`
* `BlockingQueue.put()`
* `Semaphore.acquire()`
* `CountDownLatch.await()`
* `CyclicBarrier.await()`

Example:

```java
BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
try {
    queue.take(); // waits if empty
} catch (InterruptedException e) {
    System.out.println("take() was interrupted!");
}
```

---

## 🔄 Typical Flow

1. Thread enters a blocking state (sleep, wait, join, await, etc.)
2. Another thread calls `t.interrupt()` on it.
3. JVM sets the **interrupted flag = true**, and if the thread is in a blocking call, it immediately:

    * Clears the flag
    * Throws `InterruptedException`

---

## ✅ Why is this useful?

* It’s a **graceful way to stop threads**. Instead of killing them, you “politely” interrupt.
* Example: shutting down worker threads in a server.

```java
class Worker extends Thread {
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // do work
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Worker stopped due to interrupt");
        }
    }
}
```

---

### 🔑 Summary

* `InterruptedException` occurs when a blocked thread is interrupted.
* Happens in **sleep, wait, join, and most blocking concurrency utilities**.
* It’s the standard way to **cooperatively stop threads**.

---

👉 Do you want me to also show you **what happens if you call `interrupt()` on a thread that is NOT blocked** (like just running normally)? That’s a subtle case many miss in interviews.
 refer code class InterruptDemo for code.

## ###################################### More on Interrupt() #######################################

Exactly ✅ you nailed it!

When you call `thread.interrupt()` on a thread that’s **busy doing some work** (not in `sleep`, `wait`, or `join`):

* The JVM does **not forcibly stop it**.
* Instead, the **interrupted flag** is set to `true`.
* The thread continues running until its code **checks that flag**.

Example:

```java
public class InterruptFlagDemo {
    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            while (true) {
                // Simulating busy work
                double value = Math.random() * Math.random();

                // Periodically check interruption
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Worker noticed interrupt, stopping...");
                    break; // gracefully exit
                }
            }
        });

        worker.start();

        try {
            Thread.sleep(2000); // Let worker run
        } catch (InterruptedException e) {}

        System.out.println("Interrupting worker...");
        worker.interrupt(); // Just sets the flag
    }
}
```

### 🔑 Key points

1. **If the thread never checks `isInterrupted()`**, it will just keep running forever despite the interrupt.
2. **If the thread is blocked** (e.g., `sleep`, `wait`, `join`), then the interrupt will throw `InterruptedException` immediately.
3. It’s up to **your code** to decide whether to:

    * Exit gracefully (`break` out of loop).
    * Clean up resources.
    * Or ignore and continue.

👉 So, yes — `interrupt()` is basically a **polite request** to the thread: *"Hey, can you stop when convenient?"*.

Would you like me to also show you what happens **when a thread is in sleep vs. when it’s doing busy work**, side by side? That contrast makes it super clear.

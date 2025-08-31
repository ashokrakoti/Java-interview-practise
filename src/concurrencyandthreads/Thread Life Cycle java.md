

## 🔄 Thread Life Cycle in Java

A **Thread** in Java goes through different **states**. Java defines them in the `Thread.State` enum.

---

## 1. **NEW**

* Thread is created but **not yet started**.
* State: `NEW`
* Happens after you call `new Thread(...)`, but **before** calling `start()`.

```java
    Thread t = new Thread(() -> System.out.println("Hello"));
    System.out.println(t.getState()); // NEW
```

---

## 2. **RUNNABLE**

* Thread is **ready to run**, but waiting for CPU scheduling.
* When you call `start()`, it goes to `RUNNABLE`.
* It doesn’t mean it’s *running yet* — just waiting in the queue.

```java
    t.start();
    System.out.println(t.getState()); // RUNNABLE (may already be RUNNING)
```

---

## 3. **RUNNING**

* Thread scheduler picks the thread from `RUNNABLE` and gives it CPU time.
* Only one thread per CPU core can be **RUNNING** at a time.

👉 No direct method to force a thread into `RUNNING`. The **JVM + OS scheduler** decides.

---

## 4. **WAITING**

* Thread is **waiting indefinitely** for another thread to signal it.
* It enters this state when calling methods like:

  * `Object.wait()` (without timeout)
  * `join()` (waiting for another thread to finish)
  * `LockSupport.park()`

👉 Needs another thread to call `notify()` or `notifyAll()` to continue.

---

## 5. **TIMED\_WAITING**

* Thread is **waiting for a specified time**.
* Enters this state when calling:

  * `sleep(milliseconds)`
  * `wait(timeout)`
  * `join(timeout)`
  * `parkNanos()` / `parkUntil()`

👉 After timeout, it automatically goes back to `RUNNABLE`.

---

## 6. **BLOCKED**

* Thread is waiting to acquire a **monitor lock** (synchronized block).
* Example:

```java
      synchronized(obj) {   // Thread-1 holds the lock
          // critical section
      }
      
      synchronized(obj) {   // Thread-2 is BLOCKED waiting for the same lock
          // will enter only after Thread-1 releases the lock
      }
```

---

## 7. **TERMINATED (DEAD)**

* Thread has **finished execution** (the `run()` method ends).
* Cannot be restarted again → if you call `start()` on a terminated thread, you get `IllegalThreadStateException`.

---

# 🛠️ Commonly Asked Life Cycle Methods

| Method                 | State Impact                     | Purpose                                                                 |
| ---------------------- | -------------------------------- | ----------------------------------------------------------------------- |
| `start()`              | NEW → RUNNABLE                   | Begins execution of a new thread                                        |
| `run()`                | RUNNING                          | Code to execute (don’t call directly, or it won’t create a new thread!) |
| `sleep(ms)`            | RUNNING → TIMED\_WAITING         | Pause execution for fixed time                                          |
| `join()`               | RUNNING → WAITING/TIMED\_WAITING | Wait for another thread to finish                                       |
| `wait()`               | RUNNING → WAITING/TIMED\_WAITING | Release lock & wait for notify                                          |
| `notify()/notifyAll()` | WAITING → RUNNABLE               | Wakes up threads waiting on the lock                                    |
| `yield()`              | RUNNING → RUNNABLE               | Hint to scheduler to switch threads (not guaranteed)                    |
| `interrupt()`          | Doesn’t change state directly    | Signals a thread to stop waiting/sleeping                               |

---

# 🎯 What Interviewers Expect

1. **Explain the states with transitions** (with examples like `sleep`, `wait`, `join`, `synchronized`).
2. **Difference between WAITING vs TIMED\_WAITING vs BLOCKED** (very common).
3. **Why calling `run()` directly does not start a new thread** (classic tricky question).
4. **What happens if you call `start()` twice** (exception).

---

👉 For **daily work as a Java dev**, you don’t usually manipulate `wait()`/`notify()` directly — instead, you use higher-level concurrency utilities (`ExecutorService`, `CountDownLatch`, `Semaphore`, etc.), but interviewers still want you to know the basics.

---

Would you like me to make a **visual diagram of thread state transitions** so you can memorize it quickly for interviews?

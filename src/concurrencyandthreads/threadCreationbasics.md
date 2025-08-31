1. Two Main Ways to Create a Thread in Java

In Java, you can create a new thread in two primary ways:

## 1.Extending the Thread class

## 2.Implementing the Runnable interface 

refer the class code ThreadCreationBasics.java for code.

4. Difference Between Thread vs Runnable
   Aspect	                                                Thread Class	                    Runnable Interface
   Inheritance	                Extends Thread (so you cannot extend any other class)	        Implements Runnable (still free to extend another class)
   Code Reuse	                Less flexible (each thread has its own code)	                More flexible (same Runnable can be shared across multiple threads)
   Separation of Concerns	    Thread logic & task are tied together	T                       ask (Runnable) is separate from the execution (Thread)
   Preferred in Practice?	    Rarely used (only for simple cases or quick tests)	            Commonly used in real-world projects (scales better)

## reusability of Runnable
class SharedTask implements Runnable {
    @Override
        public void run() {
        System.out.println(Thread.currentThread().getName() + " executing task");
    }
}

public class Test {
public static void main(String[] args) {
SharedTask task = new SharedTask();

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        t1.start();
        t2.start();
    }
}

## Analogy for the differences
Perfect 👍 An analogy makes this crystal clear. Let’s relate **Thread vs Runnable** to **a worker and a job/task**.

---

## 🏭 Analogy: Factory Worker & Task

### 1. **Thread (Extending Thread Class)**

Imagine you hire a **worker (Thread)** and tell them:

* “You are permanently tied to this **one specific job**. Your identity and your work are fused.”
* So the **worker IS the job**.

Example:

* You hire a worker only to paint walls.
* That worker cannot be reassigned to another job (like fixing lights).
* If you need a worker for fixing lights, you must hire a **new worker class** just for that.

👉 That’s like extending `Thread` → the work (job logic) and the worker (thread) are tied together.

---

### 2. **Runnable (Implementing Runnable Interface)**

Now, think differently:

* You define a **task/job** (like “paint the wall”).
* Then you can assign this task to **any worker (Thread)**.
* The worker executes the job but can also execute other jobs if needed.

Example:

* You have a **painting job** (Runnable).
* You give this painting job to Worker A (Thread).
* You can also give the same painting job to Worker B (another Thread).
* Tomorrow, you can create a **different job** (like “fix lights”) and still reuse the same pool of workers.

👉 That’s like implementing `Runnable` → the work (job logic) is **separate** from the worker (thread), so you can reuse, reassign, and manage better.

---

## 🎯 Core Difference in Analogy

* **Thread = Worker IS the job.**
* **Runnable = Worker DOES the job.**

---

👉 Do you want me to also extend this analogy to how **ExecutorService / ThreadPools** work (like a **factory manager assigning tasks to available workers**)? That’s the next natural step to understand how modern Java handles concurrency.


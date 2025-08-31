
## What is join()?

join() is a method of the Thread class.

It allows one thread to wait for the completion of another thread before continuing execution.

👉 In other words:
“If thread A calls t.join() on thread B → A will pause until B finishes execution.”

## Note:
join() guarantees that by the time it returns, the worker thread has finished execution.

It’s not that calling join() “says” the worker is done — it’s that the caller blocks/waits until the worker is actually done.

how join works internally.- refer the class JoinUnderstanding
main thread calls join() on a worker →
main thread is blocked (paused).

While blocked → main cannot execute any further code after the join() line.

When the worker thread finishes →
the join() call unblocks and returns.

Only then → main continues execution and runs the code that comes after join().

## use case for join with timeout
The join() method is only useful when you know (or expect) that the worker thread will eventually finish. Otherwise:

If the worker thread has a long-running or infinite task (e.g., an endless loop, a server listener), then calling join() on it will make the main thread wait forever.

This is why in production systems we sometimes use timeouts with join:

worker.join(5000);  // main waits at most 5 seconds


➡️ After 5 seconds, join() will unblock even if the worker hasn’t finished.



## working of join()

Here’s the precise behavior of join():

When Thread A calls threadB.join(), then Thread A goes into a WAITING (or TIMED_WAITING if you passed a timeout) state.

The JVM internally keeps track that Thread A is waiting for Thread B.

As soon as Thread B moves to the TERMINATED state (finishes execution), the JVM automatically wakes up Thread A.

No notify()/notifyAll() is needed — the JVM does this automatically.

Example
Thread t1 = new Thread(() -> {
try { Thread.sleep(2000); } catch (InterruptedException e) {}
System.out.println("t1 finished");
});

Thread t2 = new Thread(() -> {
try {
t1.join();  // t2 will wait until t1 is TERMINATED
System.out.println("t2 resumes after t1 finishes");
} catch (InterruptedException e) {}
});

t1.start();
t2.start();


Flow:

t2 calls t1.join() → enters WAITING.

t1 sleeps for 2s, then completes → moves to TERMINATED.

JVM detects t1 terminated → wakes up t2 automatically.

t2 continues execution.

👉 So yes, in join(), the waiting thread is resumed automatically by the JVM once the target thread hits TERMINATED.
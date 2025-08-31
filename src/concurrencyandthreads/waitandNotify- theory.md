## Refresher

wait(), notify(), notifyAll() are defined in Object class, not in Thread.

Reason: Because every object in Java can be used as a monitor/lock.

## 1. wait()

Used by a thread to say:
“I’ll pause here and release the lock until someone wakes me up.”

Must be called inside a synchronized block or method (else IllegalMonitorStateException).

Releases the lock on that object, so other threads can enter synchronized blocks.

## 2. notify()

Wakes up one waiting thread (chosen by JVM) that previously called wait() on the same object’s monitor.

The awakened thread will not run immediately; it must re-acquire the lock first.

## 3. notifyAll()

Wakes up all waiting threads on that object’s monitor.

Again, they must compete to re-acquire the lock one by one.

⚖️ Difference between notify() and notifyAll()

notify() → only one thread is awakened (unpredictable which one).

notifyAll() → all waiting threads are awakened, but they must acquire lock one by one.

👉 In practice:

Use notify() if only one thread is expected to wait.

Use notifyAll() if multiple threads may wait (to avoid deadlocks/starvation).


## Key Interview Points

wait() releases the lock, sleep() does not.

Always call wait() inside a loop checking the condition (while not if) → avoids spurious wakeups.

notify() wakes one thread, notifyAll() wakes all.

Without synchronization → IllegalMonitorStateException.

👉 So in short:
wait() = pause + release lock,
notify() = wake one,
notifyAll() = wake all.
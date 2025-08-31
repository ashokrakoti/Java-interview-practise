package concurrencyandthreads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * ✅ How to Use
 *
 * Change this line to test each mode:
 *
 * private static final LockType LOCK_MODE = LockType.SYNCHRONIZED;
 * // OR
 * private static final LockType LOCK_MODE = LockType.REENTRANT_LOCK;
 * // OR
 * private static final LockType LOCK_MODE = LockType.READ_WRITE_LOCK;
 *
 *
 * Run the program each time.
 *
 * Watch the logs:
 *
 * SYNCHRONIZED / REENTRANT_LOCK → all serialized (one at a time).
 *
 * READ_WRITE_LOCK → readers can run in parallel, but writers block everyone.
 *
 * ⚡ This is now your playground demo for interviews — you can say:
 *
 * “I built a small program that switches between synchronized, ReentrantLock, and ReentrantReadWriteLock, so I’ve seen the different thread behaviors in action.”
 *
 * Would you like me to also add timestamps in logs (so you can prove that multiple readers are running simultaneously when using ReadWriteLock)?
 */

public class LockComparisonDemo {

    // Enum to choose lock strategy
    enum LockType {
        SYNCHRONIZED,
        REENTRANT_LOCK,
        READ_WRITE_LOCK
    }

    // Pick the lock type here
    private static final LockType LOCK_MODE = LockType.REENTRANT_LOCK;

    // Time formatter
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

    private static String timestamp() {
        return "[" + sdf.format(new Date()) + "]";
    }

    // Shared Resource
    static class SharedResource {
        private int data = 0;

        // Locks
        private final ReentrantLock reLock = new ReentrantLock();
        private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

        // --- Writer ---
        public void write(String threadName) {
            switch (LOCK_MODE) {
                case SYNCHRONIZED:
                    synchronized (this) {
                        doWrite(threadName);
                    }
                    break;

                case REENTRANT_LOCK:
                    reLock.lock();
                    try {
                        doWrite(threadName);
                    } finally {
                        reLock.unlock();
                    }
                    break;

                case READ_WRITE_LOCK:
                    rwLock.writeLock().lock();
                    try {
                        doWrite(threadName);
                    } finally {
                        rwLock.writeLock().unlock();
                    }
                    break;
            }
        }

        // --- Reader ---
        public void read(String threadName) {
            switch (LOCK_MODE) {
                case SYNCHRONIZED:
                    synchronized (this) {
                        doRead(threadName);
                    }
                    break;

                case REENTRANT_LOCK:
                    reLock.lock();
                    try {
                        doRead(threadName);
                    } finally {
                        reLock.unlock();
                    }
                    break;

                case READ_WRITE_LOCK:
                    rwLock.readLock().lock();
                    try {
                        doRead(threadName);
                    } finally {
                        rwLock.readLock().unlock();
                    }
                    break;
            }
        }

        // --- Actual Work ---
        private void doWrite(String threadName) {
            try {
                System.out.println(timestamp() + " " + threadName + " started WRITING...");
                Thread.sleep(1000);
                data++;
                System.out.println(timestamp() + " " + threadName + " finished WRITING. Data = " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doRead(String threadName) {
            try {
                System.out.println(timestamp() + " " + threadName + " started READING... Data = " + data);
                Thread.sleep(1000);
                System.out.println(timestamp() + " " + threadName + " finished READING.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Main Runner
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Runnable writer = () -> resource.write(Thread.currentThread().getName());
        Runnable reader = () -> resource.read(Thread.currentThread().getName());

        // Create threads
        Thread w1 = new Thread(writer, "Writer-1");
        Thread w2 = new Thread(writer, "Writer-2");
        Thread r1 = new Thread(reader, "Reader-1");
        Thread r2 = new Thread(reader, "Reader-2");
        Thread r3 = new Thread(reader, "Reader-3");

        // Start threads
        w1.start();
        r1.start();
        r2.start();
        r3.start();
        w2.start();
    }
}


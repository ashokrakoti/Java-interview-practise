package concurrencyandthreads;

/**
 * It is a example of a memory leak in a thread.
 * we are creating a new object inside a loop and storing it in a static list.
 * as the condition is not stopping the thread. it keeps on adding new objects to the list.
 * after some point of time after the execution started the memory usage will keep on increasing.
 * finally we get the out of memory error.
 *
 * this is a memory leak. + cpu resource is not getting released.
 */
public class MemoryLeakThread {
    public static void main(String[] args) {
        Thread leakingThread = new Thread(() -> {
            while (true) {
                // Allocate new objects inside loop
                String data = new String("Some Data " + System.nanoTime());
                DummyStore.store.add(data); // keep reference forever
            }
        });
        leakingThread.start();
    }

    static class DummyStore {
        static final java.util.List<String> store = new java.util.ArrayList<>();
    }
}


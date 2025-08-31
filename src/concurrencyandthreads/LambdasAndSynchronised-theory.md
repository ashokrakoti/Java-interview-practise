Perfect 👍 let’s take a quick **refresher** on those two things:

1. **Lambda expressions in Java (used with Runnable)**
2. **`synchronized` keyword (used for locking and thread safety)**

---

# 1️⃣ Lambda Expressions Refresher

### 🔹 Why Lambdas?

Before Java 8, if you wanted to pass a piece of logic (like a `Runnable`), you had to use **anonymous inner classes**:

```java
Runnable task = new Runnable() {
    @Override
    public void run() {
        System.out.println("Task is running...");
    }
};
```

This is **verbose**.

From **Java 8 onwards**, you can use **lambda expressions** if the interface is a **functional interface** (an interface with exactly one abstract method).

`Runnable` is a functional interface (method: `void run()`), so you can write:

```java
Runnable task = () -> System.out.println("Task is running...");
```

👉 Here:

* `()` → parameter list (empty, since `run()` takes no args).
* `->` → lambda arrow.
* `System.out.println(...)` → body of the method.

You can also use multiple lines:

```java
Runnable task = () -> {
    System.out.println("Start task...");
    System.out.println("End task...");
};
```

✅ This is exactly what I used in the demo code. Instead of defining a separate class for `Runnable`, a lambda is enough.

---

# 2️⃣ `synchronized` Keyword Refresher

### 🔹 Why do we need it?

In multithreading, when multiple threads **access shared resources** (variables, lists, objects), you risk **race conditions** (unpredictable results).
`synchronized` ensures that only **one thread at a time** can execute a block of code on the same object lock.

---

### 🔹 How to Use

#### (a) **Synchronized Method**

```java
public synchronized void increment() {
    counter++;
}
```

* The lock here is on the **instance of the class** (`this`).
* Only one thread per object instance can call `increment()` at a time.

---

#### (b) **Synchronized Block**

```java
synchronized (lock) {
    // only one thread with this lock object can enter at a time
    counter++;
}
```

* You can choose any object (`lock`) as the **monitor lock**.
* While one thread holds the lock, other threads trying `synchronized(lock)` are **BLOCKED**.

---

### 🔹 Key Points for Interviews

* **Every object in Java has a monitor lock**.
* `synchronized` ensures **mutual exclusion**.
* Threads **compete** for the lock.
* If one thread is inside a `synchronized` block/method, others must wait.
* Works with both **instance methods** (lock = `this`) and **static methods** (lock = class object).

---

✅ Example Combining Both:

```java
public class Counter {
    private int count = 0;

    public void increment() {
        synchronized (this) {  // lock on current object
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
```

Multiple threads calling `increment()` will not corrupt the `count` value because of the lock.

---

So:

* **Lambda** → makes passing a short `Runnable` task concise.
* **synchronized** → makes critical sections thread-safe.

---

👉 Do you want me to now explain the **restaurant wait/notify analogy** using this knowledge (lambda + synchronized), so you see it as a story?

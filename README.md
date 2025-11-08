# Java Multithreading Guide

A comprehensive guide covering fundamental concepts of multithreading in Java with practical examples.

## Table of Contents

1. [Thread Creation](#thread-creation)
2. [Thread States](#thread-states)
3. [Thread Methods](#thread-methods)
4. [Synchronization](#synchronization)
5. [Locks](#locks)
6. [Thread Communication](#thread-communication)
7. [Thread Pools](#thread-pools)

---

## Thread Creation

There are two primary ways to create threads in Java:

### 1. Extending Thread Class

```java
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

// Usage
MyThread thread = new MyThread();
thread.start();
```

### 2. Implementing Runnable Interface

```java
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

// Usage
Thread thread = new Thread(new MyRunnable());
thread.start();
```

### 3. Lambda Expression (Java 8+)

```java
Thread thread = new Thread(() -> System.out.println("Hello from thread"));
thread.start();
```

**Note:** Implementing `Runnable` is preferred as it allows for better design (composition over inheritance) and lets your class extend other classes if needed.

---

## Thread States

A thread can be in one of the following states:

1. **NEW** - Thread is created but not yet started
2. **RUNNABLE** - Thread is executing or ready to execute
3. **BLOCKED** - Thread is blocked waiting for a monitor lock
4. **WAITING** - Thread is waiting indefinitely for another thread
5. **TIMED_WAITING** - Thread is waiting for a specified time
6. **TERMINATED** - Thread has completed execution

```java
MyThread t1 = new MyThread();
System.out.println(t1.getState()); // NEW
t1.start();
System.out.println(t1.getState()); // RUNNABLE
```

---

## Thread Methods

### Key Thread Methods

#### `sleep(long millis)`
Causes the current thread to sleep for the specified milliseconds.

```java
Thread.sleep(1000); // Sleep for 1 second
```

#### `join()`
Waits for the thread to complete before continuing execution.

```java
thread.start();
thread.join(); // Wait for thread to finish
```

#### `setPriority(int priority)`
Sets the priority of the thread (MIN_PRIORITY=1, NORM_PRIORITY=5, MAX_PRIORITY=10).

```java
thread.setPriority(Thread.MAX_PRIORITY);
```

#### `yield()`
Hints the scheduler to give CPU time to other threads.

```java
Thread.yield();
```

#### `interrupt()`
Interrupts a thread that is sleeping or waiting.

```java
thread.interrupt();
```

#### `setDaemon(boolean on)`
Marks a thread as a daemon thread. Daemon threads terminate when all user threads finish.

```java
thread.setDaemon(true);
thread.start();
```

---

## Synchronization

When multiple threads access shared resources, synchronization prevents **race conditions** and protects the **critical section**.

### Problem: Race Condition

```java
class Counter {
    private int count = 0;
    
    public void increment() {
        count++; // Not thread-safe!
    }
}
```

### Solution 1: Synchronized Method

```java
class Counter {
    private int count = 0;
    
    public synchronized void increment() {
        count++;
    }
}
```

### Solution 2: Synchronized Block

```java
class Counter {
    private int count = 0;
    
    public void increment() {
        synchronized(this) {
            count++;
        }
    }
}
```

### Solution 3: Static Synchronization

For static methods, synchronize on the class object:

```java
class Counter {
    private static int count = 0;
    
    public static synchronized void increment() {
        count++;
    }
}
```

**Key Points:**
- Every object has an intrinsic lock (monitor)
- `synchronized` keyword invokes this monitor
- Only one thread can execute a synchronized method/block at a time
- Does not guarantee FCFS (First Come First Serve) order

---

## Locks

Java provides explicit lock mechanisms through the `java.util.concurrent.locks` package for more flexible synchronization.

### ReentrantLock

A reentrant lock allows the same thread to acquire the lock multiple times.

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private int balance;
    private final Lock lock = new ReentrantLock();
    
    public void withdraw(int amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
            }
        } finally {
            lock.unlock();
        }
    }
}
```

### tryLock()

Attempts to acquire the lock without waiting indefinitely:

```java
if (lock.tryLock(3000, TimeUnit.MILLISECONDS)) {
    try {
        // Critical section
    } finally {
        lock.unlock();
    }
} else {
    System.out.println("Could not acquire lock");
}
```

### Fair vs Unfair Locks

```java
Lock fairLock = new ReentrantLock(true); // FCFS order
Lock unfairLock = new ReentrantLock(false); // Default, better performance
```

### ReadWriteLock

Allows multiple readers or one writer at a time:

```java
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadWriteCounter {
    private int count = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    
    public void increment() {
        lock.writeLock().lock();
        try {
            count++;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public int getCount() {
        lock.readLock().lock();
        try {
            return count;
        } finally {
            lock.readLock().unlock();
        }
    }
}
```

**Benefits:**
- Multiple threads can read simultaneously
- Improves performance when reads are more frequent than writes

---

## Thread Communication

Threads can communicate using `wait()`, `notify()`, and `notifyAll()` methods.

### Producer-Consumer Problem

```java
class SharedResource {
    private int data;
    private boolean hasData = false;
    
    public synchronized void produce(int value) {
        while (hasData) {
            try {
                wait(); // Wait until data is consumed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notify(); // Notify consumer
    }
    
    public synchronized void consume() {
        while (!hasData) {
            try {
                wait(); // Wait until data is produced
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasData = false;
        System.out.println("Consumed: " + data);
        notify(); // Notify producer
    }
}
```

**Key Methods:**
- `wait()` - Releases the lock and waits until notified
- `notify()` - Wakes up one waiting thread
- `notifyAll()` - Wakes up all waiting threads

---

## Thread Pools

Thread pools manage a pool of worker threads, reducing the overhead of thread creation and destruction.

### Creating a Thread Pool

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

ExecutorService executor = Executors.newFixedThreadPool(5);

// Submit tasks
executor.submit(() -> {
    System.out.println("Task executed by: " + Thread.currentThread().getName());
});

// Shutdown executor
executor.shutdown();
```

### Types of Thread Pools

1. **Fixed Thread Pool** - Fixed number of threads
   ```java
   Executors.newFixedThreadPool(10);
   ```

2. **Cached Thread Pool** - Creates new threads as needed
   ```java
   Executors.newCachedThreadPool();
   ```

3. **Single Thread Executor** - Single worker thread
   ```java
   Executors.newSingleThreadExecutor();
   ```

4. **Scheduled Thread Pool** - Schedule tasks with delay
   ```java
   Executors.newScheduledThreadPool(5);
   ```

**Benefits:**
- Reduces thread creation overhead
- Controls resource usage
- Better performance for handling multiple tasks

---

## Best Practices

1. **Always use `start()`, never call `run()` directly** - Calling `run()` executes in the same thread
2. **Handle InterruptedException properly** - Don't swallow exceptions
3. **Always unlock in a finally block** - Ensures lock is released even if exceptions occur
4. **Prefer Runnable over Thread** - Better design and flexibility
5. **Use thread pools for multiple tasks** - Avoid creating too many threads
6. **Avoid shared mutable state** - Minimize synchronization needs
7. **Use immutable objects when possible** - Thread-safe by design
8. **Be cautious with thread priorities** - They're platform-dependent hints, not guarantees

---

## Common Pitfalls

- **Deadlock** - Two threads waiting for each other's locks
- **Race Condition** - Multiple threads accessing shared data without synchronization
- **Starvation** - Thread never gets CPU time due to low priority
- **Memory Consistency Errors** - Different threads have inconsistent views of shared data

---

## Summary

Multithreading enables concurrent execution, improving performance and responsiveness. Understanding thread lifecycle, synchronization mechanisms, and communication patterns is essential for writing robust concurrent applications. Always consider thread safety, avoid common pitfalls, and use appropriate synchronization tools based on your specific requirements.

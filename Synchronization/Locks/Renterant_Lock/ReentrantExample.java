package Synchronization.Locks.Renterant_Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
    public static void main(String[] args) {
        ReentrantExample reentrantExample = new ReentrantExample();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                reentrantExample.outerMethod();
            }
        };

        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");

        t1.start();
        t2.start();
    }


    public ReentrantExample() {
        this.lock = new ReentrantLock();
    }
    // ReentrantLock allows same thread to access all the methods of that object
    // There should be always a pair of lock and unlock otherwise it will allow other threads to access it
    // lock.lockInterruptibly(); it helps us to acquire the lock interruptibly

    private final Lock lock;

    public void innerMethod() throws InterruptedException {
        lock.lock();

        try {
            System.out.println("Inner Method");
        } finally {
            lock.unlock();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " just unlocked the innerMethod lock");
            lock.unlock();
            Thread.sleep(3000);
        }
        System.out.println(Thread.currentThread().getName() + " ended with working");
    }

    public void outerMethod() {
        lock.lock();
        try {
            System.out.println("Outer Method");
            innerMethod();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}

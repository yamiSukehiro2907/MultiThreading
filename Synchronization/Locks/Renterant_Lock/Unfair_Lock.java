package Synchronization.Locks.Renterant_Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Unfair_Lock {
    public static void main(String[] args) throws InterruptedException {

        Unfair_Lock unfairLock = new Unfair_Lock();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                unfairLock.accessResource();
            }
        };

        // order in which the resource will be allowed to access by lock is called fairness

        Thread t1 = new Thread(task, "Thread 1");

        Thread t2 = new Thread(task, "Thread 2");

        Thread t3 = new Thread(task, "Thread 3");

        t1.start();
        Thread.sleep(50);
        t2.start();
        Thread.sleep(50);
        t3.start();
    }

    private final Lock unfairLock;

    public Unfair_Lock() {
        this.unfairLock = new ReentrantLock(true);// we can set it to be FCFS
    }

    private void accessResource() {
        unfairLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " accessed the resource");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() + " released the lock");
            unfairLock.unlock();
        }
    }

}

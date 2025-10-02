package Synchronization.Locks.Reentrant_Read_Write_Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCounter {

    private int count = 0;

    private final ReadWriteLock lock; // it allows multiple threads to read the resource as long as no thread is trying to write in the resource

    private final Lock readLock;

    private final Lock writeLock;

    public ReadWriteCounter() {
        this.count = 0;
        this.lock = new ReentrantReadWriteLock();
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
    }

    private void increment() {
        writeLock.lock();
        try {
            count++;
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " incremented ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            writeLock.unlock();
        }
    }

    private void getCount() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " read : " + this.count);
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteCounter readWriteCounter = new ReadWriteCounter();
        Runnable read = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    readWriteCounter.getCount();

                }
            }
        };

        Runnable write = new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    readWriteCounter.increment();
                }
            }
        };

        Thread readTask1 = new Thread(read, "Reader1 Thread");
        Thread readTask2 = new Thread(read, "Reader2 Thread");
        Thread writeTask = new Thread(write, "Writer Thread");
        writeTask.start();
        readTask1.start();
        readTask2.start();

        readTask1.join();
        writeTask.join();
        readTask2.join();

        readWriteCounter.getCount();

    }
}

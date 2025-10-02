package Synchronization.Locks.Thread_Communication;

public class SharedResource {

    private int data;

    private boolean hasData;

    public SharedResource() {
        this.hasData = false;
        this.data = 0;
    }

    public synchronized void produce(int value) {
        while (hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } // do not produce if data is already there
        data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notify(); // it notifies other threads trying to access this sharedResource
        // notifyAll() for all threads
    }

    public synchronized void consume() {
        while (!hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } // do not consume the data if data is not there
        hasData = false;
        notify(); // it notifies other threads trying to access this sharedResource
        System.out.println("Consumed : " + data);
    }
}

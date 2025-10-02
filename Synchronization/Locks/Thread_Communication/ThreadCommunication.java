package Synchronization.Locks.Thread_Communication;

public class ThreadCommunication {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread producer = new Thread(new Producer(sharedResource), "Producer Thread");

        Thread consumer = new Thread(new Consumer(sharedResource), "Consumer Thread");

        producer.start();

        consumer.start();
    }
}

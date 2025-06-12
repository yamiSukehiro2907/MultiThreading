package Synchronization.Problem;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter(); // Shared resource

        MyThread t1 = new MyThread("Thread-1", counter); // Worker - 1
        MyThread t2 = new MyThread("Thread-2", counter); // Worker - 2

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(counter.getCount());
        }
    }
}

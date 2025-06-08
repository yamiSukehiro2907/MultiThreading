package Basic.Creation;

public class Main {
    public static void main(String[] args) {
        // Creation of thread extending Thread class
        Thread2 thread2 = new Thread2();// State -> New
        thread2.start(); // State -> Runnable
        // Creation of thread using the Runnable interface
        Thread3 thread3 = new Thread3();
        Thread thread = new Thread(thread3);
        thread.start();
        for (; ; ) { // an infinite loop to see the randomness of output
            System.out.println(Thread.currentThread().getName());
        }
    }
}

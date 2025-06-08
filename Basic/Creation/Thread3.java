package Basic.Creation;

public class Thread3 implements Runnable {

    // Creation using implementing a Runnable interface
    @Override
    public void run() {
        for (; ; ) {
            System.out.println(Thread.currentThread().getName());
        }
    }
    // mandatory to override the @run function
}

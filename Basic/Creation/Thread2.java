package Basic.Creation;


// creating a child class of main Thread class
public class Thread2 extends Thread {
    @Override
    public void run() {
        for (; ; ) {
            System.out.println(Thread.currentThread().getName());
        }
    }
    // not mandatory to override any functions
}

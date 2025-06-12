package Basic.Methods;

public class MyThread extends Thread {
    @Override
    public void run() { // run method
        System.out.println("Thread is running....");
        for (int i = 1; i <= 100; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();
    }
}

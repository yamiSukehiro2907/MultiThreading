package Basic.Methods;

public class Interrupt extends Thread {
    public static void main(String[] args) {
        Interrupt interrupt = new Interrupt();
        interrupt.start();
        interrupt.interrupt(); // it interrupts the thread either in waiting or executing state
    }


    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Thread is running");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

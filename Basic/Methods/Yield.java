package Basic.Methods;

public class Yield extends Thread {

    public Yield(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            System.out.println(Thread.currentThread().getName() + " is running");
            Thread.yield();
            // it hints the scheduler to give the core to any other thread or process
        }
    }

    public static void main(String[] args) {
        Yield yeild1 = new Yield("y1");
        Yield yeild2 = new Yield("y2");
        yeild1.start();
        yeild2.start();
    }
}

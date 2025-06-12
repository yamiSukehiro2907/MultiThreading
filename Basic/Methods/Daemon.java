package Basic.Methods;

public class Daemon extends Thread {


    public Daemon(String name) {
        super(name);
    }

    public static void main(String[] args) throws InterruptedException {
        Daemon daemon = new Daemon("Daemon");
        daemon.setDaemon(true); // it creates the thread as daemon thread which terminates as main thread completes it's execution
        daemon.start();

        System.out.println("Main done");
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("HI");
        }
    }
}

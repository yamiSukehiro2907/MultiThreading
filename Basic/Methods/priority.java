package Basic.Methods;

public class priority extends Thread {
    public static void main(String[] args) {
        priority pr = new priority();
        pr.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Priority: " + Thread.currentThread().getPriority() + "- count: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

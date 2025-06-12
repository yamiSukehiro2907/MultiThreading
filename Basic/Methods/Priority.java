package Basic.Methods;

public class Priority extends Thread {

    public Priority(String name){
        super(name);
    }
    public static void main(String[] args) {
        Priority low = new Priority("Low Priority Thread");
        Priority mid = new Priority("Mid Priority Thread");
        Priority high = new Priority("High Priority Thread");
        low.setPriority(Thread.MIN_PRIORITY);
        mid.setPriority(Thread.NORM_PRIORITY);
        high.setPriority(Thread.MAX_PRIORITY);
        low.start();
        mid.start();
        high.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            String a = "a";
            for(int j = 0 ; j < 10000000 ; j++){
                a += "a";
            }
            System.out.println(Thread.currentThread().getName() + " - Priority: " + Thread.currentThread().getPriority() + "- count: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

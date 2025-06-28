package Synchronization.Java_Monitor;

public class MyThread extends Thread {

    private final Counter counter;

    public MyThread(String name , Counter counter) {
        super(name);
        this.counter = counter;
    }


    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            this.counter.increment();
        }
    }
}

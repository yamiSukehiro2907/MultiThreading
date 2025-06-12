package Basic.Methods;

import java.util.ArrayList;
import java.util.List;

public class Example {
    public static void main(String[] args) throws InterruptedException {
        /*
        for (int i = 0; i < 101; i++) {
            ExThread thread = new ExThread(i);
            thread.start();
            thread.join(); // we wait for each thread to be executed first
        }
        */
        List<ExThread> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) list.add(new ExThread(i));
        for (ExThread temp : list) {
            temp.start();
            printStates(list);
            temp.join();
        }

    }

    private static void printStates(List<ExThread> list) {
        for (ExThread temp : list) {
            System.out.println(temp.getState() + " Thread-" + temp.getCount());
        }
        System.out.println();
    }
}

class ExThread extends Thread {

    private final int count;

    public ExThread(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println(count + " Running -> " + Thread.currentThread().getName());
    }

    public int getCount() {
        return count;
    }
}



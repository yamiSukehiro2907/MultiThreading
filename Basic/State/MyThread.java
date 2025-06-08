package Basic.State;

class MyThread extends Thread {
    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        System.out.println(t1.getState()); // currently NEW state
        t1.start();
        System.out.println(t1.getState()); // currently RUNNABLE state
        Thread.sleep(2000); // main thread is put to sleep
        System.out.println(t1.getState()); // now t1 thread is in running state
        t1.join(); // this makes sure that first t1 thread should be terminated
        System.out.println(t1.getState()); // t1 thread has been executed
    }
    /*
    Main-thread -> RUNNABLE        -> WAIT
    t1 ->          NEW -> RUNNABLE ->
    */

    @Override
    public void run() {
        System.out.println("RUNNING"); // Before putting, it to sleep it in  running state
        try {
            Thread.sleep(3000); // putting it to sleep gives CPU to Main-thread for execution
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

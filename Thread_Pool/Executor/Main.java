package Thread_Pool.Executor;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[10];
        for (int i = 1; i < 10; i++) {
            final int k = i;
            threads[i] = new Thread(() -> {
                try {
                    System.out.println(factorial(k));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            threads[i].start();
        }
        for (int i = 1; i < 10; i++) {
            threads[i].join();
        }
        System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
    }

    private static int factorial(int k) throws InterruptedException {
        if (k <= 0) return 1;
        return factorial(k - 1) * k;
    }
}

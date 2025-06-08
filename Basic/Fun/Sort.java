package Basic.Fun;

public class Sort {
    private static Thread t1 = new Thread();
    public static void main(String[] args) {
        int arr[] = {10, 9, 8, 7, 6, 4, 3, 2, 1};
        System.out.println(Thread.currentThread().getName());
        Sorting sort = new Sorting(arr);
        Thread t1 = new Thread(sort);
    }
}

class Sorting implements Runnable {
    @Override
    public void run() {
        System.out.println("Starting the Thread...");
    }

    private int[] arr;

    public Sorting(int[] arr) {
        this.arr = arr;
    }

    private void sort(){

    }
}

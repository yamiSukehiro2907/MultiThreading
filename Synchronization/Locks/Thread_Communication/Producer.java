package Synchronization.Locks.Thread_Communication;

public class Producer implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            sharedResource.produce(i);
        }
    }

    private final SharedResource sharedResource;

    public Producer(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }
}

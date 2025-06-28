package Synchronization.Java_Monitor;

import lombok.Getter;

@Getter
public class Counter {
    private int count;

    public Counter() {
        this.count = 0;
    }

    public synchronized void increment() {
        this.count++;
    }
    /*
    Synchronized keyword is used to invoke the already existing Java Monitor that makes sures that only one thread can access the critical section
    -> Every object has this as it's attribute which is invoked by synchronized
    -> It does not follow the FCFS rule so the threads which came later also can jump over previous one arrived (unorganized)
    */

}

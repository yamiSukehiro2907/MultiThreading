package Synchronization.Locks;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
public class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
        this.lock = new ReentrantLock();
    }

    private final Lock lock;

    public void withdraw(int amount) {
        /*
        System.out.println(Thread.currentThread().getName() + " is accessing the bankAccount.");
        try {
            if (balance >= amount) {
                Thread.sleep(10000);
                balance -= amount;
            } else {
                System.out.println(Thread.currentThread().getName() + " insufficient balance");
            }
        } catch (InterruptedException e) {
            System.out.printf(e.getMessage());
        } finally {
            System.out.println(Thread.currentThread().getName() + " remaining balance : " + balance);
        }
        */
        System.out.println(Thread.currentThread().getName() + " is accessing the bankAccount.");

        try {
            if (lock.tryLock(3000, TimeUnit.MILLISECONDS)) {
                try {
                    if (balance >= amount) {
                        Thread.sleep(3000);
                        balance -= amount;
                    } else {
                        System.out.println(Thread.currentThread().getName() + " insufficient balance");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire the lock !! Please try again later");
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        } finally {
            System.out.println(Thread.currentThread().getName() + " remaining balance : " + balance);
        }

    }
}

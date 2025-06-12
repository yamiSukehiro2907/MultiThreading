**Synchronization**:- When multiple workers (threads) try to access a shared resource then it can corrupt the data

- The condition is called **Race Conditions**.
- The portion that is accessed is called **Critical Section**.

****Methods of synchronizing:****

1. **Synchronized Method** :- Either we synchronize the complete function like:
    ```
    public void increment(){
        count++;
    }
    ```
2. **Synchronized Block** :- Or we can synchronize a block of code containing Critical Section:
   ```
     public void increment(){
        synchronized(this){
            count++;
        }
     }
   ```
3. **Static Synchronization** :- Synchronize static methods to ensure only one thread can execute them for the class,
   not the instance
   ```
   class Counter {
     private static int count = 0;

     public static synchronized void increment() {
        count++;
     }

     public static int getCount() {
        return count;
     }
   }
   ```

- **Mutual Exclusion**

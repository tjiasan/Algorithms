
/*
    Problem: avoid deadlocks when a thread tries to acquire locks on 2  accounts,

    thread1 and thread 2 have same accounts trying to locked
    t1 has acc1 locked, t2 ahs acc2 locked
    resulting in deadlock;

    Solution: 
    Each account has a lock and unique id

    if a transaction from id1 to id 2;
    and another thread is from id2 to id1;

    if locks are always acquired id1, then id2, 
    there would never be a deadlock

    use dual nested synchronize on the account objects,
    allowing only one thread at a time to run on it, and naturally queued;

*/


import java.util.HashMap;

class Account {
    int amount; 
    public Account(){
        amount = 100;
    }
}

class AccountsLock {

    public HashMap<Integer, Account> locks;

    public AccountsLock (){
        locks = new HashMap<Integer, Account>();

        for (int i = 0; i < 10; i ++){
            locks.put(i, new Account());
        }
    }

}

class AccountProcessor  extends Thread {
    int first; 
    int second;
    int from;
    int to;
    int amount;
    AccountsLock acclock;

    public AccountProcessor (AccountsLock l, int f, int t, int a){
        if (f < t){
            first = f;
            second = t;
        } else {
            first = t;
            second = f;
        }

        from = f;
        to = t;
        acclock = l;
        amount = a;
    }

    public void run(){
        Account acc1 = acclock.locks.get(first);
        Account acc2 = acclock.locks.get(second);

        try {
            synchronized (acc1){
                synchronized(acc2){
                   // System.out.println("transferring");
                    if (first == from){
                        acc1.amount -= amount;
                        acc2.amount += amount;
                    } else {
                        acc1.amount += amount;
                        acc2.amount -= amount;
                    }
                }
            }
        } finally {
           // System.out.println("done");
        }

    }

}

public class Deadlock {


    public static void main (String args[]){

        AccountsLock lock = new AccountsLock();

        Deadlock d = new Deadlock ();

        for (int i = 0; i < 100; i ++){
            AccountProcessor transaction1 = new AccountProcessor(lock, 1, 4, 50);
            AccountProcessor transaction2 = new AccountProcessor(lock, 4, 1, 50);
    
    
            transaction1.start();
            transaction2.start();
            try {
                transaction1.join();
                transaction2.join();
            } catch (InterruptedException e){
    
            }
            int amount = lock.locks.get(1).amount;
            
            if (amount != 100){
                System.out.println("error");
            }
        }

        
    }

}
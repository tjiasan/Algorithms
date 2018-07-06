
/*
    Problem: create an object that is write protected, 
                such taht multiple threads can read the object at the same time
                when it's reading, but when a thread is writing, it's locked for
                reading

    Solution : 1) java native library, reentrantreadwritelock has read/write lock
                    where readlock can be acquired by multiple threads at once
                    and writelock can only be acquired by on thread;


                       
*/

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class WriteProtect {
    int data;
    ReadWriteLock lock;
    Lock writeLock;
    Lock readLock;

    public WriteProtect(){
        data = 1;
        lock = new ReentrantReadWriteLock();
        writeLock = lock.writeLock(); 
        readLock = lock.readLock();   

    }

    public int read (){
        try {
            readLock.lock();
            return data;
        } finally {
            readLock.unlock();
        }       
    }

    public void write (int new_data){
        

        try {
            writeLock.lock();
            data = new_data;
        } finally {
            writeLock.unlock();
        }            
    }
}

public class ReadWrite implements Runnable {

    WriteProtect object;

    public ReadWrite (WriteProtect o){
        object = o;
    }

    public static void main (String args[]){
        WriteProtect obj = new WriteProtect();

        Thread t1 = new Thread(new ReadWrite(obj));

        t1.start();
    }

    public void run (){
        object.read();
        object.write(2);
    }
}


//one way to implement lock 
class LockImplementation {

    int reading;
    int writing;

    public LockImplementation (){
        reading = 0;
        writing = 0;
    }

    public void readLock (){
        if (writing > 0){
            try {
                wait();
            } catch (InterruptedException e){

            }
            
        }
        reading ++;
    }

    public void readUnlock(){
        if (reading > 0){
            reading --;
        }
        if (reading == 0){
            notifyAll();
        }   
          
    }

    public void writeLock(){
        while (reading > 0 || writing > 0){
            try {
                wait();
            } catch (InterruptedException e){
                
            }
        }
        writing ++;

    }
    public void writeUnlock(){
        writing --;
        notifyAll();
    }

}
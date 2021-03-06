//one way to implement the lock
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
                this.wait();
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
            this.notifyAll();
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
        this.notifyAll();
    }

}


class WriteProtect {
    int data;
    LockImplementation lock;


    public WriteProtect(){
        data = 1;
        lock = new LockImplementation(); 

    }

    public int read (){

        synchronized(lock){
            try {
                lock.readLock();
                return data;
            } finally {
                lock.readUnlock();
            }   
        }
    
    }

    public void write (int new_data){
        synchronized (lock){
            try {
                lock.writeLock();
                data = new_data;
            } finally {
                lock.writeUnlock();
            }   
        }          
    }
}

public class Implement implements Runnable {

    WriteProtect object;

    public Implement (WriteProtect o){
        object = o;
    }

    public static void main (String args[]){
        WriteProtect obj = new WriteProtect();

        Thread t1 = new Thread(new Implement(obj));

        t1.start();
    }

    public void run (){
        object.read();
        object.write(2);
    }


}
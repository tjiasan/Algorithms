/*
    Problem: analyze the code
            what is min and max value;

   Solution: max value is obviously 2N, perfect scheduling
            min value is N if N = 1;
            
            worst case scheduling: where N >= 2;

            t1 reads.. inrements...lags
            t2 is n - 1 complete, write to memory

            t1 writes 1, 
            t2 reads 1, increment;

            t1 completes and writes
            t2 writes..2

            so min is 2; 
*/


class RunningThread implements Runnable {
    private Thread t;
    private String threadName; 
    private int N;
    private CounterObject count;
    
    public RunningThread (String name, CounterObject c, int amount){
        threadName = name;
        N = amount;
        count = c;
    }

    public void run (){      
     
        for (int i = 0; i < N; i++){  
                  
            count.counter ++;           
        }
       // System.out.println(count.counter);
    }

    public void start (){
        if (t == null){       
            t = new Thread(this, threadName);
            t.start();
        }
    }

    public void join(){
        try {
             t.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}

class CounterObject {
    public int counter;

    public CounterObject(){
        counter = 0;
    }
}


public class Increment {
    private CounterObject count;

    public void start (int N){
        count = new CounterObject();
        RunningThread Thread1 = new RunningThread("t1", count, N);
        RunningThread Thread2 = new RunningThread("t2", count, N);

        Thread1.start();
        Thread2.start();

        Thread1.join();
        Thread2.join();
        System.out.println(count.counter);
 
    }


    public static void main (String [] args){

        Increment inc = new Increment();

        inc.start(5000000);

    }
}
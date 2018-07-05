/*
    Problem:
        Write a program that runs 2 threads simultaneously
        where 1 thread prints odd,
        the other thread prints even,

        stop at 100;

    Solution:
        1) SynchronizedRun: see below
        2) Interleaved : see next file!!

*/

class CounterObject {
    public int count;

    public CounterObject(){
        count = 0;
    }

    public void increment (String name){
        count ++;
        System.out.println(name + ": " + count);
    }

}

class CustomThread extends Thread{
    CounterObject count;
    String thread_name;

    public CustomThread(String name, CounterObject c){
        count = c;
        thread_name = name;
    }

    public void run(){
        while (count.count < 100){
  
            synchronized(count){
                count.increment(thread_name);
            }
        }
    }
}

public class IncrementSync  {
 
    public void start(){
        CounterObject count = new CounterObject();
        CustomThread t1 = new CustomThread("t1", count);
        CustomThread t2 = new CustomThread("t2", count);

        t1.start();
        t2.start();

    }

    public static void main (String [] args){

        IncrementSync inc = new IncrementSync();

        inc.start();

    }
}
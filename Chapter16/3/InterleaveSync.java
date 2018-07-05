/*
    Problem:
        Write a program that runs 2 threads simultaneously
        where 1 thread prints odd,
        the other thread prints even,

        stop at 100;

    Solution:
        1) SynchronizedRun: see prev file, first available thread
        2) Interleaved : see this file

*/

class CounterObject {
    public int count;
    public String owner;

    public CounterObject(){
        count = 0;
    }

    public void increment (String name, String next){
        count ++;
        owner = next;
        System.out.println(name + ": " + count);
    }

}

class CustomThread extends Thread{
    CounterObject count;
    public String thread_name;
    public String next;

    public CustomThread(String name, CounterObject c){
        count = c;
        thread_name = name;
    }



    public void run(){
        while (count.count < 100){
          
            synchronized(count){
                try {
                    while (count.owner.equals(thread_name) == false){
                        count.wait();
                    }
                } catch (InterruptedException e){

                }

                count.increment(thread_name, next);
                count.notify();
            }
          
        }
    }
}

public class InterleaveSync  {
 
    public void start(){
        CounterObject count = new CounterObject();
        CustomThread t1 = new CustomThread("t1", count);
        CustomThread t2 = new CustomThread("t2", count);

        t1.next = t2.thread_name;
        t2.next = t1.thread_name;

        count.owner = t1.thread_name;

        t1.start();
        t2.start();

    }

    public static void main (String [] args){

        InterleaveSync inc = new InterleaveSync();

        inc.start();

    }
}
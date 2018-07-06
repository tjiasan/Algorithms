/*
    Problem: 
            test the Collatz conjecture
            in parallel

            from 1 to N, 


            conjecture: 
            any number, multiply by 3 


*/
import java.util.HashMap;

class Visited {

    HashMap<Integer, Boolean> seen;

    public Visited (){
        seen = new HashMap<Integer, Boolean> ();
    }
}

class Runner extends Thread {
    int start;
    int max;   
    int thread;
    Visited visit;

    public Runner(int s, int m, int th, Visited v){
        start = s;
        max = m;
        thread = th;
        visit = v;
    }

    public void test (int number){
        synchronized (visit){
            if (number == 1 || visit.seen.get(number) != null){
                return;
            }
            visit.seen.put(number, true);
        }
      
        if (number % 2 == 0){
            this.test(number / 2);
        } else {
            this.test(number * 3 + 1);
        }

    }


    @Override 
    public void run (){
        for (int i = start; i < max; i+= thread ){
           // System.out.println("Thread " + start + " Checking " + i);
            this.test(i);
        }
    }

}


public class Collatz {


    public static void main (String[] args){
        int threads = 4;
        int max = 50000;
        Visited v = new Visited();
        for (int i = 1 ; i <= threads; i++){
            Runner r = new Runner(i, max, threads, v);
            r.start();
        }

    }
}
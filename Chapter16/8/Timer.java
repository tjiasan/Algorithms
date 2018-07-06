/*
    Problem : implement a timer, such that the constructor accepts
    a runnable class, 

    should be able to start at any time
    and interrupt at any time;


    Solution: use hashmap to store new running threads;

    running threads are custom threads that sleep first;

    to stop, access thread via hashmap and interrupt, 
    can even delete them;
*/



import java.util.HashMap;

class Runner extends Thread {
    int time = 0;
    Runnable runnable;

    public  Runner  (Runnable run, int t) {
        time = t;
        runnable = run;
    }

    @Override
    public void run () {
      
        try {
            this.sleep(time);
        } catch (InterruptedException e){

        }
        
        runnable.run();
    }
}

class Input implements Runnable {

    public void run () {
        System.out.println("done");
    }
}



public class Timer  {
    HashMap<Integer, Runner> runners ;
    Runnable runnable;

    public Timer (Runnable run) throws InterruptedException {
        runners = new HashMap<Integer, Runner>();
        runnable = run;
    }

    public void execute (int id, int time){
        Runner r = new Runner(runnable, time);
        r.start();

        runners.put(id, r);
    }

    public void interrupt(int id){
        System.out.println("Interupted");
        Runner r = runners.get(id);
        r.interrupt();
    }

    public static void main(String args[]){
        try {
            Timer s = new Timer (new Input());
            s.execute(1, 3000);
           // s.interrupt(1);
        } catch (InterruptedException e){
            System.out.println("Interupted");
        }        

    }


}
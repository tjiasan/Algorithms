/*
    Implement a blocking queue /thread pooling 

    to limit workloads to amount of threads

*/


import java.io.IOException;
import java.lang.InterruptedException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPoolServer implements Runnable {
  
    BlockingQueue<Socket> q;

    ThreadPoolServer ( BlockingQueue<Socket> queue) {
       
        this.q = queue;
     }

    public static void main (String args[]){
        ServerSocket ssock = null;
        try {
            ssock = new ServerSocket(1234);
        } catch (IOException e){

        }
        
        System.out.println("Listening");
        int thread_pool = 3;
        BlockingQueue<Socket> queue = new LinkedBlockingQueue<Socket>(thread_pool);

        for (int i = 0; i < thread_pool; i ++){
            new Thread(new ThreadPoolServer(queue)).start(); 
            // if save threads to array, can add/end threads on demand
    
        }    

        while (true){
            Socket sock = null;
            try {
                sock = ssock.accept(); 
            } catch (IOException e){
                continue;
            }
           
            System.out.println("Connected");            
            try {
                queue.put(sock);
            } catch (InterruptedException e){
                continue; 
            }
        }

    }

    public void run() {
     
        while (true){ // daemonize, erase to make one off scalable threads
            try {            
                Socket csocket = null;
                try {
                    synchronized (q){
                        csocket = q.take();
                    }                
                } catch (InterruptedException e){
                    System.out.println("interrupted");
                    continue;
                    //don't want to end thread if interrupted
                }         
        
            PrintStream pstream = new PrintStream(csocket.getOutputStream()); //throw ioexecption
            for (int i = 100; i >= 0; i--) {
                pstream.println(i + " bottles of beer on the wall"); //returns to conector via curl localhost:port
            }
            
            pstream.close();
            csocket.close();
            } catch (IOException e) {
                 System.out.println(e);
            }
        }
    }


}
/*
    Problem: create multithreaded http server

    Solution: standard implementation of multi-thread server
    start a new thread for every request.
    only works for lightly loaded servers

*/


import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer implements Runnable {
   Socket csocket;
   MultiThreadServer(Socket csocket) {
      this.csocket = csocket;
   }
   public static void main(String args[]) throws Exception { 
      ServerSocket ssock = new ServerSocket(1234);
      System.out.println("Listening");// one time
      
      while (true) {  //listening loop
         Socket sock = ssock.accept(); //Listens for a connection to be made to this socket and accepts it.
         System.out.println("Connected");
         new Thread(new MultiThreadServer(sock)).start(); // invokes run 
      }
   }
   public void run() {
      try {
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
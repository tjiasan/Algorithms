/*
    Problem: 
            SQL queries are asked from the clients to the server, 
            service time is known in advance, 
            queries processed one at a time in any order;
            time a query wait before its turn is the waiting time;

            compute a schedule to minimize waiting time

            e.g. service times are (2, 5, 1 ,3)

            waiting time would be 
            0 + 2 + (2 + 5) + (2 + 5 + 1) = 17

            min waiting time is 10;


    Solution: 
        sort and process fastest one first
        
        since the first one is repeated many times, want to have that the least

        (1, 2, 3, 5);

        0 + 1 + (1 + 2) + (1 + 2 + 3) = 10
*/

import java.util.Arrays;

public class Waiting {

    public void process(int[] times){
        Arrays.sort(times);

        System.out.println(Arrays.toString(times));
    }

    public static void main (String args[]){
        Waiting waiting = new Waiting();

        int[] times = {1, 2, 5, 3};

        waiting.process(times);
    }   

}
/* Problem: Given a schedule with different start and finish times,
            Find the number of overlapping schedules.


   Solution: 
            (book) create a seperate array of start and finish, 
                sort
                iterate through array, 
                if encounter start, 
                add concurency, else
                subtract
                
                Complexity :
                2nlog2n + 2n time + n space;
                

        (mine)
        sort array by start;

        ITERATE THROUGH ARRAY
            Find k, 
            where A[k][0] (start) is equal to or below A[i][1](end) ;
            using binary search
            put k in hash table add 1 if increased;


            if (hashtable (i)){
                concurrency -= events ending;
            }

            increase concurrency; 
           
            if (concurrency > concurrency_max){
                record;
            }


        Space O (N) max, Time O(nlogn + nlog(M))

        M is decreasing as iterating;

*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import java.util.HashMap;

class ComparatorEvent implements Comparator <int[]> {

    @Override 
    public int compare (int[] a, int[] b){
        if (a[0] < b[0]){
            return -1;
        } else if (a[0] == b[0]){
            return 0;
        } else {
            return 1;
        }

    }
}


public class Calendar {
    public int find (int[] [] events, int i){
        if (i + 1 == events.length){
            return -1;
        }
        int insert = events[i][1];

        int left = i + 1;

        int right = events.length -1;

        if (insert > events[right][0]){
            return -1;
        }

        int mid = (left + right)/2;

        while (right - left > 1){
            if (events[mid][0] < insert){
                left = mid;
            } else {
                right = mid;
            }
            mid = (left + right)/2;
        }


        return right;
    }


    public int concurrent (int[] [] events){

        ComparatorEvent eventComp = new ComparatorEvent();

        Arrays.sort(events, eventComp);

        int levels = 0;
        int max = 0;
        HashMap <Integer, Integer> end = new HashMap <Integer, Integer>();
    
        for (int i = 0; i < events.length; i++){

            if (end.get(i) != null){
                levels -= end.get(i); 
                end.remove(i);
            }

            levels ++;

            int end_location = this.find (events, i);
        
            
            if (end.get(end_location) == null){
                end.put(end_location, 1);
            } else{
                Integer e = end.get(end_location) + 1;
                end.put(end_location, e);
            }
            

            if (levels > max){
                max = levels;
            }

        }

        System.out.println(max);

        return levels;

    }



    public static void main (String args[]){

        Calendar calendar = new Calendar();


        int [][] events = {
            {100, 500 },
            {600, 1000},
            {1100, 1300},
            {1400, 1500},
            { 200, 700},
            { 800, 900},
            { 400, 500 },
            {900, 1700} 
        };

        calendar.concurrent(events);

    }
}
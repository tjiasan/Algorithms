/*
    Problem: given earth is at {0, 0, 0},

            compute the closest stars to earth;
    
    Solution: preload max-heap with k stars;

            iterate through stars, 
            if found smaller distance to max of the heap,
            kick out max, 

            put in the smaller one in the heap,


            distance is computed 

            d ^2 = x^2 + y^2 + z^2;

            so it's proportional to x^2 + y^2 + z^2; 

   Variant: print out the kth largest element from kth position in n arrays,

            iterate from k,
            build heap k size, 
            if higher than max, poll, add
            after ends, find min in heap (array);
   
*/

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Arrays;

class MaxComparator implements Comparator <int[]> {

    public int distanceProportional(int[] coord){

        
        return coord[0]*coord[0] + coord[1]*coord[1]  + coord[2]*coord[2]; 
    }


    @Override
    public int compare (int[] x, int[] y){

        int distX = this.distanceProportional(x);
        int distY = this.distanceProportional(y);


        if (distX > distY){
            return -1;
        } else if (x == y){
            return 0;
        }
        return 1;
    }

}

public class Closest {

    public int distanceProportional(int[] coord){        
        return coord[0]*coord[0] + coord[1]*coord[1]  + coord[2]*coord[2]; 
    }

    public int [] [] findClosest(int [] [] input, int k){
        int [] [] result = new int [k] [3];

        MaxComparator comparator = new MaxComparator();

        PriorityQueue<int[]> max_heap = new PriorityQueue<int[]>(k, comparator);

        for (int i = 0; i < k; i++){
            max_heap.add(input[i]);
        }
        int max = this.distanceProportional(max_heap.peek());
        for (int i = k; i < input.length; i++){
           int current = this.distanceProportional(input[i]);

            if (current < max){
                max = this.distanceProportional(max_heap.poll());
                max_heap.add(input[i]);
            }
        }

        int counter = 0;
     
        while(max_heap.isEmpty() == false){
            
            result[counter] = max_heap.poll();
            System.out.println(Arrays.toString(result[counter]));
            counter ++;
        }

        return result;
    }

    public static void main (String args[]){

        Closest closest = new Closest ();


        int [] [] input = {
            {0, 0, 1},
            {1, 1, 2},
            {2, 2, 0},
            {3, 5, 10},
            {2, 2, 24},
            {5, 2, 1},
            {3, 4, 5}
        };

        int result[] [] = closest.findClosest(input, 4);

        //System.out.println(Arrays.toString(result[0]));
    }





}
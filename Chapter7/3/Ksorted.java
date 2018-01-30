
/*
    Problem: Sort an almost sorted array, where
            each number is at most k position away from being sorted;

    Solution: use a max heap, 
    
            prime the heap with the first number, 

            iterate through input, 
                if smaller, put in max_heap;

                else, poll max heap until zero, write over previous input;
                add current integer to max_heap;

          complexity : O (n log k), space O(k);      
*/

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Arrays;


class MaxComparator implements Comparator <Integer> {

    @Override
    public int compare (Integer x, Integer y){
        if (x > y){
            return -1;
        } else if (x == y){
            return 0;
        }
        return 1;
    }

}

public class Ksorted {


    public int[] sort (int[] input){

        MaxComparator comparator = new MaxComparator();

        PriorityQueue<Integer> max_heap = new PriorityQueue<Integer>(5, comparator);

        max_heap.add(input[0]);

        for (int i = 1; i < input.length; i ++){
            int max = max_heap.peek();

            if (input[i] < max){
                max_heap.add(input[i]);
            } else {
                int counter = 1;
                while (max_heap.isEmpty() == false){
                    input[i - counter] = max_heap.poll();
                    counter ++;
                }
                max_heap.add(input[i]);
            }

        }

        return input;

    }


    public static void main (String args[]){

        Ksorted ksort = new Ksorted();


        int [] input = {3, -1, 2, 6, 4, 5, 8};


        int[] result = ksort.sort(input);

        System.out.println(Arrays.toString(result));
    }



}
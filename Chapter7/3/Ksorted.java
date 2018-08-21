
/*
    Problem: Sort an almost sorted array, where
            each number is at most k position away from being sorted;
            each number is at most 2 away from final position;

    Solution: use a max heap, 
    
            prime the heap with k numbers, 

            and roll through it (extract one, add next one) while overriding input;

            poll k numbers and override input

            complexity O(nlogk)
*/

import java.util.PriorityQueue;
import java.util.Arrays;



public class Ksorted {


    public int[] sort (int[] input, int k){

        

        PriorityQueue<Integer> min_heap = new PriorityQueue<Integer>(k + 1);

        for (int i = 0; i < k + 1; i++){
            min_heap.add(input[i]);
        }        

        for (int i = k + 1; i < input.length; i ++){
            input[i - k - 1] = min_heap.poll();
            min_heap.add(input[i]);
        }

        for (int i = input.length - k - 1; i < input.length; i++){
            input[i] = min_heap.poll();
        }

        return input;

    }


    public static void main (String args[]){

        Ksorted ksort = new Ksorted();


        int [] input = {3, -1, 2, 6, 4, 5, 8};
       

        int[] result = ksort.sort(input, 2);

        System.out.println(Arrays.toString(result));
    }



}
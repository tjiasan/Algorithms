/*
    Problem : Givena lot of sorted arrays, merge them into one big sorted array


    Solution: use a min heap with k storage, where k is the number of arrays;
            as an element is removed from the heap, add to result;

            and then add the next element of the input that was removed to the heap;

            Complexity O (n log k) space O (k) space;
            
*/

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Arrays;


class HeapComparator implements Comparator <int []> {
    @Override 
    public int compare (int[] x, int [] y){
        if (x[0] < y[0]){
            return -1;
        } 
        if (x [0] == y[0]){
            return 0;
        }
        return 1;
    }
}

public class MergeSorted {


    public int[] merge (int[] [] input){
        int final_size = 0;

        HeapComparator compare = new HeapComparator();

        PriorityQueue<int[]> min_heap = new PriorityQueue<int []> (input.length, compare);

        for (int i = 0; i < input.length; i ++){
            int [] data = { input[i][0], i, 0 }; // element, array_index, location_index
            
            min_heap.add(data);

            final_size += input[i].length;
        }

        int [] result = new int [final_size];


        for (int i = 0; i < result.length; i ++){

        
            int [] removed = min_heap.poll();
            result[i] = removed[0];

            int next = removed[2] + 1;

            if (input[removed[1]].length > next){
                removed[2] = next;
                removed[0] = input[removed[1]][next];
                min_heap.add(removed);
            }            
        }          

        return result;
    }

    public static void main (String args[]){

        MergeSorted mergeSorted = new MergeSorted();
        int [] [] input = {
            {2, 3, 3, 5},
            {1, 2, 3, 5, 5, 7},
            {1, 5, 9}
        };

        int [] merged =  mergeSorted.merge (input);

        System.out.println(Arrays.toString(merged));
    }



}
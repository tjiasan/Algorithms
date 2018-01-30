/* Problem:
    Given an array that increases and decreases k times, 
    design an efficient algorithm to sort;

    Solution:
    Use min heap; 

    add to min heap inflection points ascending (with start, stop, ascending);
    add to min heap inflection points descending (with start, stop, descending);

    when processing heap, if ascending is removed,
    increase start, and re add to heap
    else 
    decrease start, and re add to heap
    when start goes over stop, do not add;


    Complexity O(nlogk), space o(k), where k is the amount of inflection points;
*/
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Arrays;

class MinComparator implements Comparator <int []>{

    @Override
    public int compare (int[] x, int[] y){
        if (x[0] < y[0]){
            return -1;
        } else if (x[0] == y[0]){
            return 0;
        }
        return 1;
    }

}

class MaxComparator implements Comparator <int []>{

    @Override
    public int compare (int[] x, int[] y){
        if (x[0] > y[0]){
            return -1;
        } else if (x[0] == y[0]){
            return 0;
        }
        return 1;
    }

}

public class SortIncDecArr {

    public int [] sortUpDown(int [] input){
        int [] result = new int[input.length];

        MinComparator  minComparator = new MinComparator();
        MaxComparator  maxComparator = new MaxComparator();

        PriorityQueue<int[]> min_heap = new PriorityQueue<int[]>(11, minComparator);
        PriorityQueue<int[]> max_heap = new PriorityQueue<int[]>(11, maxComparator);

        boolean ascending = true;
        int val = input[0];
        int start = 0;
        int stop = 0;

        for (int i = 1; i < input.length; i ++){
            if (ascending){
                if (input [i - 1] > input[i]){
                    stop = i - 1;
                    int [] add = {val, start, stop, 1};
                    min_heap.add(add);

                    //switch;
                    ascending = false;
                    stop = i;
                }
                
            } else {
                if (input [i -1] < input[i]){
                    start = i - 1;
                    val = input [i - 1];
                    int [] add = {val, start, stop, 0};
                    min_heap.add(add);

                    //switch;
                    ascending = true;
                    val = input[i];
                    start = i;
                }
            }
        }  
        // process end
        if (ascending){
            stop = result.length -1;
            int [] add = {val, start, stop, 1};
            min_heap.add(add);
        } else {
            start = result.length - 1;
            val = input [stop];
            int [] add = {val, start, stop, 0};
            min_heap.add(add);
        }
        
        
        int counter = 0;
        while (min_heap.isEmpty() == false){
            int [] min = min_heap.poll();

            result[counter] = min[0];
            counter++; 
            //next iteration
           
            //ascending popped
            if (min [3] == 1){
                min[1]++;          

                if (min[1] <= min[2]){
                    min[0] = input[min[1]];
                    min_heap.add(min);
                }
            } else { //descending popped
                min [1] --;
                //didn't go over stop signal
                if (min[1] >= min[2]){
                    min[0] = input[min[1]];
                    min_heap.add(min);
                }
            }
        }
     

        return result;
    }

    public static void main(String args[]){


        SortIncDecArr sort = new SortIncDecArr();

        int [] input = { 57, 131, 493, 294, 221, 339, 418,452, 442, 190 };

        int[] result = sort.sortUpDown(input);

        System.out.println(Arrays.toString(result));
    }
}
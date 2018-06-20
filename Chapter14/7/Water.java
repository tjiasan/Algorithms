/*
    Problem:
            array of ints
            set of parallel lines to y axis
            starting from x = 0;
            find pair of lines with x axis that trap most water

    Solution: 
            brute force is n ^2,

            use hashmap to keep track of min and max of each height;

            sort the result object;
            using a max_heap;

            iterate through max heap, 
            if previous min is smaller and max is bigger,
            we can use that,
            else we can't 

            the biggest volume is max - min index, multipled by height of current iteration;
            taller heights is treated the same as current height;

            thus O(dlogd + n) time complexity because of sorting,
            and O(d) space; where d is distinct elements 



*/
import java.util.HashMap;
import java.util.Comparator;
import java.util.PriorityQueue;

class Result {
   public int height;
   public int max;
   public int min;

    public Result(int h, int i){
        height = h;
        max = i;
        min = i;
    }
}

class  ResultComparator implements Comparator<Result> {
    @Override
    public int compare (Result x, Result y){
        if (x.height < y.height){
            return 1;
        }
        if (x.height > y.height){
            return -1;
        }

        return 0;
    }
}

public class Water {

    public void biggest(int[] arr){
        Comparator<Result> comparator = new ResultComparator();
        PriorityQueue<Result> max_heap = new PriorityQueue<Result>(10, comparator);
        HashMap<Integer,Result> map = new HashMap<Integer,Result>();

        for (int i = 0; i < arr.length; i ++){
            if (map.get(arr[i]) == null){
                Result current = new Result(arr[i], i);
                max_heap.add(current);
            } else {
                Result current = map.get(arr[i]);
                current.max = i;
            }

        }

        Result prev = max_heap.poll();
        int max = (prev.max - prev.min) * prev.height;

        int index1 = 0;
        int index2 = 0;

        while (max_heap.isEmpty() == false){

            Result current = max_heap.poll();

            if (prev.max > current.max){
                current.max = prev.max;
            }

            if (prev.min < current.min){
                current.min = prev.min;
            }

            int current_max = (current.max - current.min) * current.height;

            if (current_max > max) {
                max = current_max;
                index1 = current.max;
                index2 = current.min;
            }

            prev = current;
        }

        System.out.println(index1);
        System.out.println(index2);
    }


    public static void main (String args[]) {
        int[] arr = {1, 2, 1, 3, 4, 4, 5, 6, 2, 1, 3, 1, 3, 2, 1, 2, 4, 1};

        Water water = new Water();
        
        water.biggest(arr);
        
        //max is 4 and 16

        // area is min of heights * dist between indeces;
    }


}
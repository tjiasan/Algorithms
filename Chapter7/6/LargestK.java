/*
    Problem: given a maxHeap array,
             Extract the k largest elements;

     
    Solution: Iterate through maxHeap level by level;
    
        load first level into a stack;
            while level isn't empty:
                Initialize next_level stack;
                while level isn't empty:
                        pop level
                        if lower than k iterations, load
                        input into heap, while loading children
                        to next_level stack,

                        if higher than k iterations, 
                        if current is lower than min heap, do nothing
                        else, remove lowest heap, add current input 
                            add its children to next level stack;
                level = next_level;


        Space Complexity O(K), time complexity, O(K Log K) 
        
        why? non highest level branches are terminated early.
*/




import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Arrays;
import java.util.Stack;

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

public class LargestK{

    public int [] extractLargest(int[] input, int k){
        int [] result = new int[k];

        PriorityQueue<Integer> min_heap = new PriorityQueue<Integer>();

        int last = input.length;
     
        Stack<Integer> level = new Stack<Integer>();

        level.add(0);

        while (level.isEmpty() == false){

            Stack<Integer> next_level = new Stack<Integer>();

            while (level.isEmpty() == false){

                if (min_heap.size() < k){
                    int parent_index = level.pop();
                    int parent = input[parent_index];
    
                    min_heap.add(parent);
                    
                    int child = parent_index *2;
    
                    if (child +1 < last){
                        next_level.push(child + 1);
                    }
                    if (child + 2 < last){
                        next_level.push(child + 2);
                    }    
                } else {
                    int parent_index = level.pop();
                    int parent = input[parent_index];

                    int min = min_heap.peek();

                    if (parent > min) {
                        min_heap.remove();
                        min_heap.add(parent);
                    
                        int child = parent_index * 2;
        
                        if (child + 1 < last){
                            next_level.push(child + 1);
                        }
                        if (child + 2 < last){
                            next_level.push(child + 2);
                        }  
                    }                    
                }
            }
            level = next_level;
        }

        for (int i = 0; i < k; i++){
            result [i] = min_heap.poll();
        }


        return result;
    }


    public static void main (String args[]){

        LargestK largestK = new LargestK();

        int[] input = { 561, 314, 401, 28, 156, 359, 271, 11, 3};

        int[] result = largestK.extractLargest(input, 5);

        System.out.println(Arrays.toString(result));
    }



}
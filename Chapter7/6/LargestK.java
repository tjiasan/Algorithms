/*
    Problem: given a max heap represented as an array
             Extract the k largest elements;
           e.g.{ 561, 314, 401, 28, 156, 359, 271, 11, 3};
                children are 2n+1 2n + 2

                prime stack with 561;

                


           561  
           / \
        314    401 
        / \    / \
      28 156 359 271
    /\
   11 3

    Solution: Iterate through maxHeap level by level;
    
                prime stack with 561;

                


           561  -->  1) prime into stack
           / \
        314    401 --> 1)load into next stack 
        / \    / \
      28 156 359 271
    /\
   11 3

    while (level_stack isn't empty){
        current= stack.pop()

        if (min_heap isn't full){
            add current to min_heap;
            put children into next_stack;
        } else {
            compare with min of heap,
                if (more){
                    poll heap,
                    add current to heap
                    load children into next_stack;
                }
        }

        stack = next_stack
    }
       
  

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

                    if (parent >= min) {
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
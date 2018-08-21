/*
Problem: Implement a stack with a heap
Solution: use a max heap,
initialize max heap to zero;

current_max is analogous to index;
add and int array with [current_max, data],
and increment current_max/index;


*/

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Arrays;


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
public class Stack {

    public int max;
    public PriorityQueue<int[]> max_heap;


    public Stack (){
        MaxComparator maxComparator = new MaxComparator();
        max = 0;
        max_heap = new PriorityQueue <int[]> (10, maxComparator);
    }

    public void push (int n){
        int[] add = {max, n };
        max ++;

        max_heap.add(add);
    }

    public int pop (){
        if (max == 0){
            return 0;
        }

        max --;

        int[] data = max_heap.poll();

        return data[1];
    }



    public static void main (String args []) {

        Stack stack = new Stack();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }


}
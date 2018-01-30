/*
    Problem: output a running median from streaming input of integers;

    Solution: use two heaps;

            left : max heap
            right : min heap

            prime left heap with first,

            iterate from second;

            if less than left,
            put  integer in max_heap
            else put in right_heap;

            if iteration is even:
                left_heap should be same size as right s
            if odd (left should be greater than right by 1)   

            while uneven, poll bigger one, and push to the smaller one;

            for odd, peek at left,
            for even, peek at right;

            Complexity O(log n) per entry;
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

public class Median {

    public PriorityQueue <Integer> maxHeap;
    public PriorityQueue <Integer> minHeap;
    public boolean odd;

    public void getMedian (int n){
        
       

        int left = maxHeap.peek();

        if (n < left){
            maxHeap.add(n);
        } else {
            minHeap.add(n);
        }

        int offset;
        if (odd){
            offset = 1;
        } else {
            offset = 0;
        }

        while (maxHeap.size()  != minHeap.size() + offset ){
            if (maxHeap.size() > minHeap.size() + offset){
                minHeap.add(maxHeap.poll());
            } else {
                maxHeap.add(minHeap.poll());
            }
        }

        if (odd){
            System.out.println(maxHeap.peek());
        } else{
            System.out.println((double) ( minHeap.peek() + maxHeap.peek() )/2);
        }        

        
    }


    public void stream (int [] input){
        MaxComparator maxComparator = new MaxComparator();
        maxHeap = new PriorityQueue<Integer>(11, maxComparator);
        minHeap = new PriorityQueue<Integer>();

        odd = true;
        maxHeap.add(input[0]);
        System.out.println(input[0]);

        for (int i = 1; i < input.length; i++){
            if (odd){
                odd = false;
            } else {
                odd = true;
            }

            this.getMedian(input[i]);
        }

    }

    public static void main (String args[]){

        Median median = new Median();
        int [] input = {1, 0, 3, 5, 2, 0, 1};

        median.stream(input);

    }



}
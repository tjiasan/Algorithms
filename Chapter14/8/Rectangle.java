/* Problem:
    Compute the largest rectangle under a skyline

    Given an array of heights, 
    calculate biggest

Solution:
   brute force:
    iterate every subarray, find minimum and multiply by lenght;

    my approach:
    O(N)  time O(N) space

    use a linked list to track previous starts,

    during iteration
    deque all the starts in linkedlist higher than current, while tracking
    the min_start and calculating max,
    
    keep track of min start

        if min_start lower than current index
        update in linked list
        or if putting new one, use min_start instead


*/  

import java.util.LinkedList;

class Result {
    public int data;
    public int start;

    public Result (int d, int s){
        data = d;
        start = s;
    }
}

public class Rectangle {

    public void compute (int[] arr){

        LinkedList<Result> data_points = new LinkedList<Result>();
        int max = 0;
        int index1 = 0;
        int index2 = 0;

        for (int i = 0; i < arr.length; i ++){
            if (arr[i] > max){
                max = arr[i];
                index1 = i;
                index2 = i;
            }
            int prev_start = arr.length;
            while(data_points.isEmpty() == false && data_points.peekLast().data > arr[i]){
                Result past = data_points.removeLast();
                
                if ((i - 1 - past.start) * past.data > max){
                    max = (i - 1 - past.start) * past.data;
                    
                    index1 = i -1;
                    index2 = past.start;
                }
                if (past.start < prev_start){
                    prev_start = past.start;
                }
            }

            if (data_points.isEmpty()){
                data_points.addLast(new Result(arr[i], i));
            } else {
                if (data_points.peekLast().data != arr[i]){
                    if (prev_start < i){
                        data_points.addLast(new Result(arr[i], prev_start));
                    } else {
                        data_points.addLast(new Result(arr[i], i));
                    }                    
                } else {
                    Result last = data_points.peekLast();
                    if (prev_start < last.start){
                        last.start = prev_start;
                    }
                }
            }
        
        }
      
        while(data_points.isEmpty() == false){
            Result past = data_points.removeLast();
            int last = arr.length - 1;
            if ((last - past.start) * past.data > max){
                max = (last - past.start) * past.data;
                index1 = last;
                index2 = past.start;
            }
        }
        
        System.out.println(index1);
        System.out.println(index2);
    }


    public static void main (String args[]){

        int[] arr = { 1, 4, 2, 5, 6, 3, 2, 6 ,6, 5, 2, 1, 3 };
        //ans is 1 to 10

        Rectangle rect = new Rectangle();

        rect.compute(arr);
    }
}
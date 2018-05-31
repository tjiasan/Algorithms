/*
    Problem: given a number n, fill an n x n chessboard with n queens,
    where the queens are non -attacking

    Solution: 1) iterate through a row
              2) for each row, pass through recursively the solution
                use swaps to save some space
              
                 initialize the solution (1, 2, 3, 4);
                 where arr[i] = k
                 i = row
                 k = column

              3) recursive algorigthm:
              iterate through previous result while
              calculating the illegal places, 
              put illegal places into a hashmap

              iterate through the remaining "places",

              if it's not illegal, swap with current iteration location
              and recuse forward
              



*/

import java.util.Stack;
import java.util.Arrays;
import java.util.HashMap;

public class Queen {

    public Stack results;

    public void swap (int [] arr, int i, int j){
        int k = arr[i];

        arr[i] = arr[j];
        arr[j] = k;
    }

    public int[] deep_copy (int[] source){
       int[] result = new int[source.length];

       for (int i = 0; i < source.length; i++){
            result[i] = source[i];
       }

       return result;
    }

    public void begin (int n){
        results = new Stack <int[]> ();

        int [] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = i;
        }

        for (int i = 0; i < n ; i++) {
            int [] copy = this.deep_copy(result);
            
            this.swap (copy, i ,0);           

            this.recurse(copy, 1);   
        }

    }

    public void recurse (int[] result , int iteration) {
        HashMap <Integer, Boolean> illegal = new HashMap<Integer, Boolean>();

        for (int n = 0; n < iteration; n ++){

            int upper = result[n] + iteration - n;
            int lower = result[n] - iteration + n;  

            illegal.put (upper, true);
            illegal.put (lower, true);            
        }

        for (int n = iteration; n < result.length; n++){
              if(illegal.containsKey(result[n]) == false){                 

                  int[] next_copy = this.deep_copy(result);
                  this.swap(next_copy, iteration, n);

                  if (iteration == result.length -1){
                    System.out.println(Arrays.toString(next_copy));
                  } else {
                    this.recurse(next_copy, iteration + 1);  
                  }                
              }            
        }         
    }




    public static void main (String args[]){

        Queen queen = new Queen();

        queen.begin(4);

    }
}
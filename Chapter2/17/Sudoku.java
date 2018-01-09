/*
    Problem : Check whether a sudoku 2D array is valid,
              Check for Duplicates and empty boards (0);

    Solution: Brute force approach         
              Space Complexity sqrt (N) + sqrt(n); create subarrays + hashmap; where n is the elements in 2d array
              Time Complexity (6N), checking rows (1.3N), Checking columns (2N), Checking subarrays (2N)
              Optimization, check while creating subarrays (Reduce by 2N / constant)
*/
import java.util.HashMap;
import java.util.Arrays;

public class Sudoku {

    public boolean checkValid (int [] subarr){
        boolean valid = true;
        HashMap <Integer, Integer> present = new HashMap <Integer, Integer> (); 
        for (int i = 0; i < subarr.length; i ++){
            if (subarr[i] == 0){
                return false;
            }
            if (present.get(subarr[i]) != null){
                return false;
            }
            present.put(subarr[i], 1);
        }        
        return valid;
    }


    public boolean checkValid(int[][] arr) {
        boolean valid = true;

        //check rows
        for (int n = 0; n < arr.length; n ++){
            boolean col_valid = this.checkValid(arr[n]);
  
            if (col_valid == false){
                return false;
            }
           
        }

        //check columns
        for (int n = 0; n < arr.length; n ++){
           int[] subarray = new int [9];

           for (int k = 0; k < arr[0].length; k++) {
               subarray[k] =  arr[k][n]; 
           }
           boolean col_valid = this.checkValid(subarray);    
               
            if (col_valid == false){
                return false;
            }           
          
        }
        //check 3x3 subarrays, go from top left, down, middle, down, right, down
         int sub_offset = 0;
         int offset = 0;
        for (int n = 0; n < arr.length; n ++){
           int[] subarray = new int [9];          
           int counter = 0;

           for (int k = 0; k < 3; k++) {
              for (int p = 0; p < 3; p++){
                 subarray[counter] = arr[k + sub_offset][p + offset];   
                 counter++;
              }
           }                  

           sub_offset += 3;           

           // every 3 iterations, reset row offset, add column offset by 3;
           if ((n + 1) % 3 == 0){
               sub_offset = 0;
               offset += 3;
           }          
          
          
            System.out.println (Arrays.toString(subarray));
            boolean col_valid = this.checkValid(subarray);    
             
            if (col_valid == false){
                return false;
            }                

        }


        return valid;
    }




    public static void main (String args[]) {

        Sudoku sudoku = new Sudoku ();

        int [] [] arr = {
            {1, 2, 3, 4, 5 ,6 ,7, 8, 9},
            {1, 2, 3, 4, 5 ,6 ,7, 8, 9},
            {1, 2, 3, 4, 5 ,6 ,7, 8, 9},
            {1, 2, 3, 4, 5 ,6 ,7, 8, 9},
            {1, 2, 3, 4, 5 ,6 ,7, 8, 9},
            {1, 2, 3, 4, 5 ,6 ,7, 8, 9},
            {1, 2, 3, 4, 5 ,6 ,7, 8, 9},
            {1, 2, 3, 4, 5 ,6 ,7, 8, 9},
            {1, 3, 3, 4, 5 ,6 ,7, 8, 9},
        };

        boolean result = sudoku.checkValid (arr); 
        System.out.println(result);
    }

}
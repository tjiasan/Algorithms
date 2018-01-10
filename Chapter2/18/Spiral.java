/*Problem: Given an nxn 2d array, compute the spiral ordering from
    topleft to a center

  Solution: divide and conquer, Since it's a square, go to outside box,
   go right N amount, 
   down N -1 amount, 
   left N -1 amount, 
   Up N-2 amount,

   decrease n by 2;
   shift start to right by 1, repeat until 0 or 1,
   where if 1, just set

  Variants :
     --- generate a 2d nxn matrix where spiral order is (0 ... n); go through algorithm backwards, creating nxn matrix; (still same complexity)
     --- m x n variant : (M is y and N is x)
    1) compute order 
       Go right N amount, 
       Go down M -1 amount,
       Go left N -1 amount, 
       go Up m -2 amount, shift 1 right, (decrease m and n by 2) repeat until counter reach m x n
    2) compute kth order of m x n in O (1) time                      

        Assuming N * N, the amount in each levels is defined by area of the inner square:
        for example, n = 5 square has these inner squares
        25 9 1  sq
        5  3 1   n

        to get the level then, you just sqrt(25 - k) :
        e.g. 1 to 16  ===  3 to 5; //assign this to p
             17 to 24  === 1 to 3;
             25        === 1;

             to get the level, if N is odd or %2 != 0, round up to the next odd number.
             else round up to the next even number;

             then L = (n-p)/2;

        in case of m x n , divide by (m/n) before sqrt. 

        e.g. 4x6 rectangle ;

        24    6
        4     2

        similar to
        16   4
        4     2

        4x6 = 24 = 4 *4 *1.5;

        to get level of pos 31
        32 - 31 = 1
        1/ 1.5 

        and then sqrt it, and round up;
*/

import java.util.Arrays;

public class Spiral {
    public int computeLowestLevel (int k , int m , int n){
            int level = 0; 
            int amount = 0;

            while (amount <= k) {
                amount += (m + n - 4 * level) * 2 - 4;
                level ++;
            }

            return level - 1;
        }


    public int[] computeOrder(int [] [] arr){

        int dimension = arr.length;
        int [] order = new int [dimension * dimension];
        int [] current = {0, 0}; // row, column (y,x)
        int counter = 0;

        while (dimension > 0){


           //go right;
           for (int i = 0; i < dimension; i++){
                order[counter] = arr[current[0]][current[1] + i];
                counter ++;
           }
           
           current[1] += dimension - 1;
         
           //go down
           for (int i = 1; i < dimension; i++){  
                order[counter] = arr[current[0] + i][current[1]];      
                counter ++;
           }
        
           current[0] += dimension - 1;

          //go left
           for (int i = 1; i < dimension; i++){
                order[counter] = arr[current[0]][current[1] - i];
                counter ++;
           }
           current[1] -= dimension - 1;

          //go up
           for (int i = 1; i < dimension - 1; i++){
                order[counter] = arr[current[0] - i][current[1]];
                counter ++;
           }
           current[0] -= dimension -2;
           
            current[1] += 1;

            dimension -= 2;  

            if (dimension == 1){
                order[counter] = arr[current[0]][current[1]];
                break;
            }
           
        }

        return order;


    }


    public static void main (String args[]){
        Spiral spiral = new Spiral ();

        int [] [] arr = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}

        };

        int [] [] arr1 = {
            {1, 2},
            {3, 4}
        };

        //int [] order = spiral.computeOrder(arr); 

        int level = spiral.computeLowestLevel ( 15, 5 ,5 );
        System.out.println(level);
        //System.out.println(Arrays.toString(order));
    }
}
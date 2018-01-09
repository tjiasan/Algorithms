/*Problem: Given an nxn 2d array, compute the spiral ordering from
    topleft to a center

  Solution: divide and conquer, Since it's a square, go to outside box,
   go right N amount, 
   down N -1 amount, 
   left N -1 amount, 
   Up N-2 amount,

   shift start to right by 1, repeat until 0 or 1,
   where if 1, just set
                        

*/

import java.util.Arrays;

public class Spiral {



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

        int [] order = spiral.computeOrder(arr); 

        System.out.println(Arrays.toString(order));
    }
}
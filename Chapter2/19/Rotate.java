/*
problem : rotate an array (n*n) by 90 degrees cw
solution : work from outside in;
           1) swap edges
           2) swap corners

           OR
           swap N-1 (edge +corner) , one at a time; (four 4 loops, same thing)

*/

import java.util.Arrays;

public class Rotate {

   public int [] [] swap (int[] [] arr, int [] i, int [] j){
        int temp = arr[i[0]] [i[1]];
        arr[i[0]] [i[1]] = arr[j[0]] [j[1]];
        arr[j[0]] [j[1]] = temp;
        return arr;
    }

    public int [] [] rotateArray (int [] [] arr){
        
        int [] cursor = {0, 0};

        int dimension = arr.length;

        while (dimension > 1){

            //swap top edge
            for (int i = 1; i < dimension - 1; i ++){

                int [] source = { cursor[0] + i, cursor[1] };
                int [] dest = { cursor[0], cursor[1] + dimension - i  - 1};

                arr = this.swap(arr, source, dest);
            }
            
            //swap left edge

            for (int i = 1; i < dimension - 1; i++){
                int [] source = { cursor[0] + dimension - 1 , cursor [1] + i } ;
                int [] destination = { cursor[0] + i, cursor [1]  } ;

                arr = this.swap(arr, source, destination);
            }         
        

            //swap bottom edge
            for (int i = 1; i < dimension - 1; i++){
                int [] source = {cursor[0] + i, cursor[1]  + dimension -1 };
                int [] destination = {cursor[0] + dimension -1, cursor[1] + dimension - 1 -i};

                arr = this.swap(arr, source, destination);
            }
          
            
            //swap corners
           
           
            int [] s = new int[2];
            int [] d = new int [2];

            s[0] = cursor[0];
            s[1] = cursor[1];

            d[0] = cursor[0];
            d[1] = cursor[1] + dimension -1;
         
            arr = this.swap(arr, s, d);

            d[0] = cursor[0]+ dimension -1;
            d[1] = cursor[1];
            arr = this.swap(arr, s, d);
      
            s[0] =  cursor[1] + dimension -1;
            s[1] =  cursor[1] + dimension -1;
            arr = this.swap(arr, s, d);                
       
            dimension -= 2;

            cursor [0] += 1;
            cursor [1] +=1;
        }    


        return arr;
    }

    public static void main (String args[]){

        Rotate rotate = new Rotate();

        int [] [] arr = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16},
        };

       int [] [] result = rotate.rotateArray(arr);

       for (int q = 0; q < 4; q++){
             System.out.println(Arrays.toString(result[q]));
       }
      
    }

}
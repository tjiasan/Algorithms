/*
    Problem: given an array of black and white squares

            white = 0;
            black = 1;

            if white squares are encircled, they should allb e changed to black

     Solution: 
            Going through the outside border, 
            Go through the squares, If white, 
            do a DFS, and change to a tertiary color
            
            Then iterate through the whole array
            and change tertiary to white,
            and white to black

            Time complexity O (MN),  to be more precise: 2 MN  + 2M + 2N,  Space is O(1) since we're reusing the same array

*/


import java.util.Arrays;
public class Enclosed {

    public void close(int[][] input){
        for (int i = 1; i < input.length; i++){
            if (input[i][0] == 0){
                input[i][0] = 2;
                this.recurse(input, i, 0, 0);
            }
     
            if (input[i][input.length - 1] == 0){
                input[i][input.length - 1] = 2;
                this.recurse(input, i, input.length - 1, 0);
            }
        }

        for (int i = 0; i < input[0].length; i++){
            if (input[0][i] == 0){
                input[0][i] = 2;
                this.recurse(input, 0, i, 0);                
            }
            if (input[input[0].length - 1][i] == 0){
                input[input[0].length - 1][i] = 2;                
                this.recurse(input, input.length - 1, i, 0);
            }
        }

        for (int i = 0; i < input.length; i ++){
            for (int k =0; k < input[0].length; k ++){
                if (input[i][k] == 2){
                    input[i][k] = 0;
                } else if (input[i][k] == 0){
                    input[i][k] = 1;
                }
            }
        }
    }

    void recurse (int[][] arr, int x, int y, int color){

        //up
        if (x - 1 > -1){
            if (arr[x - 1][y] == color){
                arr[x - 1][y] = 2;
                this.recurse(arr, x - 1, y, color);
            }
        }

        if (x + 1 < arr.length){
            if (arr[x + 1][y] == color){
                arr[x + 1][y]  = 2;
                this.recurse(arr, x + 1, y, color);
            }
        }

        if (y - 1 > -1){
            if (arr[x][y - 1] == color){
                arr[x][y - 1] = 2;
                this.recurse(arr, x , y - 1, color);
            }
        }

        
        if (y + 1 < arr.length){
            if (arr[x][y + 1] == color){
                arr[x][y + 1] = 2;
                this.recurse(arr, x , y + 1, color);
            }
        }

    }


    public static void main (String args[]){

        Enclosed enclosed = new Enclosed();



        int[][] input = {
            {1, 1, 1, 1},
            {0, 1, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
            
        };

        enclosed.close(input);

        for (int i = 0; i < input.length; i++){
            System.out.println(Arrays.toString(input[i]));
        }
    }
}
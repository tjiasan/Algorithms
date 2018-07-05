/*
    Problem: 
        given an array of booleans and a starting co-ordinate, 

        flip all adjacent squares (up, down, left, right) that has the same
        color/value as the starting co-ordinate, including the squares adjacent
        to the recently flipped squares. 

     Solution:
     
        Depth first search;

*/


import java.util.Arrays;
public class BooleanMatrix{

    void flip (int[][] arr, int x, int y){
        int color = arr[x][y];

        arr[x][y] ^= 1;

        this.recurse (arr, x, y, color);

    }

    void recurse (int[][] arr, int x, int y, int color){

        //up
        if (x - 1 > -1){
            if (arr[x - 1][y] == color){
                arr[x - 1][y] ^= 1;
                this.recurse(arr, x - 1, y, color);
            }
        }

        if (x + 1 < arr.length){
            if (arr[x + 1][y] == color){
                arr[x + 1][y] ^= 1;
                this.recurse(arr, x + 1, y, color);
            }
        }

        if (y - 1 > -1){
            if (arr[x][y - 1] == color){
                arr[x][y - 1] ^= 1;
                this.recurse(arr, x , y - 1, color);
            }
        }

        
        if (y + 1 < arr.length){
            if (arr[x][y + 1] == color){
                arr[x][y + 1] ^= 1;
                this.recurse(arr, x , y + 1, color);
            }
        }

    }


    public static void main (String args[]){
        BooleanMatrix mat = new BooleanMatrix();

        int[][] arr = {
           { 1, 0, 1, 0, 0, 0, 1, 1, 1, 1 },
           { 0, 0, 1, 0, 0, 1, 0, 0, 1, 1 },
           { 1, 1, 1, 0, 0, 1, 1, 0, 1, 1 },
           { 0, 1, 0, 1, 1, 1, 1, 0, 1, 0 },
           { 1, 0, 1, 0, 0, 0, 0, 1, 0, 0 },
           { 1, 0, 1, 0, 0, 1, 0, 1, 1, 1 },
           { 0, 0, 0, 0, 1, 0, 1, 0, 0, 1 },
           { 1, 0, 0, 0, 1, 0, 1, 0, 0, 0 },
           { 1, 0, 1, 0, 0, 0, 0, 1, 1, 1 },
           { 0, 0, 1, 1, 0, 0, 0, 1, 1, 0 }

        };

        mat.flip(arr, 5, 4);


        for (int i = 0; i < arr.length; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
    }


}
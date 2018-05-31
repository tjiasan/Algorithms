/*
    Problem: Compute the next Permutation in dictionary order
            e.g. 2, 0, 1 < 2, 1, 0
            e.g  1, 0, 3, 2  --> 1, 2, 0, 3


    Solution:  int [] arr = {6, 2 , 1, 5, 4, 3, 0}; 
              1) Find the pivot point (n < n+1), going from right to left where right number is larger than the left
                 pivot is at 1; 
              2) swap the pivot point with the smallest encountered, but larger than arr[n];
                swap with 3
                {6, 2, 3, 5, 4, 1, 0}
              3) The prefix is the same, since the suffix is in decreasing order, the next smallest would be the suffix in increasing order (i.e. reverse the swapped suffix);
               {6, 2, 3, 0, 1 ,4, 5}

    variant : (find decreasing order):
             1) from left to right, find pivot point, where n + 1 decreases

              1, 2, 0, 3 
              pivot is 2
              swap with next lowest
              1, 0, 2, 3;
              make sure suffix (n+1 ...n) is highest
              1, 0, 3, 2; 
            
*/

import java.util.Arrays;

public class Next {
    public int [] swap (int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }

    public int[] Compute (int[] arr) {
        int [] result = new int [arr.length];
        boolean fin = true;
        int i;
        int fit = 0;
        
        int min_index = arr.length - 1;
        int min = arr[min_index];

        for (i = arr.length - 2 ; i >= 0; i--) {   
     
            if (arr[i] < arr [i + 1]) {    
                fin = false; 
                break;
             }    

            if (arr[i] < min){
                min = arr[i];
                min_index = i;
            }        
        }
        
        //find the min where it's larger than swapped
        while (min < arr[i]) {
            min_index --;
            min = arr[min_index];
        }
      
        if (fin == false){
            //swap
            arr = this.swap(arr, i, min_index);
            int n;
            //copy forward
            for (n = 0; n <= i ; n ++){           
                result[n] = arr[n];                
            }
            //copy backwards        
            for (int k = arr.length - 1; k > i; k--){   
                result[n] = arr[k];    
                n++;                     
            }
            
        }

        return result;
    }

    public static void main (String args[]) {

        Next next = new Next();
        // int [] arr = {0, 1, 2, 3}; //returns 0132
        //int [] arr = {1, 0, 3, 2};  //returns 1203
        // int [] arr = {0, 1, 3, 2};  //returns 0213
        // int [] arr = {3, 2, 1, 0};  //returns 0000
         //int [] arr = {0, 3, 2, 1};  //returns 1023
        int [] arr = {6, 2 , 1, 5, 4, 3, 0}; // returns 6230145
        int [] result =  next.Compute (arr);
        System.out.println(Arrays.toString(result));

    }
}


        /*
        int [] result = new int [arr.length];
        
        boolean fin = true;
        int i;
        for (i = arr.length - 2 ; i >= 0; i--) {  
            int current = arr[i];
    
            if (arr[i] < arr [i + 1]) {
                arr[i] = arr[i + 1];
                arr[i + 1] = current;
                fin = false; 
                break;
             } else {
                arr[i] = arr[i + 1];
                arr[i + 1] = current;
             }        
        }
      System.out.println(Arrays.toString(arr));
        if (fin == true) {
            return result;
        } else {
            for (int n = i + 1; n < arr.length - 1; n++) {                
                if (arr[n] > arr[n + 1]){
                    arr = this.swap(arr, n, n+1);
                }
            }
        }*/
/*
Problem: do all permutation of an array

Solution: 
    perform nested calls to do all possible swaps combinations

    permute (arr[], iteration, finish){
        for (int i = iteration; i <finish; i++){
            this.swap(iter, i);
            this.permute(arr, iteration + 1, finish);
            this.swap(iter,i);
        }
    }

    
    e.g. 3 in array
    swap (0, 0)  --> swap (1, 1) --> first result
                         --> subbranch swap (1, 2)

    swap (0, 1)  --> swap (1, 1)
                    -- subbranch swap(1,2);
                   
       swap (0, 2)  --> swap (1,1)
                        --> subbranch swap(1, 2)            
*/


import java.util.Arrays;
public class Permutation{

    public void swap (int [] arr, int i, int j){
        int tmp = arr[i];

        arr[i] = arr[j];

        arr[j] = tmp;
    }


    public void permute (int[] arr, int i, int n){
        int j;

        if (i == n){
            System.out.println(Arrays.toString(arr));
        } else {
            for (j = i; j < n; j++){
                this.swap(arr, i , j);
                this.permute(arr, i + 1, n);
                this.swap(arr, i, j); //swap back because we use the same reference to array
            }
        }
        
    }

    public static void main (String args[]){
        Permutation permutation = new Permutation();

        int[] arr = {3, 5, 7, 9};
        permutation.permute(arr, 0, 4);

    }
}
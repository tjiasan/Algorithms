/*
    Problem: get all the subsets of an array from 0 to n length subsets
        e.g. {1, 2, 3} is { {}, {1}, {2}, {3}...{1,2,3}}

        Solution: 
            Recursive: 
                to get all subsets:
                    combine/unionize first element of the array with recursive result of 
                        of subarray 1....n ;
                     
                        base case length 1 = { {itself} , {null} };

                    to combine :
                        add first element to every result + original;
                        
                        
*/


import java.util.Arrays; 
public class Powerset{

    //copies i to j inclusive
    public int[] deepCopyRange(int[] arr, int i, int j){
        int[] result = new int[j - i + 1];
        
        for (int k = i; k <= j; k++){
            result[k - i] = arr[k];
        }
        return result;
    }

    public int[] [] subset(int[] arr){
        //base case
        if (arr.length == 1){
            int [][] result = {
                arr,
                {}
            };
            return result;
        }

        int[] partial = this.deepCopyRange(arr, 1, arr.length -1);


        return this.union (arr[0], this.subset (partial));

    }

    public int [][] union (int val, int[][] arr2){
    
        int [][] result = new int[arr2.length * 2][];

        for (int i = 0; i < arr2.length; i ++){
            int[] combined = new int[arr2[i].length + 1];
            combined[0] = val;
            for (int n = 1; n < combined.length; n ++){
                combined[n] = arr2[i][n-1];
            }    
            
            result [i] = combined;
        }

        for (int i = arr2.length; i < arr2.length *2; i ++){
            result[i] = arr2[i - arr2.length];
        }

        return result;
    }

    public void printresult (int[] [] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    public static void main (String args[]){

        Powerset powerset = new Powerset();

        int [] input = { 1, 2, 3, 4};
        int [][] result = powerset.subset(input);
        powerset.printresult(result);

       /* 
       int [] [] testinput = { {1}, {1, 2} };
       int [][] testresult = powerset.union( 4, testinput);
       System.out.println(Arrays.toString(testresult[1]));// returns 4, 1, 2
       */

        /*      
       int [] testinput = { 1, 2, 3 };
       int [] testresult = powerset.deepCopyRange(testinput, 1, 2);
       System.out.println(Arrays.toString(testresult));// returns {2,3}
       */



    }
}
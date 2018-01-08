/*
    Problem: Apply permutation of a certain order without using O(n) space;

    Solution: 1) If same array types, override the order array; (if you don't care about preserving it in memory)
              2) Find previous location of previously swapped arrays time complexity (O (n^1.5)) (preserves order in memory);
              3) Destructive in memory both arr and order; Complexity (O(n));
*/

import java.util.Arrays;

public class ApplyPermutation {
    public int [] swap (int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }

    public int [] apply_find_previous (int[] arr, int[] order) {

        for(int i = 0; i < arr.length; i++){
            if (order[i] < i) {
                int location = order[order[i]];
                while (location < i){
                    location = order[location];
                }
                arr = this.swap(arr, i, location);

            } else {
                arr = this.swap(arr, i, order[i]);
            }

        }

        return arr;

    }

    public int [] apply_destructive (int[] arr, int[] order) {

        for(int i = 0; i < arr.length; i++){
            if (order[i] < i) {
                int location = order[order[i]];
                arr = this.swap(arr, i , location);               
                order[i] = location;
                             
            } else {
                arr = this.swap(arr, i , order[i]);
            }
        }

        return arr;

    }


    public int[] apply_overwrite (int[] arr, int[] order){

        for (int i = 0; i < order.length; i++) {

            int pos = order[i];
            order[i] = arr[pos];
        }

        return order;

    }


    public static void main (String args[]){

        ApplyPermutation ApplyPerm = new ApplyPermutation();

        int [] arr =   {11, 6, 7, 8, 9, 10}; 
        int [] order = {5,  3, 0, 1, 2,  4};
        
        //it is possible if it's smaller that ti's swapped more than once, but not possible if larger and same;
        //zero has been swapped twice
         System.out.println(Arrays.toString(order));
 
        //int [] result_find = ApplyPerm.apply_find_previous(arr, order);
        //System.out.println(Arrays.toString(result_find));   
       
        // int [] result = ApplyPerm.apply_overwrite(arr, order);
        // System.out.println(Arrays.toString(result));

        int [] destr = ApplyPerm.apply_destructive(arr, order);
        System.out.println(Arrays.toString(destr));

    }

}
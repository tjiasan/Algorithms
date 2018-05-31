/*
    Problem: Delete Duplicates from a sorted array in O(N) and O(1) space
    Solution: keep track of unique and duplicate numbers, if it encounters  a unique integer, override the index of unique number.
                Once i encounter the amount of duplicates, override evrtyrthing to zero;

*/

import java.util.Arrays;
public class Duplicates {

    public int [] DeleteDup (int [] arr) {

        int duplicates = 0;
        int uniques = 1; //keep track of position
        int current = arr[0];

        for (int i = 1; i < arr.length; i++){

            if (arr[i] > current) {
                current = arr[i];            

                if (i != uniques) {
                    arr[uniques] = arr [i];
                }// else leave alone

                uniques++;
            } else {
                duplicates ++;
            }

            if (i >= (arr.length - duplicates - 1)){
                arr[i] = 0;
            }

        }

        return arr;
    }

    public static void main (String args[]) {
        Duplicates duplicate = new Duplicates();

        int [] arr = { 1, 2, 2, 3, 3, 3, 4, 4, 5, 6};
        int [] result = duplicate.DeleteDup(arr);

         System.out.println(Arrays.toString(result));
    }

}
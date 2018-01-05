/*
Problem: Given elements in an array and a pivot, rearrange
the arrays such that it's in element less than the pivot, 
followed by equal, and followed by more

Solution: 1) brute force: create 3 sub arrays, put in appropriate order
          2) high time complexity: re-run through arrays multiple times, arrang it
          3) swaps: use double pointers, from both ends of the array,
                     right pointer:  keep iterating until encounter less
                     left pointer : keep iterating until encounter more
                     both pointers : first swap all equal to first/last element depending on pointer(and index)
                     after done, swap back the equals;

                     Complexity O(2N); Space O(1);

*/
import java.util.Arrays;
public class Dutch {

    public int[] Swap (int[] arr, int i, int j) {

        int temp = arr[i];

        arr[i] = arr[j];

        arr[j] = temp;

        return arr;
    }

    public int[] GetPivot (int[] arr, int pivot) {
        
        int a = 0;
        int b = arr.length -1;
        int length = arr.length -1;
        int equal_a = 0;
        int equal_b = 0;
        int index;

        boolean a_swap = false;
        boolean b_swap = false;

        while (a < b) {

            if (arr[a] == pivot) {
                arr = this.Swap(arr, a, equal_a);
                a++;
                equal_a ++;
                a_swap = false;
            }

            if (arr[b] == pivot) {
                arr = this.Swap(arr, b, length - equal_b);
                b--;
                equal_b ++;
                b_swap = false;
            }
        
            //left pointer 
            if (arr[a] > pivot){
                a_swap = true;
            } else {
                a++;
            }

            if (arr[b] < pivot) {
                b_swap = true;
            } else {
                b--;
            }

            if (a_swap && b_swap){
                arr = this.Swap(arr, a, b);
                a++;
                b--;
                a_swap = false;
                b_swap = false;
            }          
        }

        //pointers are always a > b, but if odd number of arrays, mid element ignored
        if ((b - a) == 2){
            if (arr[b + 1] < pivot){
                b ++;
            } else {
                a--;
            }
        }

        //swap back same elements to pivots; because insertions/deletions too much space cost
        if (equal_a > 0){         
            for (int i = 0; i < equal_a; i++){
                arr = this.Swap(arr, i, b - i);
            }
        }

        if (equal_b > 0) {           
            for (int i = 0; i < equal_b; i++){               
                arr = this.Swap(arr, length - i, a + i);
            }
        }      

        System.out.println(Arrays.toString(arr));
        return arr;
    }

    public static void main (String args[]) {
        int [] arr = { 1, 2, 3, 1, 2, 1, 3, 3, 1, 2, 4, 2};
        Dutch dutch = new Dutch();

        int [] result = dutch.GetPivot(arr, 2);

    }
}
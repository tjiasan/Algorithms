/*
    Problem: From An array of distinct users, create a subset k, where each user is equally likely to be in the subset

    Solution: Pick a random number from 0 to n, swap that n with random number, copy n to result array
              Pick a random number from 0 to n-1, swap, 
              repeat until o to n - k

*/
import java.util.Arrays;
import java.util.Random;

public class Offline { 
    public int [] swap (int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }

    public int[] subset (int arr[], int k){
        int [] result = new int[k];
        int max = arr.length;
        for (int i = 0; i < k; i++){
            Random rand = new Random(); 
            int value = rand.nextInt(max); 

            arr = this.swap(arr, value, max - 1);

            result[i] = arr[max - 1];

            max --;
        }



        return result;

    }

    public static void main (String args[]){

        int arr[] = {10, 11, 12, 13, 14, 15};
        Offline offline = new Offline ();

        int[] result = offline.subset(arr, 3);

        System.out.println(Arrays.toString(result));
    }

}
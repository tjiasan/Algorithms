/*
    Problem: generate a random permutation order;

    Solution: Use same solution as 12; Pick a number
*/
import java.util.Arrays;
import java.util.Random;



public class RandomPermutation{

   public int [] swap (int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }

    public int[] random (int k){
        int [] result = new int[k];
        int max = k;
        //initialize
        for (int n = 0; n < result.length; n ++){
            result[n] = n;
        }

        for (int i = k; i > 0; i--){
            Random rand = new Random(); 
            int value = rand.nextInt(i); 

            result = this.swap(result, value, i - 1);
        }
        System.out.println(Arrays.toString(result));
        return result;

    }

    public static void main (String args[]){
        RandomPermutation perm = new RandomPermutation();

        int k = 5;
        perm.random(k);  
       
 
    }

}
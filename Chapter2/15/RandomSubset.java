/*
    an Array n size, with k subset has
    a n!/((n-k)!k!) subsets and permutations;

    Problem: return a subset k of n with uniform probability
    Solution: use an inferior algorithm with hashtable for fun.
                problem 12 has O(k) solution and space already;

                keep track of swaps in hashmap;
                if get the same number, to keep it uniform: 
                hashmap [number] set to i;
                and hashmap [i] = hashmap[number];

                OR

                generate a random permutation order
                grab the first k numbers;

*/
import java.util.HashMap;
import java.util.Random;
import java.util.Arrays;

public class RandomSubset{
    public int [] swap (int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }
    public int[] generate (int k, int[] arr) {

        Random rand = new Random();
        HashMap <Integer, Integer> seen = new HashMap<Integer, Integer> ();

        for (int i = 0; i < k; i ++) {
            int value = rand.nextInt(arr.length);
            System.out.println(value);

            if (seen.get(value) != null) {
                int to_swap = seen.get(value);
                arr = this.swap(arr, to_swap, i);
                seen.put(value, i);                
            } else {
               arr = this.swap(arr, value, i);
               seen.put(value, i);
            }
        }

        int[] result = new int[k];

        for (int n = 0; n < k; n++){
            result [n] = arr[n];
        }
   
        return result;
    }

    public static void main (String args[]){
        RandomSubset sub = new RandomSubset();
        int k = 5;
        int [] arr = {0, 1, 2, 3, 4, 5, 6, 7};
        int[] result = sub.generate(k, arr);

        System.out.println(Arrays.toString(result));
    }
}
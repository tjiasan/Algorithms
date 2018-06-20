/*
    Problem: Given an array 


    Solution: Use hashtable to compare previous starting lenths, 
              store highest, 

              write, next


    Variants:  1) alternating less than, bigger than:
                double the hashmap to include longest upstream for two prev signs, gt lt,
                same algo

                2) convex number; A[i] < (A[i - 1] + A[i + 1])/2, store complete subarrays,
                if equal, store both in linked list, if satisfy condition, store

                3) nlogn time?
             


*/


import java.util.HashMap;

public class Increasing {


    public void longest(int[] input){

        HashMap<Integer, Integer> upstream = new HashMap<Integer, Integer> ();
        for (int i = 0; i < input.length; i ++){
           upstream.put(i, 0);
        }

        int max = 0;
        for (int i = 0; i < input.length; i ++){
            int up = upstream.get(i);
            if (up + 1 > max){
                max = up + 1;
            }
            for (int k = i + 1; k < input.length; k++){
                if (input[i] < input[k]){
                    if (upstream.get(k) < up + 1){
                        upstream.put(k, up + 1);
                    }
                }
            }
        }
        System.out.println(max);

    }

    public static void main (String args[]){

        int[] input = {
            0, 8, 4, 12, 2, 10, 6, 14, 1, 9
        };

        Increasing inc = new Increasing();


        inc.longest (input);

    }
}
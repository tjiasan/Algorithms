/*
    Problem: solve gray code, 
            find permutations of 2 ^ n -1 elements, of 0, 1 ,2 ... n-1;
            such that they only differ by 1 bit from array position a to a + 1;

    Solution: recursive, mod 1, test if already present, recurse if present

     Actual Problem:
            Return 1 single permutation!
            
      actual solution:
      
      prepend 0 to  previous n- 1 solution + 1 to previous n-1 solution reversed;

      e.g n =1  is

      1 , 0   n = 1

      01, 00, 10, 11 n= 2;

      001, 000, 010, 011 + 111, 110, 100, 101   n =3
*/

import java.util.HashMap;
import java.util.Arrays;

public class Code {
    
    public void allPermutations(int power){

        int max = (int)Math.pow(2, power) -1;

        for (int i = 0; i <= max; i++){
            HashMap<Integer, Integer> location = new HashMap<Integer, Integer>();
            location.put (i, 0);
            this.recurse(power, i, 0, location);
        }

    }

    public void recurse(int power, int last, int last_location,  HashMap<Integer, Integer> location ){

        int max = (int)Math.pow(2, power) - 1;

        if (last_location == max){
            int[] result = new int[max + 1];

            for (Integer key: location.keySet()){
                int loc = (int) location.get(key);
                result[loc] = (int) key; 
            }
            System.out.println(Arrays.toString(result));
            return;
        }

        for (int i = 0; i < power; i ++){
            int mask = 1 << i; 
            int next = last ^ mask;

            if (location.get(next) == null && next <= max){
                HashMap<Integer, Integer> next_location =  new HashMap<Integer, Integer>();
                next_location.putAll(location);

                next_location.put(next, last_location + 1);           

                this.recurse(power, next, last_location + 1, next_location);
            }
        }
    }

    public static void main (String args[]){
         Code code = new Code();

        code.allPermutations(3);

    }
}
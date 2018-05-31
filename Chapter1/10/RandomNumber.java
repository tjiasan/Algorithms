/* 
    Problem: Generate uniform Random Numbers between lower bound and upper bound
    Solution: if you have a 8 sided die, and exclude 2 results, you have 6 equaly probably outcomes;
              Thus, you can generate any amount of outcomes if the die size is greater than outcomes;
              1) create a binary with greater than outcomes
              2) if greater than amount of choices, re-do;
*/

import java.util.Random;

public  class RandomNumber {
   

    public int pick_random (int lower, int upper) {
        
        Random randomNum = new Random();
        int outcomes = upper - lower + 1;
        int iterations = 0;
        int result = 0;

        //figure out max iterations
        int outcome_copy = upper - lower + 1;
        while (outcome_copy > 0) {
            outcome_copy >>= 1;
            iterations ++;
        }   // figuring out log2 base     
        
        while (true) {
            for (int i = 0 ; i < iterations; i++){
               int flip_mask =  randomNum.nextInt(2) << i;
               result |= flip_mask;
            } // generate a random integer, flipping bits 0 or 1 randomly
            if (result < outcomes) {
                break;
            } //try again
            else {
                result = 0;
            }            
        }

        return lower + result;

    }


    public static void main (String args[]){
        RandomNumber rand = new RandomNumber();
        
        int lower = 1;
        int upper = 20;

        int result = rand.pick_random(lower, upper);        
        System.out.println(result);

    }

}
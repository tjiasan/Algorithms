/*
    Problem : Given an array of outcomes and array of probabilites, compute a non-uniform outcome, where you only can use 0 to 1 random generator;
    Solution: 1) compute uniform random number from 0 to max sum of probabilities; 
              2) if random number falls within certain range, assign to outcomes;
              Complexity: O (3N), space : O (1);  
              Optimization: The first for loop only need 1 iteration; (next iterations can be reused);
                            the second for loop is log(n) complexity; (can be replaced with library 0 to n generator)
                            the third for loop can be replaced with binary search, giving log(n);

                            repeated calls from the same probabilities can be optimised to log(n)*log(n) complexity;

     Variant: continious distribution:
            1) Generate Uniform random number from 0 to 1 continuously; 
            2) multiply rng by range desired = y. 
            3) if the function is y = e ^x;
            4) figure out x from y; (ln y );

            //if construct a histogram of all x values, will look like the function;

*/


import java.util.Random;
import java.util.Arrays;

public class NonUniform {


    public int generate (int[] outcomes, int [] probabilities){
       
        int sum = 0;

        for (int i = 0; i < probabilities.length; i++){
            sum += probabilities [i];
            probabilities [i] = sum;
        }

        Random rand = new Random();

        int result = 0;
        int iterations = 0;
        int sumcopy = sum;

        //figure out iterations
        while (sumcopy > 0){
            sumcopy >>= 1;
            iterations ++;
        }

        //generate a random number;
        while (true){
          
            for (int k = 0; k < iterations; k++){
                int mask = rand.nextInt(2) << k;
                result ^= mask;
            }

            if (result > sum){
                result = 0;
            } else {
                break;
            }      
        }

        for (int i = outcomes.length -1; i >= 0; i--){
            if (result <= probabilities[i]){
                location = i;
                break;
            }
        }

         
        return outcomes[location] ;
    }



    public static void main (String args[]){
        NonUniform uni = new NonUniform();

        int [] outcomes = { 3, 5, 7, 11 };
        int [] probabilities = { 9, 6, 2, 1 };

        int result = uni.generate(outcomes, probabilities);

        System.out.println(result);
    }
}
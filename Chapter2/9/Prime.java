/*
    Problem: Enumerate all prime number to limit that is given;
    Solution: 1) Trial division approach: 
                 Try to divide each number up to sqrt(n), where n is the number;
              2) Sieve method :
                 calculate multiples of each number starting from 2;
                 optimizations: 1)  start from n ^ 2 (since everything before itself sieved out already by previous calcualtions 0 to n - 1)
                                2)  increment by 2;

*/


import java.util.Arrays;
public class Prime {

    public boolean [] makePrime (int limit) {
        boolean [] result = new boolean [limit + 1];

        Arrays.fill(result, true);
        result[0] = false;
        result[1] = false;

        //get rid of evens excluding 2
        for (int n = 4; n <= limit; n+=2){
            result[n] = false;
        }

        //everything else
        for (int i = 3; i < limit; i+= 2) {
            if (result[i] == true){
                int start = (i * i);
                for (int k = start; k <= limit; k += i){
                    result[k] = false;
                }
            }
        }
        return result;


    }

    public static void main (String args[]) {
        Prime prime = new Prime();

        boolean [] result = prime.makePrime(10);

        System.out.println(Arrays.toString(result));
    }
}
/* Problem: Solve the collatz conjecture:

    Given a starting number, e.g. 11

    if odd, multiply by 3, add one
    if even, divide by 2

    11, 34, 17, 52, 26 ... 1

    The conjecture states that it will eventually terminate to 1,
    Test from 0 ... n;
        preferrably up to 1 billion;

    Solution: a bunch of optimizations,

            but basically, store, previous results in 
            hash table, if seen, break early

            since evens are immediately halved, skip evens;

            Only store numbers higher than ones seen;


            use arbitrary precision integer to avoid overflow; (in java overflow == negative)

   */ 
import java.util.HashMap;

public class Collatz {


    public boolean is_valid (int n){

        boolean result = true;
        HashMap<Integer, Integer> seen = new HashMap<Integer, Integer>();

        for (int i = 1; i <= n; i +=2){

            int current = i;

            while (current != 1){
                if (current % 2 != 0){                  
                    current *=3;
                    current += 1;
                } else {
                    current /= 2;
                }

                if (current > i){
                    Integer past = seen.get(current);
                    if (past == null){
                        seen.put(current, i);
                    } else {
                        if (past == i){
                            System.out.println("infinite loop detected false");
                            System.out.println(i);
                            return false;
                        } else {
                            break;                      
                        }                   
                       
                    }
                }

                if (current < 0){
                    System.out.println("overflow at " + i);
                    break;
                }

            }

        }

        return result;
        
    }

    public void compute (int n){

        int current = n ;

        while (current != 1){
            if (current % 2 != 0){
                current *=3;
                current += 1;
            } else {
                current /= 2;
            }
            System.out.println(current);
        }

    }


    public static void main (String args[]){


        Collatz col = new Collatz();


        boolean is_valid = col.is_valid(200000);
       // overflow 113383   to integer larger than 2^32;
        // col.compute(113383);
    }


}

/*
    ( 5 3 ) or 5 choose 3, is really an extension of the pascal triangle
    (5 3)  = (4 3) + (4 + 2)

    Problem: calculate binomial coefficient without going into overflow
    since (5 3) direct calculation is 5! / (3! * 3!)


    naive approach make repeated calls to the same arguements by
    the 3rd recursion (not optimized)


    optimize: 
            this problem is essentially the pascal triangle problem
            you can start from n = 0

            and recurse forward from n = 1 
            with the previous result until you get to the
            level you want

    */


public class BinomialCoef {
    // naive approach
    public int compute (int i, int k){
        if (k > i){
            return 0;
        }

        if (i == k || k == 0){
            return 1;
        } 


        return this.compute(i - 1, k) + this.compute (i -1, k -1);

    }

    public int computeForwards(int i, int k){
        if (i == 0){
            return 1;
        }

        //shortcuts
        if (i == k || k == 0){
            return 1;
        } 

        int [] prev = new int[1];
        prev[0] = 1;

        return this.recurse_forwards(i, k , prev, 1);

    }

    public int recurse_forwards(int i , int k , int[] prev, int level){

        if (level == i){
            //save some code from shortcuts;
            return prev[k] + prev[k - 1];
        }

        int iterations = prev.length + 1;

        int[] next = new int[prev.length + 1];

        //prefill known ones;
        next[0] = 1;
        next[iterations - 1] = 1;
        
        for (int n = 1; n < iterations - 1; n ++){
            next[n] = prev[n] + prev[n - 1];
        }

        return this.recurse_forwards(i, k, next, level +1);

    }


    public static void main (String args[]){


        BinomialCoef bin = new BinomialCoef();

        System.out.println(bin.compute(5, 3));

        // can make prefunction to prime;

        System.out.println(bin.computeForwards(5, 3));
    }


}
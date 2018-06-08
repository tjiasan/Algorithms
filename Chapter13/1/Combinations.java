
/*
    Problem: given a score, and sub scores that can make up a score,
        present a solution of score combinations that makes up
        the total score;

        e.g. 
        12 fin score, and [2, 3, 7] subscores;

        total of 0 2s, 4, 3s, and 0 7s


        Solution: Recursive algorithm, start with zero everything

        recurse sideways and down;

        sideways keep the score constant, and get to final number, and modulo to figure out if there
        is an answer

        down, subtract total with current score, increase result by 1;



    Complexity analysis:

    s ^ (n-1)/ (A[1] * A[2] * A[3]...A[n-1]);
    time


    space O(N);
*/

import java.util.Arrays;
public class Combinations {


    public void enumerate(int total, int[] scores){


        int [] result = new int[scores.length];

        this.recurse(total, scores, result, 0);

    }

    public void recurse (int total, int[] scores, int[] result, int index){

        if (total == 0){
            System.out.println(Arrays.toString(result));
            return;
        } 
        if (total < 0){
            return;
        }

        if (index == result.length - 1){
            if (total % scores[index] == 0){
                result[index] = total/scores[index];
                System.out.println(Arrays.toString(result));
               
            } 
            return;
        }

        int [] next_index = result.clone();
        next_index[index + 1] += 1;
        this.recurse(total - scores[index + 1] , scores, next_index, index +1);

        result[index]++;
        this.recurse(total - scores[index], scores, result, index);
    }


    public static void main (String args[]){

        Combinations comb = new Combinations();

        int[] scores = {2, 3, 7};

        comb.enumerate(12, scores);


    }
}
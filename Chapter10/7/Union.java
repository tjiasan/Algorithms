/* Problem:

        Given a set of intervals,
        compute the union of all intervals;



    Solution:
        sort the intervals by the start time;
        
        
        iterate through interval;
            if current interval intersects previous,
                start <= prev_end;

            combine, send end to max of either end;

    Complexity O(n logn) time to sort;
    o(1) space;


*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;

class IntervalComparator implements Comparator<int[]> {

    @Override
    public int compare (int[] a, int[]b){
        if (a[0] < b[0]){
            return -1;
        } else if (a[0] == b[0]){
            return 0;
        } else {
            return 1;
        }
    }


}

public class Union {

    public int[][] combine (int[][] input){

        IntervalComparator comparator = new IntervalComparator();


        Arrays.sort(input, comparator);

        int[] current_interval = input[0];

        ArrayList<int[]> result = new ArrayList<int[]>();

        for (int i = 1; i < input.length; i ++){

            if (input[i][0] <= current_interval[1] && input[i][0] >= current_interval[0]){

                current_interval[1] = Math.max(input[i][1], current_interval[1]);

            } else {
                result.add(current_interval);
                current_interval = input[i];
            }   

        }


        result.add(current_interval);

        int[][] fin = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++){
            System.out.println(Arrays.toString(result.get(i)));
            fin[i] = result.get(i);
        }

        return fin;

    }



    public static void main(String args[]){

        Union union = new Union ();

        int [] [] input = {
            {1,1},
            {4,5},
            {3,4}

        };

        union.combine(input);


    }



}
/*
    Problem: consider an array of intervals denoting time when you're busy:

    { {-4, 1}, { 0, 2}, { 3, 6}, {7, 9}, {11, 12}, {14, 17}};

    insert new interval when you're busy {1, 8};

    output:
    { {-4, 1}, {0, 9}, {11, 12}, {14, 17}};


    Solution:
        find the leftmost interval from new interval in schedule (logn time)
        find the rightmost interval from new interval in schedule (logn time);

        construct new interval :

            interval = given new interval;

            if inside left, interval[0] = left[0]

            if inside right, interval[1] = right[1];

        copy left side + interval + right side into a new array;

        left_side, 
                if (inside), from 0 to left -1, 
                       else 0 to left,

                size calculation is left - 1 (if inside) + 1;
        right_side,
              
            if (inside),
                from right + 1 to end;
            else 
                from right to end;
                                    last_index
            size calculation is (total_size -1) - right - 1(if inside);
        Complexity O (N) time worst case O(1) space
        

*/
import java.util.Arrays;

public class InsertInterval{


    public int[] findLeft(int [][] schedule, int[] merge){
        int[] result = new int[2];

        int left = 0;
        int right = schedule.length;

        int mid = (left + right)/2;

        while(right - left > 1){

            if (schedule[mid][0] > merge[0]){
                right = mid;
            } else {
                left = mid;
            }
            mid = (left + right)/2;

        }

        int i;
        int inside = 0;
        for (i = left; i < left + 2; i++){
            if (merge[0] >= schedule[i][0] && merge[0]<= schedule[i][1]){
                inside = 1;
                break;
            }            
        }

        if (inside == 0){
            i = right;
        }

        result[0] = i;
        result[1] = inside;

        return result;
    }


    public int[] findRight(int [][] schedule, int[] merge, int leftStart){
        int[] result = new int[2];

        int left = leftStart;
        int right = schedule.length;

        int mid = (left + right)/2;

        while(right - left > 1){

            if (schedule[mid][1] > merge[1]){
                right = mid;
            } else {
                left = mid;
            }
            mid = (left + right)/2;

        }


        int i;
        int inside = 0;
        for (i = right; i > right - 2 ; i--){
            if (merge[1] >= schedule[i][0] && merge[1]<= schedule[i][1]){
                inside = 1;
                break;
            }            
        }

        if (inside == 0){
            i = right;
        }
     
        result[0] = i;
        result[1] = inside;

        return result;
    }



    public  int[][] combine( int [][] schedule, int[] merge){
        //construct merged interval
        int[] findLeft = this.findLeft(schedule, merge);

        int[] interval_left = merge;

        if (findLeft[1] == 1){
            interval_left = schedule[findLeft[0]];
        }

        int copy_left = findLeft[0] - findLeft[1]  + 1;
        int[] findRight = this.findRight(schedule, merge, copy_left);        

        if (findRight[1] == 1){
            interval_left[1] = schedule[findRight[0]][1];

        } else {
            interval_left[1] = merge[1];
        }

        
        //compute size of copying, add one if inside
        int offset = 1;

        if (findRight[1] == 1){
            offset = 0;
        }

        int copy_right = schedule.length - 1 - findRight[0]  + offset;
   
    
        int[][] result = new int[copy_left + copy_right + 1][2];

        for (int n = 0; n < copy_left; n++){
            System.out.println(Arrays.toString(schedule[n]));
            result[n] = schedule[n];
        }

        result[copy_left] = interval_left;
        System.out.println(Arrays.toString(interval_left));


        int startRight = findRight[0] + findRight[1];
        for (int n = 0; n < copy_right; n++){
            System.out.println(Arrays.toString(schedule[startRight + n]));            
            result[n] = schedule[startRight +  n];
        }



        return result;
    }



    public static void main (String args[]){


        int[] [] schedule = { {-4, 1}, { 0, 2}, { 3, 6}, {7, 9}, {11, 12}, {14, 17} };

        int [] merge = {1, 8};


        InsertInterval insertInterval = new InsertInterval();

        int[][] combined =  insertInterval.combine(schedule, merge);


    }






}
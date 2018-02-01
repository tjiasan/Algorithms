
/*
    Problem: Given an array with a cycle,
            detect the min;


    Solution: find the cycle shift;
    
            Divide and conquer:
                    1) trim spillovers, such that start != end
                    2) logic :
                         if (start <= end )// no cycle;

                         if right - left is greater than 1
                            for left to mid, recurse
                            for mid to end, recurse

                         else end is cycle;
                         
                      if happen to break at cycle left and right is -1;
                      return mid +1;
                      
                      else keep returning left or right if it's not -1;

                 
                      Complexity: average O (logn),
                      worse case n:
                            {8,8, 8, 8,8, 8, 8, 8 ,1 ,8}

        variant: find max in an array that has 1 hill;
        
            left = 0
            right = end;

            mid = halfway point
            if halfway + 1 is down , left halfway
            else right halfway
            when right - left = 1, right = max;
            Complexity O(logn)


*/

public class Cyclearr {

    public int findMin(int[] arr){
        int start = 0;
        int end = arr.length -1;

        int offset = 0;
        if (arr[0] == arr[end]){
            for (int i = 1; i < arr.length; i++){
                if (arr[i] == arr[0]){
                    offset ++;
                } else {
                    break;
                }
            }
        }

        int cycle = this.detect(input, offset, end);

        if (cycle == -1){
            return arr[0];
        }

        return cycle;

    }


    public int detect (int [] input, int start, int end){
        // no cycle
        if (input[start] <= input [end]){           
            return -1;
        }

        // cycle detected
        int left = -1;
        int right = -1;
        int mid  = -1;   

        // length is 2 or more; 
        // [2, 3, -1] returns -1 and -1 , so we know it's at pos 3
        // [3 , -1] returns pos 2
        if (end - start > 1){
            mid = (start + end)/2;
            left = this.detect(input, start, mid);    
            right = this.detect(input, mid + 1, end);    
        } else {
            return end;
        }
        
        //the break is right at cycle
        if (left - right == 0){
            return mid + 1;
        }

        //break not at cycle
        if (left != -1){
            return left;
        }
        //break not at cycle
        if (right != -1){
            return right;
        }

        return -1;

    }


    public static void main (String args[]){

        Cyclearr cycle = new Cyclearr();

        int [] input = { 5, 6, 7, 8, -1, 1, 2, 3, 4 };

        int position = cycle.findMin(input);


        int index = cycle.detect (input, 0, input.length - 1);

        System.out.println(index);
        //if -1, we know min is at zero;
    }
}
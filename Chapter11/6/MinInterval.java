/*
    Problem: given a set of sorted arrays, 
            e.g. {5, 10, 15};
                 {3, 6, 9, 12, 15};
                 {8, 16, 24};

                 find a set of integers (ond from each array), such that if an interval were to be made, (max - min) or range is the smallest

    Solution:
            Book solution:
                Construct 1 BSTs, 
                    fill candidate BST with arr[0] elements ;
                    find the min and max in candiate bst, log k
                     calculate range,  logk
                    remove min from candidate, logk
                     add min from the BST that element originates from;

                repeat until smallest interval is found;  
                N log L according to the book, 
                and also O(H ) space for bst ;



                
            My Solution:
                Iterate throug the first array,
                    peform a binary search to find the closest number in the array
                    where it's closest to range, O(log L) time
                    
                    Optimization: decrase the range every time you search;
                                 by the position fo the last result;
                
                    best distance is where max - min is lowest;
                    
                Complexity: 
                        O(H * L log L) Log L decreases every iteration;
                        
                        
                        if H = L
                        then it's O (N Log L) and O (1) space

               
   


*/
import java.util.Arrays;

public class MinInterval {

    public int[] find (int[] arrf, int min, int max, int last){

        int left = last;
        int right = arrf.length - 1;
        int[] result = new int[4];

        int mid = (right + left)/2;


        while (right - left > 1){

            if (arrf[mid] <= min ){
                left = mid;
            } else {
                right = mid;
            }
            mid = (right + left)/2;

        }

        int left_dist = 0;
        if (arrf[left] < min){
            left_dist = min - arrf[left];
        } else  if (arrf[left] <= max){
            left_dist = 0;
        } else {
            left_dist = arrf[left] - max;
        }

        int right_dist = 0;
        if (arrf[right] <= max){
            right_dist = 0;
        } else {
            right_dist = arrf[right] - max;
        }
           

        if (right_dist > left_dist){
            result[0] = arrf[left];     
            result[3] = left;       
        } else {
            result[0] = arrf[right];     
            result[3] = right; 
        }

        result[1] = min;
        result[2] = max;
        if (result[0] < min){
            result[1] = result[0];
      
        } else if (result[0] > max){
            result[2] = result[0];
        }


        return result;

    }

    public int[] getMin(int[][] arr){

        int[] candidate = new int[arr.length];

        int[] best = new int[arr.length];
        int best_interval = 2147483647;
        int last [] = new int[arr.length];

        for (int i = 0; i < arr[0].length; i++){
            candidate[0] = arr[0][i];

            int min = candidate[0];
            int max = candidate[0];


            for (int k = 1; k < arr.length; k++){
                int [] closest = this.find(arr[k], min, max, last[k]);

                candidate[k] = closest[0];
                min = closest[1];
                max = closest[2];
                last[k] = closest[3];

            }
                      
            if (max- min < best_interval){
                best_interval = max - min;
                best = candidate;
            }





        }

        System.out.println(Arrays.toString(best));

        return best;

    }

    public static void main (String args[]){

        MinInterval minInterval = new MinInterval();

        int [] [] arr = {
            {5, 10, 15},
            {3, 6, 9, 12, 15},
            {8, 16, 24}
        };


        int[] min = minInterval.getMin(arr);
    }
}





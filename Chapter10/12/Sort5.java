/*
    Problem: suppose you have a special cpu that can
            sort 5 integers in 1 cycle, 


      compute the largest, second largest, third largest integers among 25 integers
      using sort 5 function;
      
      Solution : seperate into 5 subarrays;

                sort all 5 = 5 cycles;

                sort subarray of subarrays, 1 cycle for their largest value;

                largest = largest of subarray;

                2nd largest is either 2nd of largest, or 1st of 2nd largest subarray (1 step away)

                3rd is either 3rd of largest subarray, 2nd of 2nd largest, or 1st of 3rd largest subarray
                (2 steps away);
                make a subarray from all possiblities, and make a sort5 call;
                
*/
import java.util.Arrays;
import java.util.Comparator;

class SubArrayComparator implements Comparator<int[]> {
    @Override
    public int compare (int[] a, int [] b){
        if(a[4] < b[4]){
            return -1;
        } else if (a[4] == b[4]){
            return 0;
        } else {
            return 1;
        }
    }


}


public class Sort5 {
    

    public void findLargest(int[] arr){
        int [][] subArrays = new int[arr.length/5][5];

        for (int i = 0; i < arr.length; i+= 5){
            subArrays[i/5] = Arrays.copyOfRange(arr, i, i + 5); 
            Arrays.sort(subArrays[i/5]); // 5 calls to sort 5
            //System.out.println(Arrays.toString(subArrays[i/5]));
        }

       
        //6 th call to sort5 to sort subarrays, get largest;
        SubArrayComparator comp = new SubArrayComparator();

        Arrays.sort(subArrays, comp);

        int largest = subArrays[4][4];

        System.out.println(largest);

        int[] next_largest = {
            subArrays[4][3], //candidate for 2nd largest 1st arr, 1 below 1st
            subArrays[4][2], //candidate for 3rd largest 1st arr, 2 below 1st
            subArrays[3][4], //candidate for 2nd largest 2nd arr, 1st
            subArrays[3][3],//candidate for 3rd largest  2nd arr, 1 below 1st
            subArrays[2][4] //candidate for 3rd largest  3rd arr, 1st
        };

        Arrays.sort(next_largest); //7th call to sort 5;

        int second = next_largest[4];
        int third = next_largest[3];

        System.out.println(second);
        System.out.println(third);

    }
    
    public static void main (String args[]){
        int[] input = {
            24, 21, 22, 35, 22,
            22, 34, 1, 2, 3, 
            22, 22, 1, 22, 3, 
            33, 12, 2, 30, 2,
            12, 31, 3, 2 ,1                        
         };


         Sort5 sort5 = new Sort5();


         sort5.findLargest(input);


    }

}
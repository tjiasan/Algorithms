/*
    Problem:
          Array of n-1 size, with 0 to n-1 distinct integers;
          There should be 1 integer missing;
          Sum of all elements from 0 to n -1 is 
           n *(n +1)/2, subtract the sum of array to sum missing number;

         Similarly if n+1 size, duplicated once, with 0 to n-1;
         duplicated is difference of n*(n+1)/2 - sum;

         Alternatively, compute XOR or all elements from 0 - n-1;
         Since every 1 ^ 2 ^ 1^ 2  = 0
         0 ^ missing = missing_number;

         Actual Problem:
         given array of n integers between 0 to n-1, exactly one appears
         twice meaning that 1 is missing; Figure out both;

    Solution :
        Sum of 1 to n-1 is  (n -1) n/2;      

        unique sums - sum of arr = difference between duplicate and unique;

        the xor of all values of unique arrays ^ all values of actual arrays = dulicate ^ missing;

        so if ( the xor ^ i == (i + diff)), that's the answer;

        Complexity O(2N) Space O(1);

*/


public class DuplicateMissing {


    public void findDuplicate(int[] arr){

        int unique_sum = (arr.length - 1) *arr.length /2;

        int sum = 0;
        int xor = 0;  

        for (int i = 0; i < arr.length; i++){
            sum += arr[i];
            xor ^= arr[i] ^ i;          
        }

        int diff = unique_sum - sum;

        for (int i = 0; i < arr.length; i++){
            if ( (xor ^ i) == (i + diff)){
                System.out.println("duplicate " + i);

                if ( (xor ^ (i + diff) )== i){
                    int missing = i + diff;
                    System.out.println("missing " + missing);
                }
            }
        }




    }

    public static void main (String args[]){
        DuplicateMissing duplicate = new DuplicateMissing();


        int[] input = {0, 1, 2, 3, 4, 5, 6, 8, 8, 9 ,10, 11};

        duplicate.findDuplicate(input);

    }
}
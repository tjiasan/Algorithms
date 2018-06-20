/* Problem : 
            given an array and a number

            determine three entries in the array (not necessarily distict),
            we can use the same entry more than once;
            that adds up to the number, 

            e.g.

    Solution: 
    
        Time Complexity O(n ^ 2);

        space complexity : O (N) if you use hashtable like example,
        O(1), with additional nlogn sorting step, 

        addtwo step :
            if hashtable, go throug O(N) algo looking through array finding the partner

            if sorted, start from index and last, two pointers, spit out answer when found;

            if doesn't start from index, will give repeated answers different permutation;


*/

import java.util.Arrays;

public class ThreeSum{

    public void find(int[] arr, int sum){
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i ++){
            this.find_two(arr, sum - arr[i], i);
        }
    }

    // start from index because previous ones covered
    public void find_two(int[] arr, int sum, int index){

        int i = index;
        int j = arr.length - 1;

        while (i <= j){
            if (arr[i] + arr[j] < sum){
                i ++;
            } else if (arr[i] + arr[j] == sum){
                System.out.println("");
                System.out.println(arr[index]);
                System.out.println(arr[i]);
                System.out.println(arr[j]);
                break;
            }
             else {
                j --;
            }
        }
    }

    public static void main (String args[]){


        ThreeSum three = new ThreeSum();

        int[] input = { 11, 2, 5, 7, 3 };

        int sum = 21;

        three.find(input, sum);

    }
}
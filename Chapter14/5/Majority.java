/*
    Problem: find the majority element in a sequence of strings

    e.g. array of strings = {a, a, a, b},
    a is majority element, you know ahead of time, there's a majority element

    Solve in O(N)

    Solution:
    brute force: use hashtable to track counts

    answer: since majority element is present,
            
            use first one as candidate, 
            if same, increase count,
            if below zero, set next one
            as candidate

            array of 10 numbers, 
            6 are same, 

            the majority have enough elements to cancel out any other numbers
*/

public class Majority {

    public void find(String [] arr){

        String candidate = arr[0];
        int count = 1;
      
        for (int i = 1; i < arr.length; i++) {
            if (candidate.equals(arr[i])){
                count ++;
            } else {
                count --;
            }

            if (count < 0){
                count = 1;
                candidate = arr[i];
            }
        }
        System.out.println(candidate);
    }


    public static void main (String args[]){

        String[] arr = {
            "b",
            "a",
            "c",
            "a",
            "a",
            "b",
            "a",
            "a",
            "c",
            "a"
        };

        Majority majority = new Majority();

        majority.find(arr);

    }


}
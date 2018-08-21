/* Problem:
            Binary Search with a twist: search first occurance and not just
            the number

Solution : force worst case scenario every time
            calculate midpoint;
            if mid is found or more, set right to the index,
            else set left

            when left and right is 1 apart or less,
            the first occurance is found;

            Complexity O (log n);

 Variant 1: find the first occurance greater:
            Solution, force worst case every time:
            if less, set left,
            else set right, 
            solution is right index; O log(n)
            
 Variant 2: check for local minimum at i,
            Solution, check left check right? O(1)

Variant 3 : get the interval for fisrt and last occurance;

            Solution, Normal binary search, return mid, left and r 

            copy vars
            then do a first occurance algo, set r1 = mid, l1 = last l , binary search to dist 1 
            and last occuranc algo, set l2 = mid, r2 = last r, binary serch to dist 1,
  

Variant 4: find prefix in sorted strings;
            Solution, 
                use a for loop to iterate through prefix;
                
                find interval using binary search at string[i], 
                use interval to set off next binary search at string[i], 

*/




public class BinarySearch {

    public int find (int[] arr, int find){


        int l = 0;
        int r = arr.length - 1;

        if (arr[0] == find){
            return 0;
        }
                
        // for normal binary search arr[r] != find
        while ( (r - l) > 1){
            int mid = (r + l)/2;

            if (arr[mid] < find){
                l = mid;
            } else if (arr[mid] >= find){
                r = mid;
            } 
        }   

        return r;

    }


    public static void main (String args[]){
        BinarySearch binarySearch = new BinarySearch();

        int[] input = { 0, 1, 3, 3, 3, 3, 4, 5, 6, 7 ,8 ,9, 10 };


        int index = binarySearch.find(input, 3);

        System.out.println(index);

    }



}
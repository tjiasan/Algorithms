/*
    Problem : given a sorted array,
            find where the content of the array equals to index;
            just one answer is sufficient
            e.g. {-1,0,2,3};
            2 or 3 is fine;

            Also, each integer is unique!
            this is what makes it possible

     Solution: 

        Because all integers are unique,
        arr[k + 1] = arr[k] + 1; minimum;

        this means, 

        { -4, -3, -1, 3, 4, 6, 7};

           0 , 1 , 2, 3, 4, 5, 6 


        if a solution exists, 
            if the difference between arr[k] - k is positive 

            everything on the right isn't possible;

            e.g. 6 - 5 = 1; because the minium increment is 1 for both, it won't ever reach the same no

            if the difference between arr[k] - k is negative
            .e.g -3 -1 = -4, this means everything on the left is impossible because decrement of -1, 

            wont ever catch up!


            so do binary search! if positive, right becomes mid,
            if negative left becomes mid,
            if zero, answer
            
            break if right - left distance = 1 or less;


*/


public class IndexSearch {

    public int search(int[] arr){

        int l = 0;
        int r = arr.length -1;

        if (arr [l]  == 0){
            return  l;
        } else if (arr[r] == r){
            return r;
        }

        while (r - l > 1){

            int mid = (r + l)/ 2;
            int diff = arr[mid] - mid;
    
            if (diff < 0){
                l = mid;
            } else if (diff > 0){
                r = mid;
            } else {
                return mid;
            }
        }

        return -1;

    }


    public static void main (String args[]){

        IndexSearch index = new IndexSearch();

        int[] input = { -3, -2, 1, 2, 3, 5, 7 };


        int same = index.search(input);

        System.out.println(same);


    }
}

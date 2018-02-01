/*
    Problem: find the sqrt of an integer

    Solution: right = input, left = 0
    mid = halfway;

    precision = 1;
    while (abs(diff) > precision)
        diff too big (input - mid), right = mid (make smaller) ;
        diff too small (input - mid), left = mid (make bigger) ;

    do until precision is reached;
*/


public class SqrtInt {

    public int calculate(int input){
        if (input < 1){
            return -1;
        }

        int right = input;
        int left = 0;
        int mid = 0;


        while (left <= right){

            mid = (right + left)/2;

           // System.out.println(mid);
  
            if (mid * mid < input){ //too small
               left = mid + 1; //prevent infinite loops
            } else {
               right = mid - 1;
            }            

        }

        return  left;

    }


    public static void main (String args[]){
        int input = 25;


        SqrtInt sqrt = new SqrtInt();

        int result = sqrt.calculate(input);
        System.out.println(result);
    }
}
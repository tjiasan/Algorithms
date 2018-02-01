
/*
    Problem: find the sqrt of an float

    Solution: right = input, left = 0
    mid = halfway;

    precision = 1;
    while (abs(diff) > precision)
        diff too big (input - mid), right = mid (make smaller) ;
        diff too small (input - mid), left = mid (make bigger) ;

    do until precision is reached;

Variant :
        Given X/Y

        compute x /y to a precision where you can only do add and multiply;

        if (y > x){
            min = 1,
            max = y,
        else {
            max = 1,
            min = 0,
            find mid,
        }
          find mid, 
             diff = y - x* mid;

             while diff > precision;
                if (diff > 0) {
                    min = mid;
                } else {
                    max = mid;
                }
        
    */


public class Sqrt {

    public double calculate(double input, double precision){
    

        double right = input;
        double left = 0;

        if (input < 1){
            right = 1;  
            left= input; 
        }

       
        double mid = (right + left)/2;

        double diff = input - mid * mid;

        while (Math.abs(diff) > precision){
            System.out.println(mid);
           if (diff > 0){ //too small
               left = mid;
           } else {
               right = mid;
           }

           mid = (right + left)/2;
           diff = input - mid * mid;            

        }
        return mid;

    }


    public static void main (String args[]){
        double input = 0.23;


        Sqrt sqrt = new Sqrt();
        double precision = 0.01;
        double result = sqrt.calculate(input, precision);
        System.out.println(result);
    }
}
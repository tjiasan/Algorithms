/*
    Problem: Given two positive integers,calculate the quotient only using addition, subtraction and shifts
    Solution: find the biggest 2^k *y and add to quotient, subtract. Find biggest denominator using binary search
*/

public class Divide {

    public int  FindHighestBase(int a, int b) {
        //assume 32 bit int
        int base = 15;
        int [] query_size = {8, 4, 2, 1}; 
     
        for (int i = 0; i < 4; i++){
            int shift = b << base;
            if (shift < a) {
                base += query_size[i];               
            }           
            else {
                base -= query_size[i];
            }           
        }
        
        //make sure it's lower than a, at most it's 1 iteration
        while ((b << base) > a){           
            base--;           
        }

        return base;
    }


     // a divide by b
    public int DivideInts(int a, int b){
        int quotient = 0;
        
        int dividend = a;
        int divisor = b;

        while (dividend >= divisor) {            
            int base = this.FindHighestBase(dividend, divisor);   
            dividend -=  (divisor << base);       //i.e divisor * 2 ^ base
            quotient +=  (1 << base);     // add 2 ^ base
            //System.out.println(divisor << base);           
        }

        System.out.println(quotient);
        
        return quotient;
       
    }

    public static void main (String args[]) {
        Divide divide = new Divide();        
        //divide.FindHighestBase(9, 3);
        divide.DivideInts(1651616, 2);
    }
}
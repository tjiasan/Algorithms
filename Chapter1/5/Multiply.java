/*
    Problem     : Multiply two numbers without using arithmetic operations
    Solution    : Shift and sum, less time complexity than adding multiple times
    Explanation : Break down (a) into base 2 binary components, and add a base 2 shifted (b)
    e.g. 6 x 2 
    is equivalent to (2^2 *1 + 2^1 *1) * 2;
    shifting 2 by 1 times is equivant to multiplying by 2^1  = 4;
    shifting 2 by 2 times is equivant to multiplying by 2^2  = 8;
    4 + 8 = 12;
*/

public class Multiply {

    public int Sum(int sum, int y){
       int carryover = 0;
       int flipmask = 1;
       int copy_sum = sum;
       int copy_y = y;
       int result = 0;

       while (true) {
          int current_sum = copy_sum & 1;
          int current_y = copy_y & 1;

        
          //use xor to calculate current bit 1 or 0; 111 = 1, 110 = 0, 001 = 0, 0 = 0
          if ((current_sum ^ current_y ^ carryover) == 1){
              result = result | flipmask;
          } 
          
          //calculate carryover;
          if (
              (current_sum & current_y) == 1 || 
              ((current_sum | current_y) & carryover) == 1
              ){
            carryover = 1;
          } else {
            carryover = 0;
          }         

          copy_sum >>= 1;
          copy_y >>= 1;
          flipmask <<= 1;

          if (copy_sum == 0 && copy_y == 0 && carryover == 0){
            break;
          }           
       }

       return result;
    }

    public int MultiplyInts(int a, int b) {
        int sum = 0; 
        while (a > 0) {

            if ((a & 1) == 1){
                sum = this.Sum(sum, b);
            }

            a >>= 1;
            b <<= 1;
        }

        return sum;
    }

    public static void main (String args[]) {

        Multiply multiply = new Multiply();

        System.out.println(multiply.MultiplyInts(3, 30));

    }

}
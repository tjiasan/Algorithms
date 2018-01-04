/*
    Problem: Given a integer 1234, compute an output that is
    4321;

    Solution: Extract the last digit by %10 and add to result;
            divide original by 10;
            if more than zero, multiply result by 10 else exit;

*/

public class ReverseDigits {
    
    public int reverse (int input) {
        int result = 0;

        while (true) {
            int last_digit = input % 10;
            
            result += last_digit;

            input /= 10;

            if (input > 0){
                result *= 10;
            } else {
                break;
            }
        }
        System.out.println(result);
        return result;
    }

    public static void main (String args[]) {
        ReverseDigits reverseDigits = new ReverseDigits();

        reverseDigits.reverse(1234);      

    }
}
/* 
 Problem: Figure out if an integer is a pallindrome, 121 for example, is a pallindrome
          Exclude all negative integers

*/
import java.util.*;
public class PallindromeInt {
    public ArrayList <Integer> reverse = new ArrayList <Integer> ();

    public void Reverse (int input) {
        while (true) {

            int last_digit = input % 10;  
            reverse.add(last_digit);
            input /= 10;
                      
            if (input <= 0) {
                break;
            }
        }        
    }

    public boolean Check (int input) {
        if (input < 0){
            return false;
        }
        final int iterations = reverse.size()/2 - 1;

        for (int i = reverse.size() - 1; i > iterations; i --) {
            int last_digit = input % 10;  
            input /= 10;

            if (last_digit != reverse.get(i)){
                return false;
            }
        }

        return true;
    }

    public static void main (String args[]){
        PallindromeInt pal = new PallindromeInt();

        int input = 12321;

        pal.Reverse(input);

        System.out.println(pal.Check(input));


    }

}
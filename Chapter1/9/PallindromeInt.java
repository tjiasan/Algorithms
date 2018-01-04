/* 
 Problem: Figure out if an integer is a pallindrome, 121 for example, is a pallindrome
          Exclude all negative integers

Solution: Reverse the digits and put it in an arraylist. 
          Iterate through n/2 times starting from the last element of the array list and the first (2 pointers);
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
        int counter = 0;

        for (int i = reverse.size() - 1; i > iterations; i --) {     
            if (reverse.get(counter) != reverse.get(i)){
                return false;
            }
            counter ++;
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
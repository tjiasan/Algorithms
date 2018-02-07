/* Problem : Figure out if a string can be rearranged into a pallindrome;
            e.g. "abab" can be "abba"

    Solution: Store character counts on hashtable;
                if length is odd, allow only 1 odd;

                Space O (C), Time O(N), since there area only 240 characters max;
            where c is number of distinct chars



*/
import java.util.HashMap;
import java.util.ArrayList;

public class Pallindrome {


    public boolean isPossible(String input){
        boolean result = true;

        HashMap <Character, Integer> counts = new HashMap<Character, Integer>();

        int is_odd = 1;

        if (input.length() % 2 == 0){
            is_odd = 0;
        }

        for (int i = 0; i < input.length(); i ++){
            if (counts.get(input.charAt(i)) == null){
                counts.put(input.charAt(i), 1);
            } else {
                int amount = counts.get(input.charAt(i));
                amount ++;

                counts.put(input.charAt(i), amount);
            }
        }

        ArrayList <Character> l = new ArrayList<Character>(counts.keySet());

        int odd = 0;
        for (int i = 0; i < l.size(); i++){
            Character current = l.get(i);
            System.out.println(counts.get(current));
            if (counts.get(current) %2 != 0){
                odd ++;
            }
        }

        if (odd > is_odd){
            return false;
        } else {
            return true;
        }
       
    }


    public static void main (String args[]){

        Pallindrome pallindrome = new Pallindrome ();

        String input = "edified";


        System.out.println(pallindrome.isPossible (input));


    }
}
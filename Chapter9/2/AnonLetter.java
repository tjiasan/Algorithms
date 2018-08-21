/*
    Problem: Given two text inputs, find out if it's possible to write an anonamous 
            letter (cutouts from magazine) from a magazine article;

    Solution: use a hash table to store character counts in magazine, 
            iterate through letter, subtract counts;
            if counts less than zero or null, false
            Complexity O(M+N), Space O(1);
*/
import java.util.HashMap;

public class AnonLetter{

    public boolean isPossible(String letter, String magazine){
        HashMap <Character, Integer> counts = new HashMap <Character, Integer>();

        boolean possible = true;

        for (int i = 0; i < magazine.length(); i++){
            if (counts.get(magazine.charAt(i)) == null){
                counts.put(magazine.charAt(i), 1);
            } else {
                int count = counts.get(magazine.charAt(i));
                count ++;
                counts.put(magazine.charAt(i), count);
            }
        }

        for (int i = 0; i < letter.length(); i ++){
            if (counts.get(letter.charAt(i)) == null){
                return false;
            } else {
                int count = counts.get(letter.charAt(i));
                count --;

                if (count == 0){
                    counts.remove(letter.charAt(i));

                } else {
                    counts.put(letter.charAt(i), count);
                }
            }
        }

        return possible;

    }


    public static void main (String args[]){

        AnonLetter anonLetter = new AnonLetter();

        String letter = "abcdefab";
        String magazine = "abcdefgaa";

        boolean possible = anonLetter.isPossible(letter, magazine);

        System.out.println(possible);

    }
}
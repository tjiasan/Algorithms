/*
Problem: Given a text and and array of strings,
    e.g. "amanaplanacanal" and { "can", "apl", "ana"},
    "aplanacan" is a substring that is the concatenation of all the words;
    give the index of the substring, assume all string in words are equal length
    it's possible to contain dupplicates in words array;

    Solution: 

    use rolling hash algo to calculate matches;

    use 2 hashmap:
        1) Stores hash integer to boolean (checking)
        2) Stores offset to start and end;


    iterate through text while cycling offsets :
        if (match){
            get offset specific data;

            if (null){
                start = i - word_length;
                end = i;
            } else {

                if (end + length < i){
                    reset
                     start = i - word_length;
                     end = i;
                } else {
                    end = i;
                   
                }     
            }

            put data in hashmap;
            compare to max;           
        }

        offset ++; or reset to 0;
        next rolling_hash;




    Complexity O(N) time O (W*n + n*W) space, where n is the length of words;

    worst case = O(2N)time O(W*N) space;
    since store history, 
    worst case is 2n;
    because remove forwards;
*/

import java.util.HashMap;
import java.util.LinkedList;

class Node {
    int start;
    int end;
}




public class Decomposition{

    //assuming a-z only (26 characters, probablity of collision = 0)
    public int hash(String word){
        int result = 0;
        int exponent = word.length() - 1;
        for (int i = 0; i < word.length(); i ++ ){
            int exp = (int) Math.pow(26, exponent);
            result += (int) word.charAt(i) * exp;
            exponent --;
        }
        return result;
    }

    public int roll(int hash, int next, int previous, int length){
        int exp = (int) Math.pow(26, length - 1);
        int result = hash - previous *  exp;
        result*= 26;
        result += next;

        return result;
    }

    public void decompose (String text, String[] words){
      
        HashMap<Integer, Boolean> words_hash = new HashMap<Integer, Boolean> ();
        HashMap<Integer, Node> running_offset = new HashMap<Integer, Node> ();

        int word_length = words[0].length(); //all same length

        for (int i = 0; i < words.length; i ++){
            words_hash.put(this.hash(words[i]), true);
        }
        //rolling hash
       
        int rolling_hash = this.hash(text.substring(0, word_length));      
        int max = 0;
        int max_start = 0;
        int max_end = 0;
        int offset = 1;
        
      
        for (int i = word_length - 1; i < text.length(); i ++){

            if (words_hash.get(rolling_hash) != null){
                Node last = running_offset.get(offset);

                if (last == null){
                    last = new Node();

                    last.start = i - word_length;
                    last.end = i;                

                } else {
                   
                    if (last.end + word_length < i){
                        last.start = i - word_length;
                        last.end = i;    
                    } else {                        
                        last.end = i;                
                    }       

                }

                running_offset.put(offset, last);

                if (last.end - last.start > max){                       
                    max = last.end - last.start;
                    max_start = last.start;
                    max_end = last.end;
                }
            }
            if (i < text.length() - 2){
                rolling_hash = this.roll(rolling_hash, text.charAt(i + 1), text.charAt(i - word_length + 1), word_length);
            }
           

            offset ++;
            if (offset > word_length){
                offset = 1;
            }

        }

        System.out.println(max);
        System.out.println(text.substring(max_start + 1, max_end +1));

    }

    public static void main (String args[]){
        Decomposition decomposition = new Decomposition();
        
        
        String text = "amanaplanacanal";

        String [] words = { "can", "apl", "ana" };


        decomposition.decompose (text, words);
    }



}
/* 
Problem : compute all pallindromic decomposition of a string

        the starting string doesn't have to be pallindromic;

        e.g. 
        abbac

        abba + c
        a + bb + a + c
        ...etc.


Solution:

    Seed and grow approach recursively

    then reconstruct combinations of answers
    recursively

    complexity : n *(n-1)/2;

*/

import java.util.HashMap;
import java.util.LinkedList;

public class Decomposition {

    public void enumerate(String input) {

        //enumerate evens
        HashMap<Integer, Integer> odd = new HashMap<Integer, Integer>();
        LinkedList<Integer> process = new LinkedList<Integer> ();
        
        for (int i = 0; i < input.length(); i++){
            odd.put(i, 1);
            process.addFirst(i);
        }

        odd = this.odd(odd, process, 3, input);

        HashMap<Integer, Integer> even = new HashMap<Integer, Integer>();

        for (int i = 0; i < input.length(); i++){
            process.addFirst(i);
        }
        even = this.even (even, process, 2, input);

        HashMap<Integer, LinkedList<Integer>> pallindromes = new HashMap<Integer, LinkedList<Integer>>();
        for (int i = 0; i < input.length(); i++){
            pallindromes.put(i, new LinkedList<Integer>());
        }


        for (Integer key : odd.keySet()){
            int length = odd.get(key);
            
            while (length > 0){
                pallindromes.get(key - length/2).addLast(length);
                length -=2;
            }
        }

        for (Integer key : even.keySet()){
            int length = even.get(key);
            while (length > 1){
                pallindromes.get(key - length/2 + 1).addLast(length);
                length -=2;
            }
        }

        //construct a hashmap of starting positions and lengths of pallindromes, then we construct answers recursively!


        this.construct(pallindromes, "", 0, input);

    }

    public void construct(HashMap<Integer, LinkedList<Integer>> pallindromes, String prev, int length, String input){
        if (length == input.length()){
            System.out.println(prev);
            return;
        }
        
        LinkedList<Integer> current = pallindromes.get(length);


        current.forEach((pal) -> {
            
            int next_length = length + pal;
            String next = input.substring( length, next_length );
            if (prev.length() != 0){
               next = prev + " + " + next;
            }

            this.construct(pallindromes, next, next_length, input);
            
        });

    }

    public HashMap<Integer, Integer> even (HashMap<Integer, Integer> result, LinkedList<Integer> process, int length, String input){
        LinkedList<Integer> next = new LinkedList<Integer> ();

        while (process.isEmpty() == false){
            int current = process.remove();
            int offset = length/2;

            int lower = current - offset + 1;
            int higher = current + offset;

            if (lower < 0 || higher > input.length() - 1){
                continue;
            } else if (input.charAt(lower) != input.charAt(higher)){
                continue;
            } else {
                next.addLast(current);
                result.put(current, length);
            }    
        }

        if (next.isEmpty() == false){
            return this.even(result, next, length + 2, input);
        } else {
            return result;
        }
    }


    public HashMap<Integer, Integer> odd (HashMap<Integer, Integer> result, LinkedList<Integer> process, int length, String input){
        LinkedList<Integer> next = new LinkedList<Integer> ();

        while(process.isEmpty() == false){
            int current = process.remove();
            int offset = length/2;

            if (current - offset < 0 || current + offset > input.length() - 1){
                continue;
            } else if (input.charAt(current - offset) != input.charAt(current + offset)){
                continue;
            } else {
                next.addLast(current);
                result.put(current, length);
            }
        }

        if (next.isEmpty() == false){
            return this.odd(result, next, length + 2, input);
        } else {
            return result;
        }
    }




    public static void main (String[] args){


        Decomposition decomposition = new Decomposition();

       // String input  = "abbac";

        String input = "abacc";
        decomposition.enumerate(input);

       // System.out.println(result.get(1));
    }

}
/*
    Problem : find lowest distance between two same words in a given sentence/text;


    Solution: use hashtable to store position of previously encountered word;
             if second encounter, calcualte distance, compare to min, and store current position;

            Time O(N),
            Space O(M), M is distinct words
*/


import java.util.HashMap;

public class LowestDist {


    public String getLowest(String[] input){

        HashMap <String, Integer> position = new HashMap<String, Integer>();

        int lowest_dist = input.length +1;
        String lowest_word = "";

        for (int i = 0; i < input.length; i++){
            if (position.get(input[i]) == null){
                position.put(input[i], i);
            } else {
                int dist = i - position.get(input[i]);

                if (dist < lowest_dist){
                    lowest_dist = dist;
                    lowest_word = input[i];
                }

                position.put(input[i], i);
            }


        }

        return lowest_word;
    }


    public static void main (String args []){

        LowestDist lowest = new LowestDist();

        String[] input = {
            "this",
            "is",
            "what",
            "no",
            "well",
            "what",
            "eh",
            "no"

        };

        String result = lowest.getLowest(input);

        System.out.println(result);

    }
}
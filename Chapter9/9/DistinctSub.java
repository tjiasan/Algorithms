/*
    Problem: Design the an algorithm to calculate
    the longest unique subarray such that no repeats;


    Solution :
            Use hashtable to keep track of text positions;

            if text position in hashtable is null or less than the global minimum,
            override in text position in hashtable;

            else {
                //duplicate detected
                calculate distance (i - 1) - global min,
                compare to the max distance,


                set global min to distance the text is previously located in + 1;
                
                override in text position in hashtable;
            }



            Complexity O(N), Space O(N);


*/


import java.util.HashMap;
public class DistinctSub {

    public void getUnique(String [] input ){
        HashMap<String, Integer> unique = new HashMap<String,Integer> ();

        int current_min = 0;
        int best_max = 0;
        int best_min = 0;
        int best_dist = 0;

        for (int i = 0; i < input.length; i++){

            if (unique.get(input[i]) == null || unique.get(input[i]) < current_min){
                unique.put(input[i], i);
            } else {

                int dist = i  - 1 - current_min; // one before replicate to beginning; 

                if (dist > best_dist){
                    best_min = current_min;
                    best_max = i - 1; //not including repeat
                }
               
                current_min = unique.get(input[i]) + 1; //one after previously repeated
                unique.put(input[i], i); //put repeat in database;
            }
            
        }
        System.out.println(best_min);
        System.out.println(best_max);

    }



    public static void main (String args[]){

        String [] input = {
            "bob1",
            "bob2",
            "bob3",
            "bob4",
            "bob3",
            "bob5",
            "bob6",
            "bob3"
        };


        DistinctSub dist = new DistinctSub();


        dist.getUnique(input);
    }

}
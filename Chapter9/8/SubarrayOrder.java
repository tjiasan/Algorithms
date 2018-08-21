/* Problem: Given an array of strings A, and queries Q of distinct elements,

            Give the shortest subarray, where Q is covered in order;

Solution: Since the elements is distinct, 
          use hashtable to keep track of what the order is while iterating through the text,

          when encounter first, write down min,
          when finished, write down max, reset to zero

          if mismatch order, 
            invaliate and restart at zero;
            if the invalid happens to be zero, reprocess zero;

          else keep cache of the shortest, 
          and if found shorter, replace
          
          
          //side note; if repeats allowed, need to branch/ recurse if encounter start;
    O(N) time
    O(M) space;

    Problem with book solution:


        3 Hashtable;
        1) ongoing position of query
        2) length of shortest subarray until and including query in Order;
        3) query ordering;

        calculate current distance by :
        getting previous distance, 0 if first, 
        get prevous position,
        begin = position - distance;

        update position and shortest subarry,


        result will be last in subarray;

        if query has repeating elements
        e.g. Order = {a, b, c};

        elements {a, b, a, c} will still be valid
        whereas there would not be a valid answer with mine
        Also can't do repeated elements in query;

     
*/

import java.util.HashMap;

public class SubarrayOrder {


    public void get(String[] text, String[] query){

        HashMap <String, Integer> order = new HashMap <String, Integer>();

        for (int i = 0; i < query.length; i ++){
            order.put(query[i], i);
        }

        int current = 0;
        int current_min = 0;

        int best_dist = text.length;
        int best_max = 0;
        int best_min = 0;

        for (int i = 0; i < text.length; i ++){
            Integer current_order = order.get(text[i]);
            if (current_order != null){
                if (current_order == current) {
                    if (current_order == 0){
                        current_min = i;
                        current ++;
                    } else  if (current_order == query.length -1){
                        int dist = i - current_min;
                        current = 0;

                        if (dist < best_dist) {
                            best_max = i;
                            best_min = current_min;
                        }
                    } else {
                        current ++;
                    }

                } else {
                    current = 0;
                    current_min = 0;
                    if (current_order == 0){
                        current_min = i;
                        current ++;
                    }
                }
            }
        }

        System.out.println(best_min);
        System.out.println(best_max);
      
    }


    public static void main (String args[]){

        String[] text = {
            "banana",
            "apple",
            "cat",
            "apple",
            "banana"
        };

        String [] query = {
            "apple",
            "banana"
        };

        SubarrayOrder ord = new SubarrayOrder();

        ord.get(text, query);


    }


}
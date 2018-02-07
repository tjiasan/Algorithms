/*
    Problem:  Given an Array of strings (text), and an set of strings (search terms),

        Find the index (start and end) of the text (begin and end) that contains all the search terms. 

    Solution: Combine the array of strings into one superstring, or keep track of absolute index;
        use a doubly linked_list and hashmap;

        when encounter a text in its position;

        if it is part of a query, if it's never been seen
            insert into linked list
        else update the linkedlist, such that it's at the end position;
        
        save node (position, query) to hashmap;

        
        if complete (seen everything); /// save min, max, dist to best
        begin distance calculations for next iterations;
            updated linkedlist;

            get min (first of the linked list);
            
            if (calc dist < best_dist){
                replace
            }

            where K is number of queries;
            Complexity O (N + K), space o(K);
            why it works: updates to the hashmap represents
            the minimum amount of changes becuse it's ordered;

            therefore it'll go through most closely related
            combinations only, which is the combination that matters;
            e.g {3, 7, 12}
            {5, 11}
            {0, 10}

            don't want to consider 12,5, 0

            in order: combinations considered are 
            {0, 3, 5}
            { 7, 5, 0}
            {7, 10, 11}
            {12, 11, 10}

            altSolution: 
                use i and j,
                advance j until covers everything, use hashtable to keep track
                advance i until uncovered, subtract hashtable, uncovered - 1 is distance,

                advance j until covered, 
                advance i, until uncovered and so on, 

                Still O(K) space and O (N + K) time;

            Variant: every distinct value is found between i and j;
                    Solution: instead of query, use distinct values to create new,
                    if found new discard previous max;
            
            Variant: Given an array A and positive integer k, rearrage so that no elements
            are k or less apart; wtf?
                    Solution: compress A in hashtable, override original when iterating through 
                    hashtable. 
*/

import java.util.HashMap;
import java.util.ArrayList;

class Node {
    public String query;
    public int position;
    public Node next;
    public Node previous;
}

class LinkedList {
    public Node start;
    public Node end;

    public LinkedList () {
        start = null;
        end = null;
    }
    public void update(Node n){

        if (n == end){
            return;
        } else if (n == start){
            start = start.next;
            start.previous = null;
        } else {
            Node prev = n.previous;
            Node next = n.next;
            prev.next = next;
            next.previous = prev;
        }

        //attach to end;
        end.next = n;
        n.previous = end;       

        end = end.next;

    }

    public void insert(Node n){
        if (start == null){
            start = n;
            end = n;
            return;
        }

        end.next = n;
        n.previous = end;

        end = end.next;
    }

    public int getMin(){
        return start.position;
    }

}



public class SmallestSubarray{  

   
    public String[] split(String input){
        ArrayList<String> result = new ArrayList<String>();

        String partial = "";
        for (int n = 0; n < input.length(); n++){
            if (input.charAt(n) == ' '){
                result.add(partial);
                partial = "";
            } else if (input.charAt(n) == '.'){
                continue;
            } else {
                partial += input.charAt(n);
            }
        }
        result.add(partial);

        String[] result_array = new String[result.size()];
        result_array = result.toArray(result_array);
        return result_array;
    }
   


    public void getSmallest (String[] text, String[] queries){
        HashMap<String, Node> query_pos = new HashMap<String, Node>();
        LinkedList linked_list = new LinkedList();
        for (int i = 0; i < queries.length; i ++){
            Node query = new Node();
            query.position = -1;
            query.query = queries[i];
            query_pos.put(queries[i], query);
        }

        boolean complete = false;

        int i;
        int j;

        String [] breakdown;
        int absolute_position = 0;
        int completion_state = 0;

        int best_min = 0;
        int best_max = 0;
        int best_dist = 0;

        for (i = 0; i < text.length; i ++){
            breakdown = this.split(text[i]);

            for (j = 0; j < breakdown.length; j++){
                
                if (query_pos.get (breakdown[j]) != null){
                    Node to_insert = query_pos.get(breakdown[j]);

                    if (to_insert.position == -1){
                        to_insert.position = absolute_position;
                        linked_list.insert(to_insert);
                        completion_state ++;
                    } else {
                        to_insert.position = absolute_position;
                        linked_list.update(to_insert);
                    }

                    //check if all complete
                    if (completion_state == queries.length){
                        complete = true;                        
                        best_max = absolute_position;

                        best_min = linked_list.getMin();

                        best_dist = best_max - best_min;
                        completion_state ++;
                        absolute_position++;
                        continue;
                    }

                    if (complete){
                        int candidate_dist = absolute_position - linked_list.getMin();
                        if (best_dist > candidate_dist){

                            best_dist = candidate_dist;
                            best_max = absolute_position;
                            best_min = linked_list.getMin();
                        }
                    }
                }          

                absolute_position++;
            }
        }


        System.out.println(best_min);
        System.out.println(best_max);
    }


    public static void main (String args[]){
        SmallestSubarray smallest = new SmallestSubarray();

        String [] text = {
            "this and that bla bla",
            "bla bla this what who where when",
            "loren ipsum is gibberish haha",
            "save what now? save the union",
            "except for this this is not save",
            
        };

        String [] queries = {
            "save",
            "union",
            "this"
        };

        smallest.getSmallest(text, queries);


    }
}
/*
    Problem: Given a search queries seperated by \n 

    Figure out the k most abundant searches

    Solution : use min-heap k size + hash
            1) do counts;
            2) iterate through counts to create min heap k size
            3) copy to string array;

            Complexity O (N + m log k), where m is number of unique strings;
            Space O (M +K);

*/



import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;

class Node {
    public String query;
    public int amount;

    public Node (String q, int a){
        query = q;
        amount = a;
    }

}

class NodeComparator implements Comparator<Node> {
    
    @Override
    public int compare (Node x , Node y){
        if (x.amount < y.amount){
            return -1;
        } else if (x.amount == y.amount){
            return 0;
        }
        return 1;

    }

}


public class KMost{

    public String[] top (String[] input, int k){
        String [] result = new String[k];

        HashMap<String, Node> counts = new HashMap<String, Node>();

        NodeComparator max = new NodeComparator();
        PriorityQueue<Node> min_heap = new PriorityQueue<Node>(k, max);

        for (int i = 0; i < input.length; i ++){
            int amount = 1;
            if (counts.get(input[i]) == null){
                Node input_node = new Node (input[i], amount);
                counts.put(input[i], input_node);
            } else {
                Node count = counts.get(input[i]);
                count.amount ++;
                counts.put(input[i], count);
            }       
        }

        ArrayList <String> l = new ArrayList<String>(counts.keySet());

        for (int i = 0; i < l.size(); i++){
            Node curr = counts.get(l.get(i));

            if (min_heap.size() < k){
                min_heap.add(curr);
            } else{
                if (min_heap.peek().amount < curr.amount){
                    min_heap.remove();
                    min_heap.add(curr);
                }
            }
        }

        for (int i = 0; i < k; i ++){
            result[i] = min_heap.poll().query;
        }

        return result;
    }

    public static void main (String args[]){

        KMost kmost = new KMost();

        String [] input = {
            "bob0",
            "bob", 
            "bob", 
            "bob", 
            "bob", 
            "bob", 
            "bob2",
            "bob2",
            "bob2",
            "bob2",
            "bob3",
            "bob3",
            "bob3",
            "bob3",
            "bob3",
            "bob4",
            "bob5",
            "bob6",
        };

        String [] result = kmost.top(input, 3);
        System.out.println(Arrays.toString(result));

    }
}
/*

*/
import java.util.LinkedList;
import java.util.HashMap;

class Node {
    public String data;
    public Node from;

    public Node(String d){
        data = d;
        from = null;
    }
}

public class Transform {

    public void path (String origin, String destination, LinkedList<String>  dictionary){

        LinkedList<Node> next = new LinkedList<Node>();

        Node start = new Node(origin);
        dictionary.remove(origin);
        next.push(start);

        Node ans = this.recurse(destination, dictionary, next);

        

        Node copy = ans;
        while(copy != null){
            System.out.println(copy.data);
            copy = copy.from;
        }

    }

    public Node recurse (String destination, LinkedList<String> dictionary, LinkedList<Node> process ){
        LinkedList<Node> next = new LinkedList<Node>();
        
       
        while(process.isEmpty() == false){
            LinkedList<String> next_dictionary = new LinkedList<String>();
            Node current = process.removeFirst();

            while(dictionary.isEmpty() == false){
                String compare = dictionary.removeFirst();

                int counter = 0;

                for (int i = 0; i < current.data.length(); i ++){
                    if (current.data.charAt(i) != compare.charAt(i)){
                        counter ++;
                    }
                }
              

                if (counter == 1){
                    Node diff = new Node(compare);
                    diff.from = current;

                    if (compare.equals(destination)){
                        return diff;
                    }

                    next.addLast(diff);    
                } else {
                    next_dictionary.addLast(compare);
                }
            }


            dictionary = next_dictionary;
        }


        return this.recurse(destination, dictionary, next);

    }

    public static void main (String args[]){
        Transform transform = new Transform();

        LinkedList<String> dictionary = new LinkedList<String>  ();
        dictionary.addLast("bat");
        dictionary.addLast("cot");
        dictionary.addLast("dog");
        dictionary.addLast("cat");
        dictionary.addLast("dag");
        dictionary.addLast("dot");
        dictionary.addLast("cat");

      

      transform.path ("cat", "dog", dictionary);
    }
}
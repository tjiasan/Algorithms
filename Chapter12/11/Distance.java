
/*
    problem :
            find longest distance in a tree;

            a node may have more than 1 children;

            distance from node to child is in distance;

    Solution: 
            Standard recursive, put children in as head in recursive algorithm;

            compare U shaped loop to global max  before returning:
            i.e. max + 2nd max distance compared to global max!
            
            return max distance from  children to max leaf node + distance to head's parent;

            Each node is only visited once !;
            uses log(n) space
*/
import java.util.Stack;
import java.util.Iterator;

class Node {

    public Stack <Node> children;
    public int distance; // distance to Parent
    public String name;

    public Node (int d, String n) {
        children = new Stack<Node> ();
        distance = d;
        name = n;
    }

    public void add(Node n){
        children.push(n);
    }

}

class ResultNode {
    public int dist;
    public String name;

    public  ResultNode (int d, String n) {
        dist = d;
        name = n;
    }
}

public class Distance {

    public ResultNode longest;

    public Distance (){
        longest = new ResultNode(0, "");
    }


    public ResultNode longest (Node head){
   
        if (head.children.isEmpty() == true){
            ResultNode result = new ResultNode(head.distance, head.name);

            return result;
        }

        Iterator<Node> iter = head.children.iterator();

        ResultNode max = new ResultNode(0, "");
        ResultNode max1 = new ResultNode(0, "");

        while (iter.hasNext()){
            ResultNode dist = this.longest(iter.next());
           
            if (dist.dist > max.dist){
                if (max.dist != 0){
                    max1 = max;
                }
                max = dist;

            } else if (dist.dist > max1.dist){
                max1 = dist;
            }
        }
        
           

        if (max.dist + max1.dist > longest.dist){
            longest.dist = max1.dist + max.dist;
            longest.name = max1.name + " -->" + max.name;

            if (max1.dist == 0){
                longest.name = head.name + " -->" + max.name;
            }
        }



        max.dist += head.distance;

        return max;
    }

    public static void main (String args[]){

       

        Distance distance = new Distance();

        Node head = new Node (0, "A");

        Node left = new Node (100, "B");

        Node right = new Node (50, "C");

       // head.add(left); //comment to make it D --> E
        head.add(right);

        Node rightLeft = new Node(100, "D");

        Node rightRight = new Node (400, "E");

        right.add(rightLeft);
        right.add(rightRight);

        distance.longest(head);

        distance.check();       

    }

    public void check(){
        System.out.println(longest.dist);
        System.out.println(longest.name);
    }


}
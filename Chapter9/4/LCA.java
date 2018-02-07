/*
    Problem : Compute LCA in no less than the height difference of the tree

            (lowest common ancestor)

            e.g.
            A
          /  \
        B    C

   Solution: use hashtable to store node, 
             if present, then it's parent;
            Complexity O(H1 + H2) space and time

*/
 import java.util.HashMap;

class Node {
    public Node left;
    public Node right;
    public Node parent;
    public int data;

    Node(int d){
        left = null;
        right = null;
        parent = null;
        data = d;
    }
}

public class LCA {

    public Node get(Node first, Node second){

        HashMap<Node, Boolean> parents = new HashMap<Node, Boolean>();

        Node parent1 = first.parent;
        Node parent2 = second.parent;

        while (true){
            if (parent1 != null){
                if (parents.get(parent1) == null){
                    parents.put(parent1, true);
                    parent1 = parent1.parent;
                } else {
                    return parent1; 
                }
            }
            if (parent2 != null){
                if (parents.get(parent2) == null){
                    parents.put(parent2, true);
                    parent2 = parent2.parent;
                } else {
                    return parent2; 
                } 
            }

        }

    }


    public static void main (String args[]){


        LCA lca = new LCA();


        Node head = new Node(1);

        Node left = new Node(2);

        Node right = new Node(3);
        
        head.left = left;
        head.right = right;
        left.parent = head;
        right.parent = head;

        Node right_children = new Node(4);
        Node right_children1 = new Node (5);

        right.right = right_children;
        right.left = right_children1;

        right_children.parent = right;
        right_children1.parent = right;

        Node result = lca.get(right_children, right_children1);

        System.out.println(result.data);

        
        Node result1 = lca.get(left, right_children1);

        System.out.println(result1.data);
    }
}
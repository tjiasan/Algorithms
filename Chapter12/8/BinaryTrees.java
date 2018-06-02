/*
    Problem: Generate all possible binary trees
            of n nodes

    Solution: to get around the reference / copying issue
               

        create solution from the terminal up, attaching heads as you recurse out;

        1) create all possible left right splits
           e.g. need 3 children
           make a  for loop to allocate
           0 left,  3 right combination+
           1 left , 2 right combination+
            etc...

            since left result can have more than 1 answer
            the answer is the combination of left allocation and right allocation 

            may be able to save speed by 2x if just calculate halfway and duplicate;
            during for loop split;


*/

import java.util.Arrays;
import java.util.Stack;


class Node {

    public Integer data;
    protected Node left;
    protected Node right;

    public Node (){
        left = null;
        right = null;
        data = 0;  
    }

    public Node (int d, Node l, Node r, Node p){
        data = d;
        left = l;
        right = r;    
    }

    public void setData(int d){
        data = d;
    }

    public int getData(){
        return data;
    }

    public void setRight(Node r){
        right = r;
    }
    public void setLeft(Node l){
        left = l;
    }

    public Node getRight(){
        return right;
    }

    public Node getLeft(){
        return left;
    }   


}

public class BinaryTrees {

    public Node[] create(int nodes){

        if (nodes == 1){
            Node[] result = new Node[1];
            result[0] = new Node();
            return result;
        } 

        Stack<Node> result = new Stack <Node>();


        int leftover_nodes = nodes - 1;

        for (int i = 0; i <= leftover_nodes; i ++){
            int split_left = i;
            int split_right = leftover_nodes - i;

            Node[] left = new Node[0];
            Node[] right = new Node[0];
            if (split_left > 0){
               left = this.create(split_left);
            }
            if (split_right > 0){
                right = this.create(split_right);
            }

            if (left.length > 0 && right.length > 0){
                for (int k = 0; k < left.length; k ++){
                    for (int n = 0; n < right.length; n++){
                        Node head = new Node();
                        head.setLeft(left[k]);
                        head.setRight(right[n]);
                        result.push(head);
                    }
                }
            } else if (left.length > 0) {
                for (int k = 0; k < left.length; k++){
                    Node head = new Node();
                    head.setLeft(left[k]);
                    result.push(head);
                }

            } else if (right.length > 0) {
                for (int k = 0; k < right.length; k++){
                    Node head = new Node();
                    head.setRight(right[k]);
                    result.push(head);
                }
            }

        }
       

        Node[] final_result = new Node[result.size()];

        int counter = 0;
        while(result.isEmpty() == false){
            final_result[counter] = result.pop();
            counter ++;
        }
        return final_result;
    
    }

    //Test to see if all answers have same number of nodes
    public int nodes_counter (Node head){
        int result = 1;

        if (head.getLeft() != null){
            result += this.nodes_counter(head.getLeft());
        }
        if (head.getRight() != null){
            result += this.nodes_counter(head.getRight());
        }
        return result;
    }

    public void test(Node[] result){
        for (int i = 0; i < result.length; i++){
            System.out.println(this.nodes_counter(result[i]));
        }
    }

    public static void main (String args[]){

        BinaryTrees binary = new BinaryTrees();

        Node[] result = binary.create(4);

        System.out.println(result.length);// 5 results for 3 nodes

        binary.test(result);// all showed right nodes


     /*
        // Test for n = 3, all 5 accounted for
        //0, left, left
        System.out.println(result[0].getLeft().getRight());
        //1, left left
        System.out.println(result[1].getLeft().getLeft()); 
        
        //2, both
        System.out.println(result[2].getLeft());
        System.out.println(result[2].getRight());
    
         //3, right
         System.out.println(result[3].getRight().getRight());

         System.out.println(result[4].getRight().getLeft());

         */
    }
}
/* Problem: Do an inorder traversal without using recursion

    Solution: use a stack to store nodes while traversing left, set node as left,
            when left null visit 
            if there's right, set head as right,

            if right is null, pop, and set as head
*/

import java.util.Stack;

class Node {

    protected Integer data;
    protected Node left;
    protected Node right;


    public Node (){
        left = null;
        right = null;
        data = 0;
    }

    public Node (int d, Node l, Node r){
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


public class Inorder {

    void iterative (Node head){
        Stack <Node> left = new Stack<Node>();

        while (true){

            while (head.getLeft() != null){
                Node tmp = new Node(head.getData(),null, head.getRight());
                left.push(tmp);
                head = head.getLeft();
            }    

            System.out.println(head.getData());

            if (left.empty() && head.getRight() == null){
                break;
            }

            if (head.getRight() != null){
                head = head.getRight();
            } else {
                head = left.pop();
            }
        }

    }


    void recursive (Node head) {

        if (head == null){
            return;
        }
        
        this.recursive(head.getLeft());
     
        System.out.println(head.getData());   //visiting
   
        this.recursive(head.getRight());

    }

    public static void main (String args[]){       

        Inorder inorder = new Inorder();
     
        Node head = new Node(314, null, null);

        Node left = new Node (6, null, null);

        Node right = new Node (7, null, null);

        Node left_child = new Node(271, null ,null);

        Node right_child = new Node(1, null, null);

        Node left_child2 = new Node(561, null,null);

        Node left_child3 = new Node(28, null,null);
        Node left_child4 = new Node(0, null,null);

        Node left_child5 = new Node(3, null,null);
        Node left_child6 = new Node(17, null,null);

        head.setLeft(left);
        head.setRight(right);
        right.setRight(right_child);
        left.setLeft(left_child);
        left.setRight(left_child2);

        left_child.setLeft(left_child3);
        left_child.setRight(left_child4);

        left_child2.setRight(left_child5);
        left_child5.setLeft(left_child6);

        //inorder.recursive(head);

        inorder.iterative(head);


    }
}
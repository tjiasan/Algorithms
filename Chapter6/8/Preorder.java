/*                  314
                    /  \
                   6    7
                  / \     \
               271  561    1
               / \    \
             28   0    3
                       /
                      17          

Problem: Compute preorder traversal without recursion

Solution: Use a stack;

        Print current, push any right to stack;

        Traverse through left, while pushing any right to stack;

        if there's nothing in stack, break;

        else set head to stack.pop();

        Time comlexity O(N), Space O(H);
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


public class Preorder {

    void iterative (Node head){
        Stack <Node> right = new Stack<Node>();
        
        while (true){
            System.out.println(head.getData());

            if (head.getRight() != null){
                right.push(head.getRight());
            }

            while (head.getLeft() != null){
                head = head.getLeft();
                if (head.getRight() != null){
                    right.push(head.getRight());
                }
                System.out.println(head.getData());
            }

            if (right.empty()){
                break;         //have to break here or else may have null pointer errors
            }

            head = right.pop();

        }
    }


    void recursive (Node head) {

        if (head == null){
            return;
        }
        System.out.println(head.getData());   //visiting
        
        this.recursive(head.getLeft());  
        this.recursive(head.getRight());

    }

    public static void main (String args[]){       

        Preorder preorder = new Preorder();
     
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

        preorder.recursive(head);
        System.out.println("iter");
        preorder.iterative(head);


    }
}
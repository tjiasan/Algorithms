/*
    Problem:
                Assuming A perfect binary tree
                
                make it such that the leftmost node have a reference to the node at the same
                level beside it;

                A 
              B --> C
           D -->E -->F -->G     

    Solution:
           1
          2 3
        4 5 6 7

        for imperfect binary tree, use a stack to load the next level, and
        set the right side accordingly

        for a perfect binary tree, set the first getRight side for level 1;
        Since current level have a getRight()previously set, no need for a stack;
        thus, save leftmost children for next iteration,
        keep setting children's right until getRight() == null;

        more precisely: 
               1) same child, set left child to right side
                2) different child, set right child, to left child of getRightside,
                    shift parent to the right side
                         
*/


import java.util.Stack;


class Node {

    public int data;
    protected Node left;
    protected Node right;
    protected Node right_side;

    public Node (){
        left = null;
        right = null;
        data = 0;
        right_side = null;
    }

    public Node (int d, Node l, Node r){
        data = d;
        left = l;
        right = r;
        right_side = null;
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

    public void setRightSide(Node rs){
        right_side = rs;
    }

    public Node getRightSide(){
        return right_side;
    }


}


public class RightSide {

    //for imperfect binary tree
    public void setRight(Node head){

        Stack<Node> current = new Stack <Node>();

        current.push(head.getLeft());
        current.push(head.getRight());

        while (current.isEmpty() == false){
            Stack<Node> next = new Stack<Node>();

            while (current.isEmpty() == false){
                Node dest =  current.pop();
                Node source = null;

                if (current.isEmpty() == false){
                    source = current.peek();
                    source.setRightSide(dest);
                }                
                
                
                if (dest.getRight()!= null){
                    next.push(dest.getRight()); 
                }
                if (dest.getLeft() != null){
                    next.push(dest.getLeft());
                }

            }
            while (next.isEmpty() == false){
                current.push(next.pop());
            }

        }
    }

    //for perfect binary trees
    public void setRightConst(Node head){



        Node start = head.getLeft();
        start.setRightSide(head.getRight());

        while (start.getLeft() != null){
            Node right_trav = start;
            boolean set_left = true;
            while (right_trav != null){

                if (set_left){
                    right_trav.getLeft().setRightSide(right_trav.getRight());
                    set_left = false;
                } else {
                    if (right_trav.getRightSide() != null){
                        right_trav.getRight().setRightSide(right_trav.getRightSide().getLeft());
                    }
                    set_left = true;
                    right_trav = right_trav.getRightSide();
                }

            }
            start = start.getLeft();
        }




    }


    public static void main (String args[]){

        RightSide rightSide = new RightSide();

        Node head = new Node(1, null, null);
        Node left = new Node(2, null, null);
        Node right = new Node(3, null, null);

        Node left_child1 = new Node(4, null, null);
        Node left_child2 = new Node(5, null, null);

        Node right_child1 = new Node(6, null, null);
        Node right_child2 = new Node(7, null, null);


        head.setLeft(left);
        head.setRight(right);

        left.setLeft(left_child1);
        left.setRight(left_child2);
        
        right.setLeft(right_child1);
        right.setRight(right_child2);

        //rightSide.setRight(head);
        rightSide.setRightConst(head);

        System.out.println(head.getLeft().getLeft().getRightSide().getData());
    }

}

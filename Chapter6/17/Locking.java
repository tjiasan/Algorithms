/*
Problem: Implement a locking algorithm
            Node has locked/unlocked

Solution: O(1) to check locked, get node

            O(H) to lock, O(H) time to check parent is locked, O(H) time to set to 2;

            O(H) to unlock, unlock all parents to root (set to 2 to 0);

*/

import java.util.HashMap;

class Node {

    public int data;
    protected Node left;
    protected Node right;
    protected Node parent;
    public int locked;

    public Node (){
        left = null;
        right = null;
        parent = null;
        data = 0;
        locked = 0;
    }

    public Node (int d, Node l, Node r){
        data = d;
        left = l;
        right = r;    
        parent = null;
        locked = 0;
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

    public Node getParent(){
        return parent;
    }
    
    public void setParent(Node p){
        parent = p;
    }
}

public class Locking {

    public HashMap<Node, Integer> locks;

    public Locking (){
        locks = new HashMap<Node, Integer>();
    }

    public boolean isLocked(Node n){
        if (n.locked == 1){
            return true;
        }

        return false;
    }

    public boolean lock (Node n){
        if (n.locked == 0){
            Node check = n;
            while (check.getParent() != null){
                check = check.getParent();
                if (check.locked == 1){
                    return false; //parent is locked
                }
            }

            n.locked = 1;
            while (n.getParent() != null){
                n = n.getParent();
                n.locked = 2; //prevent locking of ancestors
            }

            return true;

        } else {
            //is locked or ancestor is locked
            return false;
        }

    }

    public boolean unlock (Node n){
        if (n.locked == 0){
            return false;
        }

        n.locked = 0;

        while (n.getParent() != null){
            n = n.getParent();
            n.locked = 0;
        }
        
        return true;
    }


    public static void main (String args[]){

        Locking locking = new Locking();

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

        left.setParent(head);
        right.setParent(head);

        left_child1.setParent(left);
        left_child2.setParent(left);

        right_child1.setParent(right);
        right_child2.setParent(right);

    }

}
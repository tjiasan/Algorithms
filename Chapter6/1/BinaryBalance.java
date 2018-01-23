/*
    Problem: check if a binary tree is balanced:
            1) right and left sub-trees are balaced;
            2) is balanced if left height - right height absolute no more than 1;

    Solution: Recursive:
              fcn return height, if -1, means unbalanced; 
             fcn set a = this.fcn(left, level +1);
             if null return level ; and vice versa for right

             if (difference between l and right too great), return -1, else return max height;

             final int result is the size of the greatest subtree that is complete;
*/

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

class BinaryTree {

    public Node head;

    public BinaryTree (){
        head = null;
    }

    public void insert (int n){
        if (head == null){
            head = new Node(n, null ,null);
            return;
        }

        Node ins = new Node(n, null, null);
        Node ptr = head;

        while (true){
            if (ins.getData() > ptr.getData()){
                if (ptr.getRight() == null){
                    ptr.setRight(ins);
                    return;
                } else {
                    ptr = ptr.getRight();
                }
            } else {
                if (ptr.getLeft() == null){
                    ptr.setLeft(ins);
                    return;
                } else {
                    ptr = ptr.getLeft();
                }
            }
        }
    }
}

public class BinaryBalance {

    public int is_balanced (Node head, int level){
        int height_left;
        int height_right;

        if (head.getRight() != null){
            height_right = this.is_balanced(head.getRight(), level + 1);
        } else {
            height_right = level;
        }
        if (head.getLeft() != null){
            height_left = this.is_balanced(head.getLeft(), level + 1);
        } else {
            height_left = level;
        }

        if (height_left == -1 || height_right == -1){
            return -1;
        }

        if (Math.abs(height_right - height_left) <= 1){
            return Math.max(height_right, height_left);
        } else {
            return -1;
        }
    }


    public static void main (String args[]){

        BinaryTree bin = new BinaryTree();

        bin.insert(10);

        bin.insert(5);
        bin.insert(3);
        bin.insert(7);
        bin.insert(6);
        /*  uncomment to make unbalanced
        bin.insert(2);
        bin.insert(1);
        bin.insert(0);
        */
        bin.insert(15);
        bin.insert(12);
        bin.insert(17);
        
        BinaryBalance bal = new BinaryBalance();

        int result = bal.is_balanced(bin.head, 0);
        
        System.out.println("Final result is " + result);

    }
}
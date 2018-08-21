/* Problem: get the leaves of the binary tree into a linked list

    Solution: using modified preorder recursive, 
              check if head has both nulls,
              if it is both nulls, push to linked list global;

              Time complexity O(N);

              return an int, from left and right
              if (l + r = 0) means leaf;
              i.e. if (head == null return 0; else return 1)

*/

import java.util.LinkedList;

class Node {

    public int data;
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




public class LinkedListLeaf {

    public LinkedList<Node> result; 



    public LinkedListLeaf() {
        result = new LinkedList<Node>();
    }

    public Node getLeafs(Node head){

        if (head == null){
            return null;
        }
        
        if (head.getLeft() == null && head.getRight() == null){
            result.push(head);
        } else {
            this.getLeafs(head.getLeft());  
            this.getLeafs(head.getRight());
        }
        
        return head;
    
    }

    public void display(){
        System.out.println(result.pop().getData());
  
        System.out.println(result.pop().getData());
        System.out.println(result.pop().getData());
        System.out.println(result.pop().getData());
    }



    public static void main (String args[]){

        LinkedListLeaf leaf = new LinkedListLeaf();
     
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

        leaf.getLeafs(head);

        leaf.display();

    }


}
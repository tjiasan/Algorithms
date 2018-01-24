/*Problem: 
    Given a preorder and an inorder traversal of a binary tree, 
    reconstruct a binary tree
  Solution v1:

    Construct from pre-order sequence:
        1) start from head, go all the way left, and right recurse
        2) Left of head is next pre-order, right of head is
            inorder right of head till end,
        2) keep going until i is start of pre-order;
        
     Complexity: 
                O(N ^2) time (improved in v2 with hash), space O(H);   

*/

import java.util.Stack;
import java.util.Arrays;
class Node {

    public Integer data;
    protected Node left;
    protected Node right;
    protected Node parent;

    public Node (){
        left = null;
        right = null;
        data = 0;
        parent = null;
    }

    public Node (int d, Node l, Node r, Node p){
        data = d;
        left = l;
        right = r;
        parent = null;
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

    public void setParent(Node p){
        parent = p;
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

}


public class Reconstruct {


    public Node[] convertNodeArray(Stack n){
        Node[] result = new Node[n.size()];

        int iterations = n.size();
        for (int i = 0; i < iterations; i++){
            result [i] = (Node) n.pop();
            
        }

        return result;
    }
 
    public void printArray(Node[] arr){
        int iterations = arr.length;
        for (int i = 0; i < iterations; i++){
            System.out.println(arr[i].getData());            
        }

    }

    public Node remake (Node[] preorder , Node [] inorder){
        Node head = null;
        Node start = null;
        int k = inorder.length - 1;
        int n = preorder.length - 1;
       
        //this.printArray(preorder);
        //this.printArray(inorder);
      
        for (int i = 0; i < preorder.length; i ++){
            Stack <Node> right_inorder = new Stack <Node> ();
            Stack <Node> right_preorder = new Stack <Node> ();

            if (head == null){
                head = preorder[i];
                start = preorder[i];
                while (inorder[n] != preorder[i]){
                    right_preorder.push(preorder[k]);
                    right_inorder.push(inorder[n]);
                    k--;
                    n--;
                }
                n--;            
               
                if (right_inorder.empty() == false){
                    Node[] arr_inorder = this.convertNodeArray(right_inorder);
                    Node[] arr_preorder = this.convertNodeArray(right_preorder);
                    preorder[i].setRight(this.remake(arr_preorder, arr_inorder));
                }
            } else {
                head.setLeft(preorder[i]);
                head = head.getLeft();
         
                while (inorder[n] != preorder[i]){
                    right_preorder.push(preorder[k]);
                    right_inorder.push(inorder[n]);              
                    k--;
                    n--;
                }
                n--;

                if (right_inorder.empty() == false){
                    Node[] arr_inorder = this.convertNodeArray(right_inorder);
                    Node[] arr_preorder = this.convertNodeArray(right_preorder);
                    preorder[i].setRight(this.remake(arr_preorder, arr_inorder));
                }
            }

            if (preorder[i] == inorder[0]){
                break;
            }            
  
        }

        return start;
    }
    void recursive (Node head) {

        if (head == null){
            return;
        }
        System.out.println(head.getData());   //visiting
        
        this.recursive(head.getLeft());  
        this.recursive(head.getRight());

    }
    void recursive_inord (Node head) {
      
        if (head == null){
            return;
        }
        
        this.recursive_inord(head.getLeft());
     
        System.out.println(head.getData());   //visiting
   
        this.recursive_inord(head.getRight());

    }

    void iterative (Node head){
        Stack <Node> left = new Stack<Node>();

        while (true){

            while (head.getLeft() != null){
                Node tmp = new Node(head.getData(),null, head.getRight(), null);
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




    public static void main (String args[]){
        Reconstruct reconstruct = new Reconstruct();

        Node [] preorder = new Node[10];
        Node [] inorder = new Node[10];

        preorder[0] = new Node(314, null, null, null);
        preorder[1] = new Node(6, null, null, null);
        preorder[2] = new Node(271, null, null, null);
        preorder[3] = new Node(28, null, null, null);
        preorder[4] = new Node(0, null, null, null);
        preorder[5] = new Node(561, null, null, null);
        preorder[6] = new Node(3, null, null, null);
        preorder[7] = new Node(17, null, null, null);
        preorder[8] = new Node(7, null, null, null);
        preorder[9] = new Node(1, null, null, null);


        inorder [0] = preorder[3];
        inorder [1] = preorder[2];
        inorder [2] = preorder[4];
        inorder [3] = preorder[1];
        inorder [4] = preorder[5];
        inorder [5] = preorder[7];
        inorder [6] = preorder[6];
        inorder [7] = preorder[0];
        inorder [8] = preorder[8];
        inorder [9] = preorder[9];
        
        Node result = reconstruct.remake(preorder, inorder);

        /*
        System.out.println(result.getLeft().getData());
        System.out.println(result.getLeft().getLeft().getData());
        System.out.println(result.getLeft().getLeft().getRight().getData());
        System.out.println(result.getLeft().getLeft().getLeft().getData());
        */
       //reconstruct.recursive(result);
        reconstruct.recursive_inord(result);
       //reconstruct.iterative(result);
    }


}
/*
   Solution 2 : 

               1) set head as beginning of preorder array;
               2) get index of head in the inorder array, prehash to search faster; 
               3) calculate size of right and size of left;
               4) if left more than 0, recurse left with preorder array (start +1, stop - size_right), and in order_array (start, head_index -1)
               5) if right more than 0, recurse right with preorder array (stop - size_right, stop ) and in order array (head_index + 1, stop)


               Space Complexity O(H + N), using dictionary/hashmap to do mapping;
               Time Complexity O(2N), first to hash, then to construct.

           
*/

import java.util.HashMap;

class Node {

    public Integer data;
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




public class ReconstructV2 {


    public Node reconstruct (Node [] preorder, Node[] inorder, int start_pre, int stop_pre, int start_in, int stop_in, HashMap<Node, Integer> hash) {

        Node new_head = preorder[start_pre];

        int head_index = (int) hash.get(new_head);
       
        int size_right = stop_in - head_index;

        int size_left = head_index - start_in;
       

        if (size_left > 0){
            Node left = this.reconstruct(preorder, inorder, start_pre + 1, stop_pre - size_right, start_in, head_index - 1, hash);
            new_head.setLeft(left);
        }

        if (size_right > 0){            
            Node right = this.reconstruct(preorder, inorder, stop_pre - size_right + 1, stop_pre, head_index + 1, stop_in, hash);            
            new_head.setRight(right);
        }

        return new_head;
        

    }
    void recursive_inord (Node head) {
      
        if (head == null){
            return;
        }
        
        this.recursive_inord(head.getLeft());
     
        System.out.println(head.getData());   //visiting
   
        this.recursive_inord(head.getRight());

    }

    HashMap <Node, Integer> construct (Node [] inorder){
        HashMap<Node, Integer> result = new HashMap<Node, Integer> ();
        for (int i = 0; i < inorder.length; i++){
            result.put(inorder[i], i);
        }
        return result;
    }

    public static void main (String args[]){

        ReconstructV2 reconstruct = new ReconstructV2();

        Node [] preorder = new Node[10];
        Node [] inorder = new Node[10];

        preorder[0] = new Node(314, null, null);
        preorder[1] = new Node(6, null, null);
        preorder[2] = new Node(271, null, null);
        preorder[3] = new Node(28, null, null);
        preorder[4] = new Node(0, null, null);
        preorder[5] = new Node(561, null, null);
        preorder[6] = new Node(3, null, null);
        preorder[7] = new Node(17, null, null);
        preorder[8] = new Node(7, null, null);
        preorder[9] = new Node(1, null, null);


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

        HashMap<Node, Integer> hash = reconstruct.construct(inorder);

        Node result = reconstruct.reconstruct (preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, hash);

        reconstruct.recursive_inord(result);
        

    }
}
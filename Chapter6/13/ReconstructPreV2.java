/*Problem: given an array with processing order of pre-order, where nulls are
encountered nulls, reconstruct the array

    Solution: using recursive algorigthm similar to the one to print pre-order;
              Instead of recurse, set left(), and set right() to the recurse;
              also keep a public integer where the poitner is;
              Time complexity O(N), space O(H);


      Variant: postorder :
                preprocess the array, swap nulls,e.g. A , null,null to null, null, A
                Do the same as preorder, but from poitner = length -1 
                but set right first, then set left        
*/


class Node {

    public Character data;
    protected Node left;
    protected Node right;

    public Node (){
        left = null;
        right = null;
        data = 0;
    }

    public Node (Character d, Node l, Node r){
        data = d;
        left = l;
        right = r;
     
    }

    public void setData(Character d){
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

class HeadNode {
    public Node head;
    public int lowest; 

    public HeadNode(){
        head = null;
        lowest = -1;
    }
}



public class ReconstructPreV2 {
    public int pointer;
    
    public Node reconstruct (Node [] preorder){

        pointer = 0;
        return this.helper (preorder);       
    
    }

    public Node helper (Node[] preorder){

        Node head = preorder[pointer];
        pointer ++;
        
        if (head == null){
            return null;
        }

        head.setLeft(this.helper(preorder));
        head.setRight(this.helper(preorder));

        return head;
    }




    public Node reconstructPost (Node [] postorder){

        pointer = postorder.length -1;

        Node [] processed = this.preprocess(postorder);

        return this.postOrder(processed);       
    
    }

    public Node[] preprocess (Node [] postorder){

        int null_to_swap = -1;

        for (int i = postorder.length - 1; i >=0 ; i--){
            if (postorder[i] == null && null_to_swap == -1){        
                null_to_swap = i;
            }

            if (postorder[i] != null && null_to_swap != -1){
                postorder[null_to_swap] = postorder[i];
                postorder[i] = null;
                null_to_swap = -1;
            }
        }

        return postorder;
    }

    public Node postOrder (Node [] postorder){
        Node head = postorder[pointer];
        pointer --;

        /*
        System.out.println(pointer);

        if (head == null){
            System.out.println("null");
        } else {
            System.out.println((char) head.getData());
        }
                */
        if (head == null){
            return null;
        }

        head.setRight(this.postOrder(postorder));
        head.setLeft(this.postOrder(postorder));

        return head;
    }
    void post_recurse (Node head) {

        if (head == null){
            return;
        }     
        
        this.post_recurse(head.getLeft());  
        this.post_recurse(head.getRight());

        System.out.println((char) head.getData());
    }


    void recursive (Node head) {

        if (head == null){
            return;
        }
        System.out.println((char) head.getData());   //visiting
        
        this.recursive(head.getLeft());  
        this.recursive(head.getRight());

    }

    


    public static void main (String args[]){


        Node [] preorder = new Node[19];

        preorder[0] = new Node('H', null, null);
        preorder[1] = new Node('B', null, null);
        preorder[2] = new Node('F', null, null);
        preorder[3] = null;
        preorder[4] = null;
        preorder[5] = new Node('E', null, null);
        preorder[6] = new Node('A', null, null);
        preorder[7] = null;
        preorder[8] = null;
        preorder[9] = null;
        preorder[10] = new Node('C', null, null);
        preorder[11] = null;
        preorder[12] = new Node('D', null, null);
        preorder[13] = null;
        preorder[14] = new Node('G', null, null);
        preorder[15] = new Node('I', null, null);
        preorder[16] = null;
        preorder[17] = null;
        preorder[18] = null;
          
        int pointer = preorder.length - 1;

        ReconstructPreV2 pre = new ReconstructPreV2();

        pointer = 0;
        Node head = pre.reconstruct(preorder);

      //  System.out.println((char) head.getLeft().getRight().getData());

       // pre.recursive(head);




        Node [] postorder = new Node[11];
    
        postorder[0] = new Node('D', null, null);
        postorder[1] = null;
        postorder[2] = null;     
        postorder[3] = new Node('E', null, null); 
        postorder[4] = null;
        postorder[5] = null;
        postorder[6] = new Node('B', null, null);
        postorder[7] = new Node('C', null, null);  
        postorder[8] = null;
        postorder[9] = null; 
        postorder[10] = new Node('A', null, null);

        Node result_post = pre.reconstructPost(postorder);

        System.out.println((char) result_post.getData());
        //System.out.println((char) result_post.getLeft().getData());
 
       pre.post_recurse(result_post);
    }


}

/* Problem :
    Can you reconstruct a unique BST from preorder, inorder, postorder traversal data
    alone?
    for inorder: NO,
    preorder yes,
    postorder yes,

    One condition: all numbers must be unique:

    e.g      285       preorder is same as if  285
             /                                 /  \   
            273                             273     285               
                \
                285

       for this reason, usually implementations of bst,
                either use counter (or linked list for same value)
                or go to one side;
                
     There are 5 different BSTs possible for 1,2,3 keys (all have same inorder traversals);
     
     e.g.  1          2            3        1           3
            \        / \          /          \          /
            2       1   3        2           3         1
             \                  /            /          \
              3                 1           2            2   
Preorder: 1,2,3    2,1,3      3,1,2       1,3,2         3,1,2
Postorder:3,2,1    1,3,2      1,2,3,      2,3,1         2,1,3



1st solution:
            Complexity nlogn , space o (H);


              */





import java.util.Stack;

public class Reconstruct {

 
    public void postorder (Node root){
        if (root == null){
            return;
        } 

        this.postorder(root.left);
        this.postorder(root.right);
        
        System.out.println(root.data);

    }

    public void preorder (Node root){
        if (root == null){
            return;
        } 
        System.out.println(root.data);
        this.preorder(root.left);
        this.preorder(root.right);       
      

    }

    public int pos;

    public Node reconstructPreV2 (int [] arr, int max){
        Node head = new Node(arr[pos]);
        pos ++;
        if (pos < arr.length){

            if (arr[pos] < head.data){ //head processing this pos is different than
                head.left = reconstructPreV2(arr, head.data);
            } else {
                if (arr[pos] < max){ // this pos, but max stays the same;
                    head.right = reconstructPreV2(arr, max);
                }    
            }
        }       

        return head;
    }


    public Node recursePost (int [] arr, int max){

        Node head = new Node(arr[pos]);
        pos --;
        if (pos >= 0){
            if (arr[pos] > head.data){ //head processing this pos is different than
                head.right = recursePost(arr, head.data);                
            } else {
                if (arr[pos] < max){ //this one at a later time
                    head.left = recursePost(arr, max);
                }                
            }
        }       

        return head;


    }

    public Node reconstructPost (int [] arr){
        int max = 2147483647; //max
        pos = arr.length -1;
        return this.recursePost(arr, max);

    }

    public Node reconstructPre (int[] arr, int start, int end){
        Node head = new Node (arr[start]);

        if (end - start == 0){
            return head;
        }

        int left_size = 0;

        for (int i = start + 1; i <= end; i ++){
            
            if (arr[i] > head.data){
                break;
            }

            left_size ++;
        }

        head.left = null;

        if (left_size > 0){
            head.left = this.reconstructPre(arr, start + 1, start + left_size);
        }

        head.right = null;
        if (end - start - left_size > 0 ){
            head.right = this.reconstructPre(arr, start + left_size + 1, end);
        }


        return head;
    }


    public static void main (String args[]){
        Node head1 = new Node(1);
        head1.right = new Node(2);
        head1.right.right = new Node (3);

        Node head2 = new Node (2);
        head2.left = new Node (1);
        head2.right = new Node (3);

        Node head3 = new Node (3);
        head3.left = new Node(2);
        head3.left.left = new Node(1);

        Node head4 = new Node (1);
        head4.right = new Node (3);
        head4.right.left = new Node(2);

        Node head5 = new Node (3);
        head5.left = new Node (1);
        head5.left.right = new Node(2);

        Reconstruct reconstruct = new Reconstruct();
        

      //  reconstruct.preorder(head3);
        int[] preorder1 = { 1, 2, 3};
         
        Node BST = reconstruct.reconstructPre(preorder1, 0, preorder1.length -1);
        /*
        System.out.println(BST.data);
        System.out.println(BST.right.data);
        System.out.println(BST.right.right.data);

        //
        int biggest = 2147483647;
        Node BST2 = reconstruct.reconstructPreV2(preorder1, biggest);

       reconstruct.preorder(BST2);
        */
       
      
        int[] postorder1 = { 1, 3, 2};
        Node BST3 = reconstruct.reconstructPost(postorder1);        
        reconstruct.postorder(BST3);
    }


}












class Node {

    public int data;

    public Node left;

    public Node right;


    public Node(int d){
        left = null;
        right = null;
        data = d;
    }
}

class BinarySearchTree {

    public Node head;

    public BinarySearchTree (){
        head = null;
    }

    public void insert (int d){
        Node ins = new Node(d);

        if (head == null){
            head = ins;
            return;
        }

        Node ptr = head;
        while(true){
            if (d > ptr.data){
                if(ptr.right != null){
                    ptr = ptr.right;
                } else {
                    ptr.right = ins;
                    break;
                }
            } else {
                if (ptr.left != null){
                    ptr = ptr.left;
                } else {
                    ptr.left = ins;
                    break;
                }
            }
        }
        
    }

    public Node find (int d){

        Node ptr = head;
        while (ptr != null){
            if (d == ptr.data){
                return ptr;
            } else if (d < ptr.data){
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
        }

        return null;
    }

    

    //preorder traverse;
    public void display (Node head) {
        if (head == null){
            return;
        }
        this.display(head.left);
        System.out.print(head.data);
        this.display(head.right);


    }

}
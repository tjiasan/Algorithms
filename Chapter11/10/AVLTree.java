/* Problem:
        Design an efficent insertion/deletion for a BST
        Assume that insertions maintain inqueness property;

    Solution:
     see below
    

*/        

import java.util.Stack;
import java.util.LinkedList;

class Node {

    public int key;

    public Node left;

    public Node right;

    public int height;

    public Node(int d){
        left = null;
        right = null;
        key = d;
        height = 0;
    }
}


class BinarySearchTree {

    public Node root;

    public BinarySearchTree (){
        root = null;
    }

     // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    
    // A utility function to get height of the tree
    int height(Node N) {
        if (N == null)
            return 0;
 
        return N.height;
    }
 
    // A utility function to right rotate subtree rooted with y
   //       A            B
  //      /             / \
  //     B                 A
  //   /  \                / 
  //       D              D  
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
 
        // Perform rotation
        x.right = y;
        y.left = T2;
 
        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        
        // Return new root
        return x;
    }
 
    // A utility function to left rotate subtree rooted with x
  //       A               B
  //        \             / \
  //        B            A
  //       / \            \
  //      D                 D  
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
 
        // Perform rotation
        y.left = x;
        x.right = T2;
 
        //  Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
       
        // Return new root
        return y;
    }
 
    // Get Balance factor of node N
    int getBalance(Node N) {
        if (N == null) {
            return 0;
        }            
        
        return height(N.left) - height(N.right);
    }

    Node insert(Node node, int key) {
 
        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return (new Node(key));
 
        if (key < node.key){
            node.left = insert(node.left, key);
        }            
        else if (key > node.key) {
            node.right = insert(node.right, key);
        }            
        else { // Duplicate keys not allowed
            return node;
        }            
 
        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left),
                              height(node.right));
 
        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced , left - right*/
        int balance = getBalance(node);
 
        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case, left> right, key inserted left in left child
        if (balance > 1 && key < node.left.key){
            return rightRotate(node);
        }           
 
        // Right Right Case, right> left, key inserted in right of right child;
        if (balance < -1 && key > node.right.key){
            return leftRotate(node);
        }               
 
        // Left Right Case, left > right, key inserted right of left child;
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
 
        // Right Left Case, right > left, key inserted right of left child;
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
 
        /* return the (unchanged) node pointer */
        root = node;
        return node;
    }

    public Node find (int d){

        Node ptr = root;
        while (ptr != null){
            if (d == ptr.key){
                return ptr;
            } else if (d < ptr.key){
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
        }

        return null;
    }

    public void delete (int d){
        Stack<Node> updates = new Stack<Node>();
        Node delete = root;
        Node parent = null;

      
        while (delete != null){
            if (d == delete.key){
                break;
            } else if (d < delete.key){                
                parent = delete;
                updates.push(parent);
                delete = delete.left;
            } else {              
                parent = delete;
                updates.push(parent);
                delete = delete.right;
            }
        }


        if (delete == null){
            return;
        }        

        if (delete.left == null && delete.right == null){
            if (parent != null){
                if (delete == parent.left){
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else {
                root = null;
                return;
            }
        } else if (delete.left == null || delete.right == null){
            Node successor;

            if (delete.left == null){
                successor = delete.right;
            } else {
                successor = delete.left;
            }

            if (parent != null){
                if (parent.right == delete){
                    parent.right = successor;
                } else {
                    parent.left = successor;
                }
            } else {
                root = successor;
                return;
            }
        } else {
            //find successor, right subtree minimum
            LinkedList<Node> suc_update = new LinkedList<Node>();

            Node successor = delete.right;
            Node successor_parent = delete;
            while (successor.left != null){
                suc_update.addLast(successor);
                successor_parent = successor;
                successor = successor.left;
            }

            successor_parent.left = null; //deleting the min to swap;

            //relink  right children, to parent's left
            //because it's still less than successor parent, more than min
            if (successor.right != null){
                successor_parent.left = successor.right;
            } 

            if (parent != null){
                if (parent.right == delete){
                    parent.right = successor;            
                } else {
                    parent.left = successor;
                }

            } else {
                root = successor;
            }

            successor.right = delete.right;
            successor.left = delete.left;

            updates.push(successor);

            while(suc_update.isEmpty() == false){
                updates.push(suc_update.removeFirst());
            }
        }
 

        while(updates.isEmpty() == false){

            Node update = updates.pop();
    
            update.height = this.max(this.height(update.left), this.height(update.right)) + 1;

            int balance = this.getBalance(update);
   
  
             // more on the right
            if (balance < -1){
                if (this.height(update.right.right) < this.height(update.right.left)){
                    this.rightRotate(update.right);
                                     
                }
                root =  this.leftRotate(update);
               
            }

            //more on the left
            if (d == 8){
                System.out.println(balance);
            }
            if (balance > 1){
                if (this.height(update.left.left) < this.height(update.left.right)){                    
                    this.leftRotate(update.left);                
                }                
                root = this.rightRotate(update);
             
            }            
        }

        
    }

    //preorder traverse;
    public void display (Node root) {
        if (root == null){
            return;
        }
        this.display(root.left);
        System.out.print(root.key);
        this.display(root.right);
    }



}


public class AVLTree {



    public static void main (String args[]){

        BinarySearchTree bin = new BinarySearchTree();

         bin.root = new Node(3);      
         bin.insert(bin.root, 4);
         bin.insert(bin.root, 5);
         bin.insert(bin.root, 6);
         bin.insert(bin.root, 7);
         bin.insert(bin.root, 8);
         bin.insert(bin.root, 9);

        //System.out.println(bin.root.key); // 6
        //System.out.println(bin.root.right.right.right.key); 9
        //  6
        //   \
        //    7-8-9
        bin.delete(7);
        bin.delete(8);
        // 6-9
        // right rotate = root becomes right child
        // left rotate  = root becomes left child

        //   4
        //    \
        //     6 
        //    /  \
        //    5   9
        System.out.println(bin.root.key);
        System.out.println(bin.root.right.key);
        System.out.println(bin.root.right.right.key);
        System.out.println(bin.root.right.left.key);
       
       
        
    
         
     
    }


}



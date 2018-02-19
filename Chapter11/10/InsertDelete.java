/* Problem:
        Design an efficent insertion/deletion for a BST
        Assume that insertions maintain inqueness property;

    Solution:
        already solved with my implementation:

    

*/        



class Node {

    public int data;

    public Node left;

    public Node right;

    public int height;

    public Node(int d){
        left = null;
        right = null;
        data = d;
        height = 0;
    }
}


class BinarySearchTree {

    public Node head;

    public BinarySearchTree (){
        head = null;
    }

    public boolean insert (int d){
        Node ins = new Node(d);
      
        if (head == null){
            head = ins;
            return true;
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
            } else if (d == ptr.data){
                return false;
            }             
            else {
                if (ptr.left != null){
                    ptr = ptr.left;
                } else {
                    ptr.left = ins;
                    break;
                }
            }
        }

        return true;
        
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

    public void delete (int d){

        Node delete = head;
        Node parent = null;

        while (delete != null){
            if (d == delete.data){
                break;
            } else if(d < delete.data){
                parent = delete;
                delete = delete.left;
            } else {
                parent = delete;
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
                head = null;
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
                head = successor;
            }
        } else {
            //find successor, right subtree minimum
            Node successor = delete.right;
            Node successor_parent = delete;
            while (successor.left != null){
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
                head = successor;
            }

            successor.right = delete.right;
            successor.left = delete.left;

        }
        
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


public class InsertionDelete {



    public static void main (String args[]){



    }


}



/*

    Problem: Given a bst and a key, find the next
            sorted order in the BST (i.e. next inordertraversal)

    Solution:
            Time O(H), Space O(1);

            In an inorder traversal, 
                the next Node is the leftmost of the right children,
                in a BST, this is a sorted order

            But if this is null, then it's one of the parents!
            
            
            Take advantage of the BST property;
            we know that the next sequence is greater than d;

            going from root down,
            if we go left, the parent is greater than or equal to D,
            if we go right, it's less than D, can't possibly be one of the answers,

            each successive going down, if we record parent
            when we go left, it's getting closer to D, but not D;

            so if there is no children, answer is recorded parent;
            else, go down right subtree, and go all the way left;


            Variant: with duplicate keys,
                    find the first appearance of the int key given

                    e.g. 
                    108
                   / 
                  108
                    \ 
                      108
                      
                      returns the second one first;


               solution: go down subtree as if searching;
               
                        when encounter 108;
                        go down left while left is = 108;
                        
           */  

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

    public void delete (int d){

        Node delete = head;
        Node parent = null;

        while (true){
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
            //find successor
            Node successor = delete.right;
            Node successor_parent = delete;
            while (successor.left != null){
                successor_parent = successor;
                successor = successor.left;
            }

            if (successor.right != null){
                successor_parent.right = successor.right;
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


public class FindNext {

    public int get(Node root, int d){
        int result = root.data;
        Node ptr = root;
     
        //fake insert method
        while (ptr != null){
            if (ptr.data == d){
                break;
            } else if (d > ptr.data){
                ptr = ptr.right; 

            } else {
                result = ptr.data; //parent
                ptr = ptr.left;              
            }
        }

        // result is leftmost of right subtree, if return the parent one;
        if (ptr.right != null){
            ptr = ptr.right;
            result = ptr.data;
            while(ptr.left != null){
                ptr = ptr.left;
                result = ptr.data;
            }
        }


        return result;
    }

    public static void main (String args[]){

        FindNext findNext = new FindNext();

        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(19);
        tree.insert(7);
        tree.insert(43);
        tree.insert(3);
        tree.insert(2);
        tree.insert(5);
        tree.insert(11);
        tree.insert(17);
        tree.insert(13);

        tree.insert(23);
        tree.insert(37);
        tree.insert(29);
        tree.insert(31);
        tree.insert(41);
        tree.insert(47);
        tree.insert(53);


        Node root = tree.head;

        int next = findNext.get(root, 13);
        System.out.println(next);
    }


}
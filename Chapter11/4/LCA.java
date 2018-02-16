/* Problem : Find the lowest common ancestor
            Assume all numbers are distinct;

    Solution:
            
        let a be int1, b int2
        4 case studies:
            if root is a or b, lca is root;
            if a and b is less than key, it's in the left subtree
            if a and b is greater than key, it's in the right subtree
            if a is less than key, and b is more than key, root is lca;

            so in scenario 2 or 3, go down left and right subtree, 
            with pointer;



            */  

public class LCA {

    public int find (Node root, int a, int b){

        Node ptr = root;

        while (true){
            if (ptr.data == a || ptr.data == b){
                return ptr.data;
            } else if (a > ptr.data && b > ptr.data){
                ptr = ptr.right;
            } else if (a <= ptr.data && b <= ptr.data){
                ptr = ptr.left;
            } else {
                break;
            }
        }

        return ptr.data;

    }

    public static void main (String args[]){

        LCA lca = new LCA ();

        
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(19);

        tree.insert(7);  //left

        tree.insert(3); //lc1

        tree.insert(11); //lc2
        tree.insert(17); //right
        //lca = 7  

        Node root = tree.head;

        int result = lca.find(root, 17, 3);
        System.out.println(result);
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
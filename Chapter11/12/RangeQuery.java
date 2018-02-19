/*
    Problem: Finding restaurants in nearest to coordinate X,Y

    can be done with two BSTs,

    or sorted lists
    and search cor all in range of 
     X - D to X +D
     Y - D to Y + D

     and return the intersection;

     given a BST, 
     find all values in a given range;

     Other approaches are quadrees (four children tree to represent quadrants )and k-d trees



     Solution:
                find LCA, (where it starts to diverge)

                use a linked list for result:

                  Recurse inorder traversal backwards
                  for LCA.left
                    recursion algo :
                        if less than low,
                        return early else addFirst to linkedlist

                 add LCA to linkedlist
                 
                 recurse inorder traversal forwards for LCA.right
                 for lca.right:
                    recursion algo:

                        if higher than low,
                        return early else addLast to linkedlist;

                 convert linkedlist to array;       
                        

                 O(H) space and  O (K + d) time;

                 H is height of LCA tree;
                 where K is amout of elements ;
                 d is distance from root to LCA;



*/
import java.util.Stack;
import java.util.Arrays;
import java.util.LinkedList;

public class RangeQuery {

    public int[] find(Node root, int low, int high){

        LinkedList<Integer> result = new LinkedList<Integer>();

        Stack <Node> call_stack = new Stack<Node>();

        //find LCA

        Node ptr = root;

        while(true){
            if (ptr == null){
                int[] res = new int[0];
                return res;
            }
            if (low < ptr.data && high < ptr.data){
                ptr = ptr.left;
            } else if (low > ptr.data && high > ptr.data){
                ptr = ptr.right;
            } else {
                break;
            }
        }
        
       
        this.recurse_prev (ptr.left, result, low);
        result.addLast (ptr.data);

        this.recurse_next (ptr.right, result, high);
      
        int [] fin_result = new int[result.size()];

        for (int i = 0; i < fin_result.length; i++){
            fin_result[i] = result.removeFirst();
        }

        return fin_result;

    }

    public void recurse_next (Node head, LinkedList<Integer> result, int high){
        if (head == null){
            return;
        }

        this.recurse_next (head.left, result, high);

        if (head.data > high){
            return;
        } else {
            //System.out.println(head.data);
            result.addLast(head.data);
        }

        this.recurse_next (head.right, result, high);
    }

    public void recurse_prev (Node head, LinkedList<Integer> result, int low){
        if (head == null){
            return;
        }

        this.recurse_prev(head.right, result, low);

        if (head.data < low){
            return;
        } else {
            //System.out.println(head.data);
            result.addFirst(head.data);
        }       
        
        this.recurse_prev(head.left, result, low);
    }


    public static void main(String args[]){

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

        RangeQuery rangeQuery = new RangeQuery();

        int[] points = rangeQuery.find(root, 12, 31);



        System.out.println(Arrays.toString(points));
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
/* Problem : Return an array of the K largest 
                elements in a binary search tree

                e.g. for k = 3 
                return { 53, 47, 43}


    Solution: 1) Do an in order traversal backwards :
                    order(right);
                    print & record;
                    order (left);
                    Space O(H) + log(h + k) time;


                2) Use a double linked list  of k +1 size;

                    record and trim linked list as you go:

                        go all the way right;

                        if null, 
                         trim 1 linked list (just added same node)   
                        add to result linked list,

                        while (going left is null){
                            ptr = removelast;
                            add to result; (biggest possible after max);

                        }
                        ptr go left;


                    Space O(k) + log(h + k) time;    
                        


*/
import java.util.Arrays;


import java.util.LinkedList;

public class KLargest{

    public int[] find (Node root, int k){
        

        LinkedList<Node> last = new LinkedList<Node>();
        LinkedList<Integer> result = new LinkedList<Integer>();
        Node ptr = root; 

        int counter = 0;
        while (counter <= k){

            last.addLast(ptr);

            if (last.size() > k + 1){
                last.removeFirst();
            }

            if (ptr.right != null){
                ptr = ptr.right;
            } else {
                last.removeLast(); //remove duplicate addition
                result.addLast(ptr.data);
                counter ++;
                while (ptr.left == null){
                    ptr = last.removeLast();
                    result.addLast(ptr.data);
                    counter ++;
                }
                ptr = ptr.left;

            }


        }


        int[] result_arr = new int[k];

        for (int i = 0; i < k; i ++){
            result_arr[i] = (int) result.removeFirst();
        }



        return result_arr;
    }

    public static void main (String args[]){

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



        KLargest largest = new KLargest();

        int[] result = largest.find(root, 10);

        System.out.println(Arrays.toString(result));


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
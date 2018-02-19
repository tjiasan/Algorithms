/*
    Problem: Given 3 nodes and input {ancestor, descendant, middle},

    give a true/false response whether 
            ancestor is not equal to middle, 
                and has a path to middle from it,


           same for proper descendant   
           
     Solution:
          if assigned anceestor and descendant:
               Start from ancestor,
                search for middle
                
                start from middle,
                search for descendant;

                if either is false, return false;


       if not assigned,
            do a path check from 
            a and b to middle in tandem (at the same time);

            if both null, return false
            if one is true, break;

            set descendant as the other
                and do search from the middle to descendant;

                Complexity O(H) for both;
       
*/

public class ProperAncestor {

    public boolean is_proper(Node ancestor, Node descendant, Node middle){

        if (ancestor.data == middle.data || descendant.data == middle.data){
            return false;
        }

        //find middle 
        Node ptr = ancestor;
        while (ptr != null && ptr.data != middle.data){         
            if (middle.data < ptr.data){
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
        }

        if (ptr == null){
            return false;
        }

        ptr = middle;

        while (ptr != null && ptr.data != descendant.data){          

            if (descendant.data < ptr.data){
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
        }
        if (ptr == null){
            return false;
        }
        return true;

    }

    boolean is_proper_unk (Node a, Node b, Node middle){

        Node ptr1 = a;
        Node ptr2 = b;
        int ancestor = 0;
        while (ptr1 != null || ptr2 != null){
            if (ptr1 != null){
                if (ptr1.data == middle.data){
                    ancestor = 1;
                    break;
                }
                if (middle.data < ptr1.data){
                    ptr1 = ptr1.left;
                } else {
                    ptr1 = ptr1.right;
                }
            }

            if (ptr2 != null){
                if (ptr2.data == middle.data){
                    ancestor = 2;
                    break;
                }
                if (middle.data < ptr2.data){
                    ptr2 = ptr2.left;
                } else {
                    ptr2 = ptr2.right;
                }
            }
        }
        if (ancestor == 0){
            return false;
        } else if (ancestor == 1){
            return this.is_descendant(b, middle);
        } else {
            return this.is_descendant(a, middle);
        } 
    }

    public boolean is_descendant(Node descendant, Node middle){
        Node ptr = middle;

        while (ptr != null && ptr.data != descendant.data){          
    
            if (descendant.data < ptr.data){
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
        }
        if (ptr == null){
            return false;
        }

        return true;

    }

    public static void main (String args[]){


        ProperAncestor anc = new ProperAncestor();
        Node root = new Node(5);

        Node right = new Node (9);
        Node right_c1 = new Node (7);
        Node right_c2 = new Node (10);
        Node right_c1_c2 = new Node (8);


        root.right = right;
        right.right = right_c2;
        right.left = right_c1;

        right_c1.right = right_c1_c2;


        boolean proper = anc.is_proper(root, right_c2, right);
        System.out.println(proper);
        boolean proper1 = anc.is_proper(root, right, right_c1);
        System.out.println(proper1);


        boolean proper2 =  anc.is_proper_unk(root, right_c2, right);
        System.out.println(proper2);

        boolean proper3 = anc.is_proper(root, right, right_c2);
        System.out.println(proper3);
    }


}

class Node {
    Node left;
    Node right;
    int data;

    public Node (int d){
        data = d;
    }
}
/* Problem:       314
                    /  \
                   6    7
                  / \     \
               271  561    1
               / \    \
             28   0    3
                       /
                      17       

         Compute the exterior: 314, 6, 271, 0, 17, 1, 7   
         
     Solution: do 4 traversals: ()
            1) traverse left, get root to leaf for all left in a Queue,
                while adding, Any right branch to a stack
            2) Process  Stack, get all the leafs, andd add to left queue,
            3) traverse right, add all to a right stack, while adding any left branch
                to a  right_queue
            4)  Process the right_queue, adding all leafs to the left queue,
            5) Add right stack to  left queue
            6) trasnform to array and return;
            
       Complexity O(N) time, space O (H), same as traversing recursively, but this one is
       a combo of iterative and recursive; this is because space for result isn't counted,
       length of leaf stack/queue is the same as result, so no extra space used;     


       OR lazier way and less efficient:
       1) go left until null to get left side;

       2) do a pre-order traversal to get all leafs (as previous question) to get all leafs (trim first and last)

       3) go right until null to get right side;

       4) combine;
       Complexity O(N + H), space O(H);
   
*/

import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

class Node {

    public int data;
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


public class ExteriorBinary {

   
    public Node[] compute (Node head){

        Queue<Node> left = new LinkedList<Node>();

        Stack<Node> left_leaf = new Stack<Node>();
        Queue<Node> right_leaf = new LinkedList<Node>();

        Stack<Node> right = new Stack<Node>();

        left.add(head);

        Node start = head;

        while (start.getLeft() != null){
            start = start.getLeft();
            left.add(start);
            if (start.getRight()!= null){
                left_leaf.push(start.getRight());
            }
        }

        start = head;
        while (start.getRight() != null){
            start = start.getRight();
            right.push(start);
            if (start.getLeft() != null){
                right_leaf.add(start.getLeft());
            }
        }

        while (left_leaf.empty() == false){
            this.getLeaf(left_leaf.pop(), left);
        }

        while (right_leaf.isEmpty() == false){
            this.getLeaf(right_leaf.remove(), left);
        }

        while(right.empty() == false){
            left.add(right.pop());
        }

        Node [] result = new Node[left.size()];

        int iterations = left.size();
        for (int i = 0; i < iterations; i++ ){
            result[i] = left.remove();
            System.out.println(result[i].getData());
        }


        return result;

        
    }

    public void getLeaf(Node head, Queue<Node> left){
        if (head == null){
            return;
        }

        if (head.getLeft() == null && head.getRight() == null){
            left.add(head);
        } else {
            this.getLeaf(head.getLeft(), left);
            this.getLeaf(head.getRight(), left);
        }

    }

    public static void main (String args[]){

        ExteriorBinary ext = new ExteriorBinary();
     
        Node head = new Node(314, null, null);

        Node left = new Node (6, null, null);

        Node right = new Node (7, null, null);

        Node left_child = new Node(271, null ,null);

        Node right_child = new Node(1, null, null);

        Node left_child2 = new Node(561, null,null);

        Node left_child3 = new Node(28, null,null);
        Node left_child4 = new Node(0, null,null);

        Node left_child5 = new Node(3, null,null);
        Node left_child6 = new Node(17, null,null);

        head.setLeft(left);
        head.setRight(right);
        right.setRight(right_child);
        left.setLeft(left_child);
        left.setRight(left_child2);

        left_child.setLeft(left_child3);
        left_child.setRight(left_child4);

        left_child2.setRight(left_child5);
        left_child5.setLeft(left_child6);

 
        Node[] result = ext.compute(head);
        
        
    }


}
/*
    Problem: check if a tree satisfies the BST property:
            1) left child is less than equal to parent, right child is more than parent
            2) all keys on the left subtree is less than or equal to parent, 
                all keys on the right subtree is more than parent;
    
                
    Solution: 1) Perform in order traversal, which prints out the bst in sorted order,
                 if outputs aren't sorted, then we know it's not BST,
                 O(H) space O(N) time;

                 ALT:
                    pass down range constraint when recursing/calling;
                    still O(H) space and O(N) time;

              2) Brute force, check max and min of left subtree, 
                check max min of right subtree, all the range should be
                within specs: O(N^2);
                
              3) Breadth first search:  
                 Use a queue to keep track of child nodes; 
                for each node, add range information constraints; 
                e.g. for left (set { -infitinty, parent})
                if right (set { parent + 1, infinity})
                advantage: terminates early!
                but O(N/2) space required;

                when processing, if left is > range, return false immediately;
                or if right < range;


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



public class Check{

    public Integer last = null;

    public boolean check (Node head){

        if (head == null){
            return true;
        }

        boolean left = check(head.left);
        System.out.println(head.data);
        if (last == null){
            last = head.data;
           
        } else {
            if (head.data < last){
                return false;
            }
            last = head.data;
        }
       
        boolean right = check(head.right);

        return left && right;

    }


    public static void main (String args[]){

        Check check = new Check();

        Node head = new Node(4);

        Node left = new Node (3);
        head.left = left;

        Node right = new Node (5);
        head.right = right;

        Node left_child = new Node (2);
        left.left = left_child;

        Node left_child2 = new Node(1);
        left_child.left = left_child2;

        left_child.right = new Node(3);


        System.out.println(check.check(head));


    }

}
/* Problem: Computing the kth order node in an in order traversal is easy in O(N) iterations, 
            Assuming each node contains an extra information that stores the number of nodes
            inside its subtree, compute kth node efficiently,

    Solution: Using recursive:
            if downstream left initialize to zero;
            if not null, nodes in left = total nodes in left + 1;

            if (visited + total_left >= order) traverse left,
            else, skip and add visited total_left;

            visit current node ++;

            if (visit == order){
                return
            }

            return recurse traverse right;


            Comlexity O(H) time, O(H) space
*/

class Node {

    protected Integer data;
    protected Node left;
    protected Node right;

    protected int child_nodes;

    public Node (){
        left = null;
        right = null;
        data = 0;
        child_nodes = 0;
    }

    public Node (int d, Node l, Node r, int k){
        data = d;
        left = l;
        right = r;
        child_nodes = k;
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

    public int getChild(){
        return child_nodes;
    }

    public void setChild(int k){
        child_nodes = k;
    }

}


public class KInorder {
    //order is k
    int recursive (Node head, int order, int visited) {
       
        System.out.println( "traversing: " + head.getData());

        int downstream_left = 0;
        if (head.getLeft() != null){
            downstream_left = head.getLeft().getChild() + 1;
        }
            
        if ((visited + downstream_left ) >= order) {            
            return this.recursive(head.getLeft(), order, visited); // traverse left
        }  else {
            visited += downstream_left; //skip and add nodes;
        }   

        
        visited ++; //going upstream or right     

        if (visited == order){
            return head.getData();
        }

        return this.recursive(head.getRight(), order, visited);
        
        

    }

    public static void main (String args[]){       

        KInorder inorder = new KInorder();
     
        Node head = new Node(314, null, null ,9);

        Node left = new Node (6, null, null, 6);

        Node right = new Node (7, null, null , 1);

        Node left_child = new Node(271, null ,null, 2);

        Node right_child = new Node(1, null, null, 0);

        Node left_child2 = new Node(561, null,null, 2);

        Node left_child3 = new Node(28, null,null, 0);
        Node left_child4 = new Node(0, null,null, 0);

        Node left_child5 = new Node(3, null,null, 1);
        Node left_child6 = new Node(17, null,null, 0);

        head.setLeft(left);
        head.setRight(right);
        right.setRight(right_child);
        left.setLeft(left_child);
        left.setRight(left_child2);

        left_child.setLeft(left_child3);
        left_child.setRight(left_child4);

        left_child2.setRight(left_child5);
        left_child5.setLeft(left_child6);

        int result = inorder.recursive(head, 9, 0 );

       
        System.out.println(result);

    }
}
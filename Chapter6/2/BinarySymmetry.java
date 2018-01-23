/* Problem: If a binary tree is symmetric, if you put a line through the middle of the tree,
            it should form a mirror image, with the exact same data.
            Check if a tree is symmetric;

    Solution: recursive algorithm:
                Input: node left & node right;
                
                if left.data != right.data return false;

                if (left.leftchild or right.rightchild)
                    get recursive boolean of (left.leftc, right.rightc);
                
                same for left.rightchild;

                return left boolean && right boolean;    
                
                

*/


class Node {

    protected Integer data;
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

public class BinarySymmetry {

    public boolean check_symmetry(Node left, Node right){

        if (left == null || right == null){
            return false;
        }

        if (left.getData() != right.getData()){
            return false;
        }

        boolean left_s = true;
        boolean right_s = true;

        if (left.getLeft() != null || right.getRight() != null){
            left_s = this.check_symmetry(left.getLeft(), right.getRight());
        } 

        if (left.getRight() != null || right.getLeft()!= null){
            right_s = this.check_symmetry(left.getRight(), right.getLeft());
        }

        return left_s && right_s;
    }

    public boolean is_symmetric (Node head){

        return this.check_symmetry(head.getLeft(), head.getRight());
    }

    public static void main (String args[]){

        BinarySymmetry bin = new BinarySymmetry();

        Node head = new Node();

        Node left = new Node (5, null, null);

        Node right = new Node (5, null, null);

        Node left_child = new Node(6, null ,null);

        Node right_child = new Node(6, null, null);
        
       // Node right_unsym = new Node(4, null, null);
       // right_child.setRight(right_unsym);

        head.setRight(right);
        head.setLeft(left);

        left.setLeft(left_child);

        right.setRight(right_child);


        boolean is_sym = bin.is_symmetric(head);

        System.out.println(is_sym);


    }
}
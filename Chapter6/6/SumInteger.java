
/*
    Problem: Given a binary tree with integers as nodes ,
            find whether a root to leaf path exists where the sum
            equals a given integer


    Solution: Using a similar a prroach to the last question,
            if not leaf, add current data integer, pass it down,
            
            if encounter leaf, add integer, 
            return 0 if the sum isn't there for partial sum,
            if exact sum is found, the check_integer is returned,

    variant: to return all paths, pass down LinkedList<String>
            if true, then add to public LinledList<LinkedList<String>>;
            OR concatenate nested LinkedListss        

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


public class SumInteger {

    public boolean sum (Node head, int check_sum, int partial_sum){

        partial_sum += head.getData();

        if (head.getLeft() == null && head.getRight() == null){
            if (partial_sum == check_sum){
                return true;
            }
            else {
                return false;
            }
        }

        boolean right = false;
        boolean left = false;

        if (head.getRight() != null){
            right = this.sum(head.getRight(), check_sum, partial_sum);
        }

        if (head.getLeft() != null){
            left = this.sum(head.getLeft(), check_sum, partial_sum);
        }

        return left || right;

    }

    public static void main (String args[]){       

        SumInteger srl = new SumInteger();
     
        Node head = new Node(50, null, null);

        Node left = new Node (10, null, null);

        Node right = new Node (31, null, null);

        Node left_child = new Node(5, null ,null);

        Node right_child = new Node(1, null, null);

        Node left_child2 = new Node(2, null,null);

        head.setLeft(left);
        head.setRight(right);
        right.setRight(right_child);
        left.setLeft(left_child);
        left.setRight(left_child2);


        boolean sum = srl.sum(head, 65, 0);

        System.out.println(sum);
    }
}

/*
    Problem: Given a binary tree where each path from root to leaf
    is a  binary representation of a number,
    sum all the binary representations

    e.g. root to leaf is (10001)


    Solution: Use recursion:
              pass down partial sums, starting from zero
              bitshift partial by 1, and add 1 if data is 1;

              if leafs are null, return partial sum;

              if have left, recurse left, vice versa with right;

              with java, could do global variable, and sum if leafs are null;
              but the sum of left + right should also equal sum of all paths;

              Complexity O(N) , space O(H);

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


public class SumRootLeaf {

    public int sum (Node head, int partial_sum){

        partial_sum <<= 1;

        if (head.data == 1){
            partial_sum += 1;
        }

        if (head.getLeft() == null && head.getRight() == null){
            return partial_sum;
        }

        int right = 0;
        int left = 0;

        if (head.getRight() != null){
            right = this.sum(head.getRight(), partial_sum);
        }

        if (head.getLeft() != null){
            left = this.sum(head.getLeft(), partial_sum);
        }

        return left + right;


    }

    public static void main (String args[]){       

        SumRootLeaf srl = new SumRootLeaf();
     
        Node head = new Node(1, null, null);

        Node left = new Node (0, null, null);

        Node right = new Node (1, null, null);

        Node left_child = new Node(1, null ,null);

        Node right_child = new Node(1, null, null);

        Node left_child2 = new Node(0, null,null);

        head.setLeft(left);
        head.setRight(right);
        right.setRight(right_child);
        left.setLeft(left_child);
        left.setRight(left_child2);


        int sum = srl.sum(head, 0);

        System.out.println(sum);
    }
}
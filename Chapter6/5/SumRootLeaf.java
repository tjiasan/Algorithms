
/*
    Problem: Given a binary tree where each path from root to leaf
    is a  binary representation of a number,
    sum all the binary representations

    e.g. root to leaf is (10001)


    Solution: Use recursion:
              pass down partial binaries left and right,
              bitshift partial by 1, and add 1 if data is 1;
              if leafs are null, print binary, add to global sum;

            

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
    public int fin_sum;
    public void sum (Node head, int partial_sum){

        partial_sum <<= 1;

        if (head.data == 1){
            partial_sum ^= 1;
        }

        if (head.getLeft() == null && head.getRight() == null){
            fin_sum += partial_sum;
            System.out.println(fin_sum);
        }

        if (head.getRight() != null){
            this.sum(head.getRight(), partial_sum);
        }

        if (head.getLeft() != null){
            this.sum(head.getLeft(), partial_sum);
        }


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


        srl.sum(head, head.data);

        
    }
}
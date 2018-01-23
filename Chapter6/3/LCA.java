/*
    Problem: Given two nodes, find the lowest common ancestor.
            Nodes do not have a parent field;

    Solution: 1) use recursion and O(H) space , where H is the
                number of elements in height, and each space has h character length;
                 ,to find path, store it, compare to get to node
    
              2) use recursion to sum of Nodes in subtree left and right
                left == 1 and right == 1, save node;
                
                if null return 0;
                if (found), add 1;

                return tree; O(N) time, O(N) space because it goes all the way down,
                each space has 1 character length;
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


public class LCA {

    public String path1 = "";
    public String path2 = "";
    public Node ancestor2;

    public void get_path(Node head, Node find, boolean is_first, String partial){
        if (head == find){
            if (is_first){
                path1 = partial;
            } else {
                path2 = partial;
            }
            return;
        }
        
        if (head.getRight() != null){
            this.get_path(head.getRight(), find, is_first, partial + 'R');
        }

        if (head.getLeft() != null){
            this.get_path(head.getLeft(), find, is_first, partial + 'L');
        }
        
    }

    public Node find_lca (Node head, Node first, Node second) {

        this.get_path(head, first, true, "");
        this.get_path(head, second, false, "");

        Node start = head;

        for (int i = 0; i < path1.length(); i++){
            if (path1.charAt(i) != path2.charAt(i)){
                break;
            } else {
                if (path1.charAt(i) == 'R'){
                    start = start.getRight();
                } else {
                    start = start.getLeft();
                }
            }
        }

        return start;
    }

    public int find_lca2 (Node head, Node first, Node second){
        if (head == null){
            return 0;
        }

        int left_sum = this.find_lca2(head.left, first, second);
        int right_sum = this.find_lca2(head.right, first, second);

        int total = left_sum + right_sum;

        if (right_sum == 1 && left_sum == 1){
            ancestor2 = head;
            System.out.println(ancestor2.getData());
        }
        if (head == first || head == second){
            total += 1;
            if (sum == 2){
                ancestor2 = head; // common ancestor is the node situation
            }
        }
        
        return total;
    }

    public static void main (String args[]){       

        
        LCA lca = new LCA();


        Node head = new Node(0, null, null);

        Node left = new Node (255, null, null);

        Node right = new Node (5, null, null);

        Node left_child = new Node(6, null ,null);

        Node right_child = new Node(6, null, null);

        Node left_child2 = new Node(7, null,null);

        head.setLeft(left);
        head.setRight(right);
        right.setRight(right_child);
        left.setLeft(left_child);
        left.setRight(left_child2);


        Node ancestor = lca.find_lca (head, left_child, left_child2);

        System.out.println(ancestor.getData());

        int sum = lca.find_lca2(head, left_child, left_child2);

        
    }
}
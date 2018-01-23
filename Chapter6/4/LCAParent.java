/*
    Problem : find LCA if parent field is given;

    Solution: find height of node:

             go upstream of the higher height until heights are common;
             then go simultaneously up and compare;

             Space O (1);
             Complexity O(H);

*/


class Node {

    public Integer data;
    protected Node left;
    protected Node right;
    protected Node parent;

    public Node (){
        left = null;
        right = null;
        data = 0;
        parent = null;
    }

    public Node (int d, Node l, Node r, Node p){
        data = d;
        left = l;
        right = r;
        parent = p;
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

    public void setParent(Node p){
        parent = p;
    }

    public Node getRight(){
        return right;
    }

    public Node getLeft(){
        return left;
    }
    
    public Node getParent(){
        return parent;
    }

}


public class LCAParent{

    public int getHeight(Node n){
        int height = 0;
        Node start = n;

        while (start != null){
            start = start.getParent();
            height ++;
        }
        return height;
    }


    public Node get_lca(Node head, Node one, Node two){

        int height1 = this.getHeight(one);
        int height2 = this.getHeight(two);

        while (height1 > height2){
            one = one.getParent();
            height1 --;
        } 
        
        while (height2 > height1) {
            two = two.getParent();
            height2--;
        }
     
        if (one == two){
            return one;
        } else {
            while (one != two){
                one = one.getParent();
                two = two.getParent();

                if (one == null || two == null){
                    //shouldn't happen;
                    break;
                }
            }
        }

        return one;
    }

    public static void main (String args[]){


        LCAParent lca = new LCAParent();


        Node head = new Node(211231, null, null, null);

        Node left = new Node (255, null, null, head);

        Node right = new Node (5, null, null, head);

        Node left_child = new Node(6, null ,null, left);

        Node right_child = new Node(6, null, null, right);

        Node left_child2 = new Node(7, null,null, left);

        left.setLeft(left_child);
        left.setRight(left_child2);
        
        right.setRight(right_child);

        head.setLeft(left);
        head.setRight(right);

        Node ancestor = lca.get_lca (head, left_child, left_child2);

        System.out.println(ancestor.data);
        
        Node ancestor1 = lca.get_lca (head, left_child, right_child);
        System.out.println(ancestor1.data);
    }


}


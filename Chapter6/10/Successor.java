/* Problem : compute next successor of an in order traversal given a node,
            Assume Parent field is in the node info;

   Solution: Case studies:

           Ignore all left child:

           if you are current node, and there is a right child, the successor is in the right subtree;
                    if the right child doesn't have a left subtree, it is the successor,
                    else, go down the left subtree until terminal and that is the successor,

            if no right child of current;
                go up the tree until encounter parent that is on right side of current, 
                    or current = parent.getleft()
                      P                    
                    /
                   C 
                   and that parent is successor.

                   if doesn't encounter, it's the end or terminal, go up to root;
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

    public Node (int d, Node l, Node r){
        data = d;
        left = l;
        right = r;
        parent = null;
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


public class Successor{

    public int compute (Node current){
        if (current.getRight() == null){
            Node parent = current.getParent();

            while (parent != null){
                if (parent.getLeft() == current){
                    return parent.getData(); //successor is parent
                } else {
                    
                    current = parent;
                    parent = current.getParent();
                }
            }

            return 0;// invalid;    

        }

        //ignore all left;

        current = current.getRight();

        while(current.getLeft() != null){
            current = current.getLeft();
        }

        return current.getData();


    }


    public static void main (String args[]){       

        Successor succ = new Successor();
     
        Node head = new Node(314, null, null);

        Node left = new Node (6, null, null);

        Node right = new Node (7, null, null );

        Node left_child = new Node(271, null ,null);

        Node right_child = new Node(1, null, null);

        Node left_child2 = new Node(561, null,null);

        Node left_child3 = new Node(28, null,null);
        Node left_child4 = new Node(0, null,null);

        Node left_child5 = new Node(3, null,null);
        Node left_child6 = new Node(17, null,null);

        head.setLeft(left);
        head.setRight(right);

        left.setParent(head);
        right.setParent(head);


        right.setRight(right_child);
        right_child.setParent(right);
        
        left.setLeft(left_child);
        left.setRight(left_child2);
        left_child.setParent(left);
        left_child2.setParent(left);


        left_child.setLeft(left_child3);
        left_child.setRight(left_child4);
        left_child3.setParent(left_child);
        left_child4.setParent(left_child);


        left_child2.setRight(left_child5);
        left_child5.setLeft(left_child6);
        left_child5.setParent(left_child2);
        left_child6.setParent(left_child5);


       int result = succ.compute(left_child);

       System.out.println(result); // 0 (not invalid)

       int result1 = succ.compute(left_child6);

       System.out.println(result1); //3

       int result2 = succ.compute(left_child5);

       System.out.println(result2); //314

       int result3 = succ.compute(right_child);

       System.out.println(result3); //invalid or return 0;
 
 
    }
}
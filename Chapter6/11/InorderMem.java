/*
    Problem: Traverse the binary tree with O(1) memory, given that parent field is present
/*                  314
                    /  \
                   6    7
                  / \     \
               271  561    1
               / \    \
             28   0    3
                       /
                      17          

    Solution : use successsor iteratively;

    variant : pre-order + postorder with O(1) and parent;

        pre-order: 1) if left, go left, visit,
                   2) if left null, and right not null, visit right
                   2) if both bottom are null, go up until right unvisited child; i.e. current != parent.right, and visit right

    post-order: primary: all the way left, all the way right, 
                        visit

                        ignore children, wheter null or otherwise!!
               
                     if current = parent.left;
                     go up parent, 
                     go right, if parent.right = null visit parent;
                     if parent.right not null move right,
                        then follow algo: if left move left first, else move right until both are null; visit;

                     if current = parent.right;
                     go up parent, visit
                   
                    if parent= null, done;
    

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


public class InorderMem {

    public void traverse (Node head){
        //go to first
        while (head.getLeft() != null){
            head = head.getLeft();
        }

     
        while (head != null){
            System.out.println(head.getData());
            head = this.compute(head);                
        }

    }

    public Node compute (Node current){
        if (current.getRight() == null){
            Node parent = current.getParent();

            while (parent != null){
                if (parent.getLeft() == current){
                    return parent; //successor is parent
                } else {
                    
                    current = parent;
                    parent = current.getParent();
                }
            }

            return null;// invalid;    

        }

        //ignore all left;

        current = current.getRight();

        while(current.getLeft() != null){
            current = current.getLeft();
        }

        return current;


    }
    
    void postorder (Node head) {
        if (head == null){
            return;
        }
        
        this.postorder(head.getLeft());
        this.postorder(head.getRight());    
        System.out.println(head.data);         

    }


    public static void main (String args[]){       

        InorderMem  inorderMem = new InorderMem ();
     
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

        
        inorderMem.traverse(head);
        System.out.println(" ");
        inorderMem.postorder(head);
 
    }
}



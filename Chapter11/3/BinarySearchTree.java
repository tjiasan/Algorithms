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

class BinarySearchTree {

    public Node head;

    public BinarySearchTree (){
        head = null;
    }

    public void insert (int d){
        Node ins = new Node(d);

        if (head == null){
            head = ins;
            return;
        }

        Node ptr = head;
        while(true){
            if (d > ptr.data){
                if(ptr.right != null){
                    ptr = ptr.right;
                } else {
                    ptr.right = ins;
                    break;
                }
            } else {
                if (ptr.left != null){
                    ptr = ptr.left;
                } else {
                    ptr.left = ins;
                    break;
                }
            }
        }
        
    }

    public Node find (int d){

        Node ptr = head;
        while (ptr != null){
            if (d == ptr.data){
                return ptr;
            } else if (d < ptr.data){
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
        }

        return null;
    }

    

    //preorder traverse;
    public void display (Node head) {
        if (head == null){
            return;
        }
        this.display(head.left);
        System.out.print(head.data);
        this.display(head.right);


    }

}
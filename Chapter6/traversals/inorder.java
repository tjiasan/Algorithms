public class inorder{

    void inorder (Node head) {

        if (head == null){
            return;
        }

        this.inorder(head.getLeft());
    
        System.out.println(head);
   
        this.inorder(head.getRight());

    }
}
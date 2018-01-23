

public class preorder{

    void preorder (Node head) {
        if (head == null){
            return;
        }
        //visit head
        System.out.println(head); 
        this.preorder(head.getLeft());
        this.preorder(head.getRight());

    }
}
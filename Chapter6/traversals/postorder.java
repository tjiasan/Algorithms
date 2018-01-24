import java.util.Stack;

public class postorder{

    void postorder (Node head) {
        if (head == null){
            return;
        }
        
        this.postorder(head.getLeft());
        this.postorder(head.getRight());    
        System.out.println(head);         

    }

    void postorderIter(Node head){
        Stack <Node> next = new Stack<Node> ();        

        while (next.empty() == false){

            while (head.getLeft() != null){
                next.push (new Node(head.getData(), null, head.getRight())); // no backtracking
            } // go all the way left until dead end      


            if (head.getRight() == null && head.getLeft() == null){
                System.out.println(head.getData());
                head = next.pop();
            }    //if real dead end, pop and print

            if (head.getRight()  != null){
                next.push (new Node(head.getData(), null, null));
                head = head.getRight();
            }    // re-push popped head with nulls, and go right
         
        }


   



    }
}
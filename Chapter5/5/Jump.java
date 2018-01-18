/*
    Problem: Given a linked list with another pointer called jump
            that can jump to any node, and a secondary data called
            order, Fill in the order such that jumps are enumerated
            first and then sequentially.
            Assume order is initialized with -1;

    Solution: The easiest way is to use recursion:

            Recursive solution:
                    use public variable for order b/c if fn param, it fills variables during function calls, thus both calls will have same order;
                    public int order;
                    fn (){
                        if (node & unvisited){
                            order ++;
                            this.fn(jump);
                            this.fn(next);
                        }                        
                    }

            Stack Solution: 
                push next nodes (node.getLink()) in stack while processing jump;

                if jump becoms null or encounters already set,
                set jump to the popped next node;

             Since every jump sets the next node to the stack, it won't be missed by iterative;   

*/

class Node {
    protected int data;
    protected Node link;
    protected Node jump;
    protected int order;

    /* Constructor */
    public Node ()
    {
        link = null;
        data = 0;
        order = -1;
        jump = null;
    }

    public Node (int d, Node n){
        data = d;
        link = n;
        order = -1;
        jump = null;
    }

    public void setLink (Node n){
        link = n;
    }

    public void setJump(Node n){
        jump = n;
    }

    public Node getJump(){
        return jump;
    }

    public int getOrder(){
        return order;
    }

    public void setOrder(int o){
        order = o;
    }

    public void setData (int d){
        data = d;
    }

    public Node getLink() {
        return link;
    }
    
    public int getData(){
        return data;
    }
}

class Stack {

    public Node start;
    public int size;

    public Stack () {
        size = 0;
        start = null;
    }


    public void push (int data){

        Node push = new Node(data, start);
        start = push;
    }

    public int pop (){

        int pop = start.data;
        
        start = start.getLink();

        return pop;
    }

    public void setJump (int o, int n){
        Node ptr = start;
        int i = 0;
        while (i != n){
            ptr = ptr.getLink();
            i++;
        }

        Node origin = start;
        i = 0;
        while (i != o){
            origin = origin.getLink();
            i++;
        }

        origin.setJump(ptr);
    }

    public void displayOrder(){
         Node ptr = start;
         
         while (ptr != null){
            System.out.println(ptr.getOrder());
            ptr = ptr.getLink();
         }
    } 

}

class NodeS {
    protected Node data;
    protected NodeS link;

    public NodeS (){
        data = null;        
    }

    public NodeS (Node d, NodeS l){
        data = d;
        link = l;
    }

    public NodeS getLink(){
        return link;
    }

    public Node getData(){
        return data;
    }

}


class NodeStack {

    public NodeS start;
    public int size;

    public NodeStack () {
        size = 0;
        start = null;
    }

    public void push (Node data){
        NodeS push = new NodeS(data, start);
        start = push;
        size ++;
    }

    public Node pop (){
        if (start == null){
            return null;
        }

        Node pop = start.data;
        
        start = start.getLink();
        size --;
        return pop;
    }

}



public class Jump {
    public int order;

    public Jump (){
        order = 0;
    }

    public void setFirstJumpOrder(Stack input){

        Node pointer = input.start;
        Node jump = pointer.getJump();
        NodeStack iter = new NodeStack();

        iter.push(pointer.getLink());
        int jump_order = 1;
        
        pointer.setOrder(1);
      
        while (jump != null){
            while (jump != null && jump.getOrder() == -1){
                jump_order++;
                jump.setOrder(jump_order);
                if (jump.getLink() != null){
                    iter.push(jump.getLink());
                }
                
                jump = jump.getJump();

            }
            
            jump = iter.pop();
           

        }


    }

    public void recursive (Node n){        
        if  (n != null && n.getOrder() == -1){
            order = order + 1;
            n.setOrder(order);
            this.recursive (n.getJump());
            this.recursive (n.getLink());
        }

    }

    public static void main (String args[]){
        Jump jump = new Jump();

        Stack input = new Stack();

        input.push(6);
        input.push(5);
        input.push(4);
        input.push(3);
        input.push(2);
        input.push(1);
        input.push(0); //remember using stack library to simulate linked list, so add backwards
  
        input.setJump(0,5);
        input.setJump(5,6);
        input.setJump(1,2);

        //input.displayOrder();
        //jump.setFirstJumpOrder(input);

        jump.recursive(input.start);
        input.displayOrder();

    }
}
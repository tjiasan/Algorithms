/*
    Problem: Given only the stack library, implement a queue with stacks

    Solution: use 2 stacks, put all input inside the in stack;

                when trying to remove, if the out stack is empty,

                push all of the input stack into the remove stack,
                thereby reversing it;

                Complexity O(2N) or O(N), space, same as native queue implementation;
*/


class Node {
    protected int data;
    protected Node link;

    /* Constructor */
    public Node ()
    {
        link = null;
        data = 0;
    }

    public Node (int d, Node n){
        data = d;
        link = n;
    }

    public void setLink (Node n){
        link = n;
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

    protected Node start;
    public int size;

    public Stack () {
        size = 0;
        start = null;
    }


    public void push (int data){

        Node push = new Node(data, start);
        start = push;
        size ++;
    }

    public int pop (){

        int pop = start.data;
        
        start = start.getLink();
        size --;
        return pop;
    }

}


class Queue {


    Stack in;
    Stack out;


    public Queue (){
        in = new Stack();
        out = new Stack();
    }


    public void insert(int n){

        in.push(n);
    }

    public Integer remove(){

        if (out.size == 0){
            if (in.size == 0){
                return null;
            } else {
                while (in.size > 0){
                    out.push(in.pop());
                }
            }

        }
    
        return out.pop();

    }



}

public class QwithStack{

    public static void main (String args[]){

        Queue q = new Queue();

        q.insert(1);
        q.insert(2);
        q.insert(3);
        q.insert(4);

        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());

    }
}
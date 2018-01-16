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

    public void push (int data){
        Node push = new Node(data, start);

        start = push;
    }

    public Node pop (){
        Node pop = new Node(start.data, null);

        start = start.getLink();

        return pop;
    }

}
/*
Problem :reverse a linked list:

Solution: 
    Instantiate 3 Node variables :

    Previous
    Current
    Next

    set end to previous, and it's pointer to null

    loop through list from 1:

        get Next from current1
        set current pointer to previous

        if (next){
            set  previous to current, current to next;
        } else {
            Last iteration, set start to current;
        }


*/

/* Class Node */

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

class LinkedList {
    protected Node start;
    protected Node end;
    public int size;

    /* Constructor */
    public LinkedList(){
        start = null;
        end = null;
        size = 0;
    }

    public void insert (int val) {
        if (size == 0){
            this.insertAtStart(val);
        } else {
            this.insertAtEnd(val);
        }
    }

    public void insert (int val, int pos){
        this.insertAtPos (val, pos);
    }

    public void insertAtStart (int val) {
        Node nptr = new Node (val, null);
        size ++;
        if (start == null) {
            start = nptr;
            end = start;
        } else {
            nptr.setLink(start);
            start = nptr;
        }
    }

    public void insertAtEnd (int val){
        Node nptr = new Node(val, null);
        size ++;
        if (start == null){
            start = nptr;
            end = start;
        } else {
            end.setLink(nptr);
            end = nptr;
        }
    }

    public void insertAtPos (int val, int pos){
        Node nptr = new Node (val, null);
        Node ptr = start;
        pos = pos - 1;
        for (int i = 1; i < size; i ++){
            if (i == pos){
                Node tmp = ptr.getLink();
                ptr.setLink(nptr);
                nptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink(); //go to next n;
        }
        size ++;
    }

    public void deleteAtPos (int pos){
        //start position
        if (pos == 1) {
            start = start.getLink();
            size --;
            return;
        }
        //last position
        if (pos == size){
            Node s = start;
            Node t = start;
            while (s != end)
            {
                s = t;
                s.getLink();
            }
            end = t;
            end.setLink(null);
            size --;
            return;
        }

        //delete at end
        Node ptr = start;
        pos = pos -1;
        for (int i = 1; i < size - 1; i ++){
            if (i == pos){
                Node tmp = ptr.getLink();
                tmp = tmp.getLink();
                ptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
        size --;
    }

    public void display (){
        if (size == 0){
            System.out.print("empty");
            return;
        }
        if (start.getLink() == null){
            System.out.println(start.getData());
            return;
        }
        Node ptr = start;
        System.out.print(start.getData() + "->");
        ptr = start.getLink();
        while (ptr.getLink() != null){
            System.out.print(ptr.getData() + "->");
            ptr = ptr.getLink();
        }
        //print last one;
        System.out.print(ptr.getData());
    }

    public void reverse(){
        if (size <= 1){
            return;
        }          

        Node previous = start;
        Node cursor = start.getLink();
        Node next = null;

        end = previous;
        previous.setLink(null); //prevent infinite loops during print;

        for (int i = 1; i < size; i ++){
            
            next = cursor.getLink();
            cursor.setLink(previous);

            if (next != null){
                //move references;
                previous = cursor;
                cursor = next;
            } else {
                start = cursor;
            }          
        }       

    }

}

public class ReverseList {


    public static void main (String [] args){

        LinkedList list = new LinkedList();

        list.insert(-1);
        list.insert(1);
        list.insert(4);
        list.insert(6);
        list.insert(7);
        list.reverse();

        list.display();
    }
}
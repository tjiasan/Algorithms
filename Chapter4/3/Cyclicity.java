/*
    Problem: Given a linked list with/without a cycle, determine if there is a cycle
            if there is one, return the head of the cycle

    Solution: 
            Create two cursors, one that advances one at a time, 
            the other advances two at a time;

            if their memory address matches, then it's a cycle, if encounter null, no cycle

            If there is a cycle, iterate through the cycle to get the loop length;

            restart cursors to zero, offset one of the cursors by loop length,
            advance both cursors 1 at a time to return head;
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

    public void createCycle (int pos){
       
        Node ptr = start;
        pos = pos - 1;
        for (int i = 0; i < size; i ++){
            if (i == pos){
                end.setLink(ptr);              
                break;
            }
            ptr = ptr.getLink(); //go to next n;
        }       
    }

    public int testCycle(){
        //assume position (data value) is address in memory (not)
        Node i = start;
        Node j = start;

        boolean cycle = false;

        while (true){
      
            //skip twice
            j = j.getLink();

            if (j == null){
                break;
            }

            j = j.getLink();
            if (j == null){
                break;
            }
        
            if (i.data == j.data){
                cycle = true;
                break;
            }

            //skip once;
            i = i.getLink();
       
        }

        //calcualte loopLength, iteratively
        int loopLength = 1;
        if (cycle){
            j = j.getLink();
            while (i.data != j.data){
                loopLength++;
                j = j.getLink();
            }           

        } else {
            return -1;
        }

        //reset from start, do over
        i = start;
        j = start;

        while (loopLength > 0){
            j = j.getLink();
            loopLength --;
        }

        while (i.data != j.data){
            i = i.getLink();
            j = j.getLink();
        }

        return i.data;

    }

}

public class Cyclicity {


    public static void main (String [] args){

        LinkedList list = new LinkedList();

        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);

        list.display();

        list.createCycle(2);
        
        int head = list.testCycle();
        System.out.println("");
        System.out.println(head);
      
    }
}
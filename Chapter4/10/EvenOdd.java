
/*
    Problem: Given a linked list reorder the odd elements (1,3,5) to the tail end of the list, and even(0,2,4) to begin;


    Solution: Create a Second head/start and two iterations even = start, odd = start +1, and cursor;
            if cursor encounters even: link  even iterator to cursor
            if cursor encounters odd : link  odd iterator to cursor

            continue until cursor == null;

            set end of even linked list to start of odd, set end of odd list to null;

            creates 0,2,4,1,3,5

            complexity O(N) time O(1) space
            
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

    public int currentPtr;
    public Node current;

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

    public void evenOdd(){

        Node cursor = start;

        Node start1 = start.getLink();

        Node list1  = start;
        Node list2  = start1;

        boolean even = true;

        //skip 2
        cursor = cursor.getLink();
        cursor = cursor.getLink();

        while (cursor != null){
            if (even == true){
                list1.setLink(cursor);
                cursor = cursor.getLink();
                list1 = list1.getLink();
                even = false;

            } else {
                list2.setLink(cursor);
                cursor = cursor.getLink();
                list2 = list2.getLink();
                even = true;
            }       

        }

        list2.setLink(null);
        list1.setLink(start1);

    }




}

public class EvenOdd {

    public static void main (String [] args){

        LinkedList list = new LinkedList();

        list.insert(0);
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        
        list.evenOdd();
        list.display();
    }
}
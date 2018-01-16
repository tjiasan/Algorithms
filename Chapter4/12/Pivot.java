/*
Problem: implement a pivot: given a linked list,
         reorder the values < pivot before,
         then pivot,
         then values > pivot after

Solution: reorder the list into 3 sublists, then recombine;
         
*/

/*
    Problem: Given a node in a linked list, delete the node in O(1) time

    Solution: set the given node to the same data and link as the next one;
            
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

    public void pivot (int k){

        Node cursor = start;

        Node less = null;
        Node greater = null;
        Node exact = null;

        Node exactStart = null;
        Node greaterStart = null;

        while (cursor != null){
            if (cursor.data < k){
                if (less == null){
                    start = cursor;
                    less = cursor;
                } else {
                    less.setLink(cursor);
                    less = cursor;
                }

            } else if (cursor.data == k){
                if (exact == null){
                    exact = cursor;
                    exactStart = cursor;
                } else {
                    exact.setLink(cursor);
                    exact = cursor;
                }

            } else {
                if (greater == null){
                    greater = cursor;
                    greaterStart = cursor;
                } else {
                    greater.setLink(cursor);
                    greater = cursor;
                }
            }

            cursor = cursor.getLink();
        }

        less.setLink(exactStart);
        exact.setLink(greaterStart);

        greater.setLink(null);


    }


}

public class Pivot {

    public static void main (String [] args){

        LinkedList list = new LinkedList();

        list.insert(3);
        list.insert(2);
        list.insert(2);
        list.insert(11);
        list.insert(7);
        list.insert(5);
        list.insert(11);
        
        list.pivot(7);
        list.display();
    }
}
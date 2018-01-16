import java.security.spec.EncodedKeySpec;

import javafx.scene.shape.Ellipse;

/*
    Problem: Add list based integers where it starts from the least significant digits;

    Solution: 
            
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


    public Node getNextNode(){
        if (start == null || current == null){
            return null;
        }

        Node tmp = current;
        current = current.getLink();
        return tmp;
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
            current = nptr;
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

 

}

public class Addition {

    public LinkedList addList (LinkedList a, LinkedList b){

        LinkedList result = new LinkedList();

        int data1 = 0;
        int data2 = 0;

        Node i = a.getNextNode();
        Node j = b.getNextNode();
        int carryover = 0;
        while (true) {

            if (i != null){
                data1 = i.data;
            } else {
                data1 = 0;
            }

            if (j != null) {
                data2 = j.data;
            } else {
                data2 = 0;
            }

            int sum = data1 + data2 + carryover;

            if (sum > 9) {
                carryover = 1;
            } else {
                carryover = 0;
            }

            int insert = sum % 10;

            result.insert(insert);

            if (i != null) {
                i = a.getNextNode();
            } 

            if (j != null) {
                j = b.getNextNode();
            }

            if (j == null && i == null && carryover == 0){
                break;
            }

         }
         return result;

    }

    public static void main (String [] args){

        LinkedList list = new LinkedList();
        LinkedList list1 = new LinkedList();

        list.insert(1);
        list.insert(9);
        list.insert(8);
        list.insert(3);
        list.insert(5);

        list1.insert(2);
        list1.insert(3);
        list1.insert(4);
        list1.insert(2);
        list1.insert(9);
        
     
        Addition add = new Addition();
        LinkedList result = add.addList(list, list1);

        result.display();
    
    }
}
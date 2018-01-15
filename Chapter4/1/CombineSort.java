
/* Problem: 
    Given two sorted linked list, write a program that combines the two lists;

    Solution: 
        Combine the two lists by modifying the library function;
        fuction (LinkedList OtherList);
        unshift OtherList
        Iterate through original list, if less than unshifted OtherList, go to next iteration;
        Else, insert OtherList before original list's current iteration and pop the next list;
        Time complexity O (M +N), space complexity O(1);
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
        System.out.println('\n');
    }

    public void combineSorted (LinkedList other){
        if (other.size == 0) {
            return;
        }

        Node j = other.getNextNode(); // get next node is same as shift();
        Node i = start;
        Node previous = null;

        //set previous by doing 1 iteration;
        if (i.data < j.data){
            previous = i;
            i = i.getLink();
        } else {
            this.insertAtStart(j.data);
            previous = start;
            j = other.getNextNode();
        }

        //intervweave
        while (i != null && j != null){
            //if less go next;
            if (i.data < j.data){
                previous = i;
                i = i.getLink();
            } else {
                Node tmp = new Node(j.data, i);
                previous.setLink(tmp);
                previous = tmp;                   
            
                j = other.getNextNode();             
            }
        } 

        // tailing Other List
        while (j != null){
            this.insertAtEnd(j.data);
            j = other.getNextNode();
        }

    
    }

}

public class CombineSort {


    public static void main (String [] args){

        LinkedList list = new LinkedList();
        LinkedList list1 = new LinkedList();
        list.insert(-1);
        list.insert(1);
        list.insert(4);
        list.insert(6);

        list.display();

        list1.insert(0);
        list1.insert(2);
        list1.insert(3);
        list1.insert(5);
        list1.insert(7);
        list1.display();

        list.combineSorted(list1);

        list.display();
    }
}
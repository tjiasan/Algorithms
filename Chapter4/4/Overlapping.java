/*
   Problem: Given two linked lists (single), One of the linked list may link to the other.
            Figure out if two linked lists connect, and if so where;

    Solution: If two linked list combine, they should end at the same address;
              To figure out the intersection point, Trim the longer list to 
               the same length as the other, and then iterate both at the same time.
              Complexity: O (M +N) time, O(1) space

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

    protected Node start1;
    protected Node end1;

    protected Node current;

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
            end = start;
            current = nptr;
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
        System.out.print(ptr.getData() + "\n");
    }


   public void displayOther (){
        if (size == 0){
            System.out.print("empty");
            return;
        }
        if (start1.getLink() == null){
            System.out.println(start1.getData());
            return;
        }
        Node ptr = start1;
        System.out.print(start1.getData() + "->");
        ptr = start1.getLink();
        while (ptr.getLink() != null){
            System.out.print(ptr.getData() + "->");
            ptr = ptr.getLink();
        }
        //print last one;
        System.out.print(ptr.getData() + "\n");
    }

    public void linkAnotherList (int n, LinkedList otherlist){
        Node ptr = start;

        for (int i = 0; i < n; i++){
            ptr = ptr.getLink();
            if (ptr == null){
                break;
            }
        }

        start1 = otherlist.getNextNode();
        Node current = start1;

        while (current.getLink() != null){
            current = current.getLink();
        }

        current.setLink(ptr);
      
    }

    public void checkOverlap (){
        int length = 1;
        int length1 = 1;
        boolean overlap = false;

        Node ptr = start;
        while (ptr.getLink() != null){
            ptr = ptr.getLink();
            length ++;
        }

        Node ptr1 = start1;
        while (ptr1.getLink() != null){
            ptr1 = ptr1.getLink();
            length1 ++;
        }

        System.out.println(length);
        System.out.println(length1);

        if (ptr.data == ptr1.data){
            overlap = true;
        } else {
            System.out.println("no overlap");
            return;
        }

        ptr = start;
        ptr1 = start1;

        if (length > length1){
            while (length > length1){
                ptr = ptr.getLink();
                length --;
            }
        } else {
            while (length1 > length){
                ptr1 = ptr1.getLink();
                length1 --;
            }
        }

        while (ptr.data != ptr1.data){
            ptr = ptr.getLink();
            ptr1 = ptr1.getLink();
        }


        System.out.println("Same Length at ptr " + ptr.data);

    }

}

public class Overlapping {


    public static void main (String [] args){

        LinkedList list = new LinkedList();
        LinkedList list1 = new LinkedList();

        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);

        list.display();

        list1.insert(6);
        list1.insert(7);
        list1.insert(8);
        list1.insert(9);
        list1.insert(10);
        list1.display();

        list.linkAnotherList(3, list1);
        list.displayOther();
        list.checkOverlap();


    }
}
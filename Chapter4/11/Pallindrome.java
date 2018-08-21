
/*
    Problem: Givena single linked list, determine if the values are pallindromic

    Solution: reverse the second half of the sublist, and iterate through both list
                at the same time;


                reversing algo

                revese;
                reverse_next = reverse.next;

                loop {
                  next = reverse_next.next;
                  reverse_next.next = reverse;

                  reverse = reverse_next;

                  if(not null){
                      reverse_next = next;
                  }
                }
        

                unreverse the list if want not destructive
                Complexity O(N) time O(1)space
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

    public boolean isPallindrome(){

        boolean pallindrome = true;

        int sublist_start = size/2;

        Node reverse_start = null;
        Node cursor = start;

        for (int i = 0; i < sublist_start; i ++){
            cursor = cursor.getLink();
        }

        //ongoing reverse list
        Node reverse = cursor;       
        
        Node rev_pointer = reverse.getLink();
        reverse.setLink(null);

        while (true){
            Node next = rev_pointer.getLink();
            rev_pointer.setLink(reverse);
            
            reverse = rev_pointer;
            
            if (next == null){
                break;
            } else {
                rev_pointer = next;
            }
        }

        //compare
        sublist_start --;
        cursor = start;
        
        for (int i = 0; i < sublist_start; i ++){
            if (rev_pointer.data != cursor.data){
                return false;
            }
            rev_pointer = rev_pointer.getLink();
            cursor = cursor.getLink();
        }

        return pallindrome;

    }



}

public class Pallindrome {

    public static void main (String [] args){

        LinkedList list = new LinkedList();

        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(2);
        list.insert(1);

        boolean is_pallindrome = list.isPallindrome();
        list.display();

        System.out.println(is_pallindrome);

    }
}
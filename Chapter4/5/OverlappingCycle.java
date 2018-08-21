/*
   Problem: Given two linked lists (single) , One of the linked list may link to the other.
            Figure out if two linked lists connect, and if so where;
            One or Both may have cycles!!!;

            if both have cycles all entry points are valid

    Solution: Figure out if either or have cycles;

           

            if one has cycle = doesn't intercept;

            if both has no cycle = compare end, do the same as previous question;

            if both have cycles, (modification of cycle tests):
                - calculate loop length for L1
                - record one node, and see if it exists in the other list, if same node then same cycle


            Two subcases where both have cycles and overlap:
                1) entry points into cycles are differ
                2) lists merge before cycle

            to figure out entry points :
                iterate the list Cycle length apart  , both pointers will eventually get to the same adress  

            if they're the same for both lists, 
                run previous solution to entry point, 
                to get merging point    

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

    public void createCycle (int pos){
       
        Node ptr = start;
        pos = pos - 1;
        for (int i = 0; i < size; i ++){
            if (i == pos){
                end.setLink(ptr);              
                break;
            }
            System.out.println(ptr.data);
            ptr = ptr.getLink(); //go to next n;
        }       
    }
     public int checkOverlapLinear (){
        int length = 1;
        int length1 = 1;     

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


        if (ptr.data != ptr1.data){
            System.out.println("no overlap");
            return -1;
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

        return ptr.data;

    }

    public int checkOverlapCycle (int limit){
        int length = 0;
        int length1 = 0;

        Node ptr = start;

        while (ptr.data != limit){
            ptr = ptr.getLink();
            length ++;
        }

        Node ptr1 = start1;
        while (ptr1.data != limit){
            ptr1 = ptr1.getLink();
            length1 ++;
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
        
        return ptr.data;

    }

    public int testCycle(int list){
        //assume position (data value) is address in memory (not)
        Node i = null;
        Node j = null;

        if (list == 1){
             i = start;
             j = start;
        } else {
             i = start1;
             j = start1;
        }
    
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
        if (list == 1){
             i = start;
             j = start;
        } else {
             i = start1;
             j = start1;
        }

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

    public int checkOverlap (){
        
        int cycle = this.testCycle(1);
        int cycle1 = this.testCycle(2);
        System.out.println("got cycle 1: " + cycle);
           System.out.println("got cycle 2: " + cycle);
        if (
            (cycle > 0 && cycle1 < 0) ||
            (cycle < 0 && cycle1 > 0)
         ){
             //no overlap
            return -1;
        }

        if (cycle < 0 && cycle < 0){
            //test non-cycle linear
            int intercept = this.checkOverlapLinear();
            return intercept;
        }

        if (cycle1 != cycle){
            //both valid answers
            return cycle;
        }

        //intercepted before cycle!
     
        int intercept_cycle = this.checkOverlapCycle(cycle);
        System.out.println("intercept before loop " + intercept_cycle);

        return intercept_cycle;
    }

    

}

public class OverlappingCycle {


    public static void main (String [] args){

        LinkedList list = new LinkedList();
        LinkedList list1 = new LinkedList();

        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);

        //cycle
        list.insert(11);
        list.insert(12);
        list.insert(13);
        list.insert(14);
        list.insert(15);

      

        list1.insert(6);
        list1.insert(7);
        list1.insert(8);
        list1.insert(9);
        list1.insert(10);
       

        list.linkAnotherList(3, list1);
        list.createCycle(8);

      //  list.displayOther();
        
        list.checkOverlap();


    }
}
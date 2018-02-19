/*
    Complexity, Inserts O(1);
                Update O(1);

                read k O(k);
*/

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
public class Frequency2 {

    public LinkedList linkedlist = new LinkedList();
    public HashMap<String,Node> map = new HashMap<String,Node>();

    public void insert (String [] arr){

        for (int i = 0; i < arr.length; i++){
            Node n = map.get(arr[i]);
            if (n == null){
                if (linkedlist.start != null && linkedlist.start.count == 1){
                    linkedlist.start.inside ++;
                    linkedlist.start.ids.put(arr[i], true);
                    map.put(arr[i], linkedlist.start);
                } else {
                    n = new Node(arr[i]);
                    linkedlist.addFirst(n);
                    map.put(arr[i], linkedlist.start);
                }
            } else {
                map.put(arr[i], linkedlist.update(n, arr[i]));
            }

        }



    }

    public void display(){
        Node ptr = linkedlist.end;

        while(ptr != null){
            ArrayList<String> keys = new ArrayList<String>(ptr.ids.keySet());
            
            for (int n = 0; n < keys.size(); n++){
                System.out.println(keys.get(n));
            }

            ptr= ptr.previous;
        }
    }


    public static void main(String args[]){

        
        Frequency2 frequency = new Frequency2();

        String [] input = {
            "bob",
            "bob",
            "bob1",      
            "bob1",
            "bob3",
            "bob2",
            "bob2"
        };

        frequency.insert(input);
        frequency.display();
    }


}

class Node {
    public Node next;
    public Node previous;
    public int count;
    public int inside;
    public HashMap<String, Boolean> ids;

    public Node (String id){
        next = null;
        count = 1;
        inside = 1;
        ids = new HashMap<String, Boolean>();

        ids.put(id, true);
    }
}


class LinkedList {
    public Node start;
    public Node end;

    public LinkedList(){
        start = null;
        end = null;
    }

    public void addFirst (Node n){
        if (start == null){
            start = n;
            end = n;
            return;
        }

        n.next = start;
        start.previous = n;

        start = n;
    }

    public Node update (Node n, String s){
        if (n.inside == 1){
            if (n.next == null || n.next.count < n.count + 1){
                n.count++;
                return n;
            } else {
                //delete current;
                n.previous.next = n.next;
                n.next.previous = n.previous;

                n.next.inside ++;
                n.next.ids.put(s, true);
                return n.next;

            }
        } else {
            n.inside --;
            n.ids.remove(s);

            if (n.next == null){
                Node new_ins = new Node(s);
                new_ins.count = n.count + 1;

                n.next = new_ins;
                new_ins.previous = n;

                end = new_ins;

                return new_ins;

            }

            if (n.next.count == n.count + 1){
                //update
                n.next.ids.put(s, true);
                n.next.inside ++;
                return n.next;
            } else {
                //insert node
                Node new_ins = new Node(s);
                new_ins.count = n.count + 1;

                //fix links
                new_ins.previous = n;
                new_ins.next = n.next;

                n.next.previous = new_ins;
                n.next = new_ins;    
                
                return new_ins;

            }

        }


    }

}
/* Problem: 
    Find the longest length
    in an array such that
    there are all elements from a to b in them;
    e.g.
    int [] input  ={
            3, -2, 7, 9, 8, 2, 0 ,-1, 5, 1
        };
      largest complete subarray = 
      {-2, -1, 0, 1, 2, 3};

      so should return 6;
    
    Soluton :
            1) No extra space, sort + iterate, Complexity O(nlogn)


            2) load everything(Nodes) to hashtable;
                and in in LinkedList; (used to quickly delete processed items)

                Insert = O(1);
                Find = O(1);
                Delete = O(1);


                O(N);

                Iterate through linkedlist {
                    poll linked list, 

                        iterate forwards from item using hashmap,
                        if found, remove in hashmap and linkedlist,
                            size ++

                        iterate backwards from item using hashmap,
                            if found, remove in hashmap and linkedlist
                            size ++

                    compare size,    
                    keep going until linked list is zero
                }




                Time O(2N), Space O(2N);


 */   

import java.util.HashMap;

 class Node {
    public int data;
    public Node next;
    public Node previous;

    Node (int d){
        data = d;
        next = null;
        previous = null;
    }
 }

 class LinkedList{
    public Node start;
    public Node end;
    public int size;

    public LinkedList (){
        size = 0;
        start = null;
        end = null;
    }

    public void insert (Node n){
        if (start == null){
            start = n;
            end = n;
            size ++;
            return;
        }
        end.next = n;
        n.previous = end;
        end = end.next;
        size ++;
    }

    public void remove(Node n){
       //System.out.println(n.data);
        if (n == start){
            start = start.next;
            start.previous = null;            
        } else if (n == end){
            end = end.previous;
            end.next = null;         
        } else {
            Node previous = n.previous;
            Node next = n.next;
            next.previous = previous;
            previous.next = next;       
        }
        size --;
    }

    public Node pollFirst(){
        Node result = start;
        if (start.next != null){
            start = start.next;
            start.previous = null;
        }
        size --;
        return result;
    }
 }


 public class Inclusive {

    public void findLongest(int[] input){

        LinkedList linked_list = new LinkedList();
        HashMap<Integer, Node> exists = new HashMap<Integer, Node>();
        for (int i = 0; i < input.length; i ++){
            Node curr = new Node(input[i]);
            exists.put(input[i], curr);
            linked_list.insert(curr);
        }

        int biggest = 0;
        int current_size = 1;
        
    

        while (linked_list.size > 0){
            
            Node polled = linked_list.pollFirst();
            
            int data = polled.data;
            int start= polled.data;
         
            //crawl right
            while(exists.get(data + 1) != null){
                data ++;
                linked_list.remove(exists.get(data));
                exists.remove(data);
                current_size ++;
            }

            data = start;
           
            //crawl left
            while (exists.get(data -1) != null){
                data --;
                linked_list.remove(exists.get(data));
                exists.remove(data);
                current_size ++;
            }
            if (current_size > biggest){
                biggest = current_size;
            }
        
            current_size = 1;

        }

        System.out.println(biggest);
    

    }


    public static void main (String args[]){

        Inclusive inclusive = new Inclusive();
        int [] input  ={
            3, -2, 7, 9, 8, 2, 0 ,-1, 5, 1
        };

        inclusive.findLongest(input);
    }
 }
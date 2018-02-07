/* Problem: 
            Implement a cache for ISBN number and book prices;
            The least recently used gets evicted,
            most recently used gets updated,


   Solution:
            Use doubly linked linkedlist and hashmap that both contain nodes;
            
            hashmap [ISBN] leads to node : which checks info in constant time O(1);

            lookups/updates reorders node to the very end:
                1) lookup node to be updated in O(1) time;
                2) previous setNext to Next, NExt setPrevious to previous;
                3) end setNext to updatedNode, updatedNode setPrevious to end
                4) end = updatedNode;

            
            inserts delete start node and hash if get to size limit;
            puts node in hashtable;
            remove just fixed linkages;

            Space O(N), time O(1) on all operations;

*/

import java.util.HashMap;

class Node {
    protected String isbn;
    protected int price;
    protected Node previous;
    protected Node next;

    Node (String isbn_input, int p) {
        isbn = isbn_input;
        price = p;
        previous = null;
        next = null;
    }

    public void setPrice(int p){
        price = p;
    }

    public int getPrice(){
        return price;
    }

    public Node getPrevious (){
        return previous;
    }
    public void setPrevious (Node n){
        previous = n;
    }

    public Node getNext (){
        return next;
    }
    public void setNext (Node n){
        next = n;
    }
}

public class ISBNCache {

    public HashMap<String, Node> lookup;
    private Node start;
    private Node end;
    public int size;
    public int specified_size;


    public ISBNCache(int spec_size){
        lookup = new HashMap<String, Node>();
        start = null;
        end = null;
        specified_size = spec_size;
        size = 0;
    }


    public void insert (String isbn, int price){

        Node to_insert = new Node(isbn, price);
        lookup.put(isbn, to_insert);

        if (start == null){          
            start = to_insert;
            end = to_insert;
            size ++;
        } else {
            if (size == specified_size){
                String isbn_remove = start.isbn;
                start = start.getNext();
                lookup.remove(isbn_remove);                
            } else {
                size ++;
            }
            
            end.setNext(to_insert);
            to_insert.setPrevious(end);
            end = end.getNext();
    
        }

    }

    public void remove(String isbn){
        
        Node remove = lookup.get(isbn);

        if (start == remove){
            start = start.getNext();
        }
        if (end == remove){
            end = end.getPrevious();
        }


        Node previous = remove.getPrevious();

        Node next = remove.getNext();

        if (previous != null){
            previous.setNext(next);
        }        

        if (next != null){
            next.setPrevious(previous);
        }   
        
        lookup.remove(isbn);
        size --;

    }

    public void update(String isbn, int price){
        Node to_update = lookup.get(isbn);
        to_update.setPrice(price);
        this.setLast(isbn);
    }

    public void setLast(String isbn){
        Node setLast = lookup.get(isbn);

        if (start == setLast){
            start = start.getNext();
        }

        if (setLast != null){
            Node prev = setLast.getPrevious();
            Node nxt = setLast.getNext();
            
            if (prev != null){
                prev.setNext(nxt);
            }        
    
            if (nxt != null){
                nxt.setPrevious(prev);
            }   
           
            setLast.setNext(null);
            setLast.setPrevious(end);    
            end.setNext(setLast);

            end = setLast;
        }
   
        
    }

    public Integer lookup (String isbn){
        this.setLast(isbn);

        if (lookup.get(isbn) != null){
            return lookup.get(isbn).getPrice();
        } else {
            return null;
        }
        
    }

    public void display(){
        Node ptr = start;

        while (ptr != null){
            System.out.println(ptr.isbn);
            ptr = ptr.getNext();
        }
    }

    public static void main (String args[]){

        ISBNCache cache = new ISBNCache(5);

        cache.insert("first", 40);
        cache.insert("second", 20);
        cache.insert("third", 20);
        cache.insert("fourth", 20);
        cache.insert("fifth", 60);
    
        System.out.println(cache.lookup("first")); //reorder to last

        cache.insert("six", 20);  
        System.out.println(cache.lookup("first")); 
        cache.display();//check if reorder work


        System.out.println(cache.lookup("second")); // should be kicked out;
        
        cache.update("third", 50);  // update third to last
        cache.insert("seven", 20); //kick out  fourth

        System.out.println(cache.lookup("fourth"));// should be kicked out
        System.out.println(cache.lookup("third"));// updated to 50
        //cache.display();
      
    }

}
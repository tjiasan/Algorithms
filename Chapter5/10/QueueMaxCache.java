import java.util.LinkedList;

class Node {
    protected int data;
    protected Node link;

    public Node (){
        data = 0;        
    }

    public Node (int d, Node l){
        data = d;

        link = l;
    }

    public Node getLink(){
        return link;
    }

    public void setLink(Node n){
        link = n;
    }

    public int getData(){
        return data;
    }

}


class Queue {

    public Node head;
    public Node end;
    public int size;

    protected LinkedList<Integer> max_cache;

    public Queue () {
        size = 0;
        head = null;
        end = null;
        max_cache = new LinkedList<Integer>();
 
    }

    public void insert (int data){

        Node ins = new Node(data, null);

        if (head == null){
            size ++;
            head = ins;
            end = head;
            max_cache.addLast((Integer) data);
            return;
        }

       
        while (max_cache.peekLast() != null && max_cache.peekLast() <  data){
            max_cache.removeLast();
        }
        max_cache.addLast((Integer) data);
      

        end.setLink(ins);
        end = ins;
        
        size ++;
    }

    public Integer remove (){
        if (head == null){
            return null;
        }
        
        int pop = head.getData();
     
        head = head.getLink();
    
        if (pop == max_cache.peekFirst()){
            max_cache.removeFirst();
        }  

        return pop;
    }

    public Integer peek (){
        if (head == null){
            return null;
        }
        int peek = head.data;
        return peek;

    }

    public void display (){
        Node ptr = head;

        while (ptr != null){
            System.out.println(ptr.getData());
            ptr= ptr.getLink();
        }

    }

    public Integer max (){        
        if (max_cache.peekFirst() != null){

            return max_cache.peekFirst();
        } else {
            return null;
        }
    }

}

public class QueueMaxCache {

    public static void main (String args[]){
        
        
        Queue q = new Queue();


        q.insert(1);
        q.insert(5);
        q.insert(1);
        q.insert(1);
        q.insert(1);
        q.insert(2);
        q.insert(1);
        
      //  q.display();

        System.out.println("Max is " + q.max());

        System.out.println("removing : " + q.remove());         
        System.out.println("removing : " + q.remove());
 
        
        System.out.println("Max is " + q.max());

        System.out.println("removing : " + q.remove());         
        System.out.println("removing : " + q.remove());
        System.out.println("removing : " + q.remove());         
        System.out.println("removing : " + q.remove());

        System.out.println("Max is " + q.max());

    }



}
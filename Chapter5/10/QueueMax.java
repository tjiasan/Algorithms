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

    protected int max;
    protected int max_index;

    public Queue () {
        size = 0;
        head = null;
        end = null;
        max = 0;
        max_index = -1;
    }

    public void insert (int data){

        Node ins = new Node(data, null);

        if (head == null){
            size ++;
            head = ins;
            end = head;
            max = data;
            max_index = 0;
            return;
        }

        if (data >= max){
            max_index = size - 1;
            max = data;
        }        

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
    
        size --;
        max_index --;
   
        if (max_index <= -1){
            Node ptr = head;
            int current_max = head.getData();
            int current_max_index = 0;

       
            for (int i = 1; i < size; i ++){
                ptr = ptr.getLink();

                if (ptr.getData() >= current_max){
                    current_max = ptr.getData();
                    current_max_index = i;
                }
            }
          
            max = current_max;
            max_index = current_max_index;
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

    public int max (){
        return max;
    }

}


public class QueueMax {


    public static void main (String args[]) {

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
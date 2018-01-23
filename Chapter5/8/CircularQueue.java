/*
    Problem: Implement a queue using an array, with dynamic resizing

    Solution: Using an array, with size, start, and end; 

            every enqueue, move end ++;
            every dequeue, move start ++;

            dequeue array[start];
            enqueeue array[end];


            if end == size,
            copy to new table twice the size; 

*/

class Queue {

    protected int size;
    protected int [] queue;
    protected int start;
    protected int end;

    public Queue (int n){
        start = 0;
        end = 0;
        queue = new int[n];
        size = n;
    }

    public int[] swap(int[] arr, int i , int j){
        int tmp = arr[i];
        arr [i] = arr[j];
        arr[j] = tmp;

        return arr;
    }

    public void swapLower (){
        int counter = 0;
        for (int i = start; i < end; i++){
            queue = this.swap(queue, counter, i);
            counter ++;
        }
        start = 0;
        end = counter;
    }

    public void enqueue (int d){

        //do swaps save space
        if (end == size){
          
            //dynamic resize
            size *= 2;
            
            int[] tmp = new int[size];
            int iter = end - start;
            for (int i = 0; i < iter; i ++){
                tmp[i] = queue[start + i];
            }

            start = 0;
            end = iter;
            queue = tmp;    
          
        }
       
        queue[end] = d;
        end++;
    }

    public  Integer dequeue(){

        if (start > (size/2)){
            this.swapLower();  
        }

        if (start == size){
            return null;
        }

        start ++;

        return queue[start - 1];

    }

}


public class CircularQueue {


    public static void main (String args[]){
        Queue q = new Queue(3);

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);


        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);
        
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());

    }
}
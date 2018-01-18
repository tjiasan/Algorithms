/*Problem : All buildings are in a line facing west (sunset) with different heights, Processing from east to west, determine which
            buildings have a sunset view;

  Solution: set first building as minimum, process from 1;
  
             keep track of the minimum and add to stack, 
             IF encounter building higher, pop the stack until encounter a higher previous building,
             set current to minimum and add to stack;
             
             print stack;

             Worst case O(N) time, O(N) space for stack, if use swaps, ahve to initialize new array anyways for result because different lengths, still O(N) space;
*/


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


class Stack {

    protected Node start;

    public int size;

    public Stack () {
        size = 0;
        start = null;
    }

    public void push (int data){   

        Node push = new Node(data, start);
        size ++;
        start = push;
    }

    public Integer pop (){
        if (start == null){
            return null;
        }

        int pop = start.data;       

        start = start.getLink();
        size --;

        return pop;
    }

    public void display (){

        Node ptr = start;

        while (ptr != null){
            System.out.println(ptr.data);
            ptr = ptr.getLink();
        }

    }

    public Integer peek () {
        if (start == null){
            return null;
        }


        return start.data;
    }


}

public class Sunset {

    public int[] getView(int[] input){

        Stack process = new Stack();
        int start = input.length -1;
        int min = input[start];
        process.push(min);

        for (int i = start - 1; i >= 0; i --){

            int peek = process.peek();

            if (peek > input[i]){
                process.push(input[i]);
            } else {
                while (true){
                    process.pop();

                    if (process.peek() == null || process.peek() > input[i]){
                        break;
                    }
                }
                process.push(input[i]);
                min = input[i];
            }

        }



        int[] result = new int[process.size];


        process.display();

        Integer data = process.pop();

        int i = 0;
        while (data != null){
            result[i] = (int) data;
            data = process.pop();
            i++;
        }

       
        return result; 
    }


    public static void main (String args[]){
        Sunset sunset = new Sunset();

        int[] input = { 2, 4, 3, 2, 1, 4, 7};

        int [] result = sunset.getView (input);

    }


}
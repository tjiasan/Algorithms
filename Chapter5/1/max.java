/*Problem : Implement a stack where you can call the max value of a stack, even after deletions/insertions;

Solution: no free lunch;

        1) Keep track of max numbers in a stack and keep counts of their occurance
            Worst case O(N) time + space O(N); worst case: 1,2,3,4,5,6,
            if encounter 6,5,4,3,2,1, will have to re-cache every time max is deleted;

        2) Keep a small cache of max numbers and occurance :
            O(N)time  + space O (1), more frequent re-cache

        3) Keep a max heap :
            O(N) time + O(N) space, no re-caching;

            deletions of max are O(logN) time;

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

    public int [] [] max_cache;
    public int max_cache_size;

    public Stack () {
        size = 0;
        start = null;
        max_cache_size = 3;
        max_cache = new int [max_cache_size][2];
    }

    private int[][] swap (int [][] arr, int i , int j){
        int [] tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

        return arr;
    }

    private void insertMaxCache(int data){

        int i;

        for (i = 0; i < max_cache_size; i ++){
            if(max_cache[i] == null){
                int [] insert = {data, 1};
                max_cache[i] = insert;
                return;
            }

            if(max_cache[i][0] == data){
                max_cache[i][1] ++;
                return;
            }

            if (max_cache[i][0] < data){
                for (int n = max_cache_size - 1; n > i; n --){
                    max_cache = this.swap(max_cache, n, n -1);
                }

                int [] insert = {data, 1};
                max_cache[i] = insert;
                return;
            }
        }
    }

    public void deleteMaxCache(int data){

        if (size == 0){
            return;
        }

        int i;
        for (i = 0; i < max_cache_size; i ++){
            if (max_cache[i] == null){
                return;
            }
            
            if(max_cache[i][0] == data){
                max_cache[i][1] --;

                if (max_cache[i][1] == 0){
                    max_cache[i] = null;
                    for (int n = i ; n < max_cache_size - 1; n ++){
                        max_cache = this.swap(max_cache, n, n + 1);
                    }
                    return;

                } else {
                    return;
                }                
            }
        }
    }

    public void remakeCache(){
        Node ptr = start;

        while (ptr != null){
            this.insertMaxCache(ptr.data);
            ptr = ptr.getLink();
        }
    }

    public void push (int data){
        this.insertMaxCache(data);

        Node push = new Node(data, start);
        size ++;
        start = push;
    }

    public Node pop (){

        Node pop = new Node(start.data, null);
        
        this.deleteMaxCache(start.data);

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

    public Integer max (){
        if (size == 0){
            return null;
        }

        if (max_cache[0] == null){
            this.remakeCache();
        }

        int max = max_cache[0][0];
        return max;        
    }

}

public class Max {



    public static void main (String args[]){

        Stack stack = new Stack();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.max());
        stack.pop();
        stack.pop();
        System.out.println(stack.max());
        stack.pop();
        System.out.println(stack.max());
        //stack.display();
        



    }


}
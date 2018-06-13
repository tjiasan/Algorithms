/*

    Problem: given an 2D array representinga  triangle
    e.g n = 1;
       n = 2
       n = 3

       find a path from top to bottom
       where bottom is either row + 1, column + 1 or + 0


       Solution: 
        iterate from the bottom of triangle up, 

        create a linked list of previous paths (a linked list Result class) to recurse;
        when iterating up, 
            create a new node for the current, 
            and choose from a list of linked list of previous nodes which one is the child of the current node.
            store location
            update the sum; 

            garbage collection takes care of non referenced objects;



       Time: O(N) where N is the amount of entries in the array ;
       Space: O(L) where L is the level of the triangle;

*/
import java.util.LinkedList;
import java.util.Arrays;
class Result {
    public Result child;
    public int [] location;
    public int sum;
}

public class Triangle {


    public LinkedList<Result> find (int [][] input, int iteration, LinkedList<Result> prev ){
        int length = input.length - iteration;

        if (length == 0){
            return prev;
        }

        LinkedList<Result> next = new LinkedList<Result>();

        for (int i = 0; i < length; i ++){
            Result current = new Result();

            //get the two nodes to compare
            Result prev1 = prev.removeFirst();
            Result prev2 = prev.peekFirst();
         
            int [] location = {length - 1, i};
            current.location =  location;
            current.sum = input[length - 1][i];

            //choose which one has minimum
            if (prev1.sum < prev2.sum){
                current.child = prev1;
                current.sum += prev1.sum;
            } else {
                current.child = prev2;
                current.sum += prev2.sum;
            }   
            next.addLast(current);
        }

        return this.find(input, iteration + 1, next);
    }

    public void  findPath(int [][] input){
        LinkedList<Result> prev = new LinkedList<Result>();

        int length = input.length;
        for(int i = 0; i < length; i ++){

            Result current = new Result();

            current.sum = input[length - 1][i];
          
            int [] location = {length - 1, i};
            current.location =  location;
            current.child = null;

            prev.addLast(current);
        }


        LinkedList<Result> fin = this.find(input, 1, prev);

        Result path = fin.removeFirst();

        System.out.println(path.sum);

        System.out.println("path :");
        while(path != null){
            System.out.println(Arrays.toString(path.location));
            path = path.child;           

        }

    }

    public static void main (String args[]){

        Triangle triangle = new Triangle ();



        int [][] input = {
            {2},
            {4, 4},
            {8, 5, 6},
            {4, 2, 6, 2},
            {1, 5, 2, 3, 4}
        };

        triangle.findPath(input);
    }
}
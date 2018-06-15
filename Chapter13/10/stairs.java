/*
    Problem: you are climbing stairs 1 to k steps at a time

    where your destination is k steps up. 
    write a program that takes n and k and returns the number of ways you can get to your destination

    e.g. 
    4 stairs:
        k = 2:
            2 single 1 double
            1 double 2 single
            4 single
            2 double
            etc...

    Solution: 
        recompose arrays 1 longer at a time from the previous result

        e.g. 
        length = 1

        5

        length = 2
        4 1
        3 2
        2 3
        1 4


        length = 3
        shift previus array from 1 by 1 to the right;
        (use first number, decompose like abobe)

        3 1 1
        2 2 1
        2 1 2
        1 3 1  // if 1 ignore
        2 1 1
        1 2 1 // ignore
        1 1 3 // ignore

        length = 4
        2 1 1 1
        1 2 1 1 
        1 1 1 2
        1 1 2 1 
              


*/
import java.util.LinkedList;
import java.util.Arrays;

public class Stairs {

    public LinkedList<int[]> enumerate (int n, int k, LinkedList<int[]> prev, LinkedList<int[]> result, int iteration){
        
        LinkedList<int[]> next = new LinkedList<int[]>();

        while(prev.isEmpty() == false){
            int[] blank = new int[iteration];
            int[] previous = prev.removeFirst();
            if (previous[0] > 1){
                for (int i = 0; i < previous.length; i ++){
                    blank[i + 1] = previous[i];
                }
    
                for (int i = 1; i < previous[0]; i ++){
                    int[] new_blank = blank.clone();
    
                    new_blank[0] = previous[0] - i;
                    new_blank[1] = i;
                    next.addLast(new_blank);

                    if (new_blank[0] <= k && new_blank[1] <= k){
                        result.addLast(new_blank);
                    }
                }


            }

        }


        if (iteration == n){
            return result;
        }

        return this.enumerate(n, k, next, result, iteration + 1);

    }

    public void pre_process(int n, int k){

        LinkedList<int[]> result = new LinkedList<int[]>();

        int[] start = new int[1];

        start[0] = n;
        LinkedList<int[]> prev = new LinkedList<int[]>();
        prev.addLast(start);

        if (n == k){
            result.addLast(start);
        }

        LinkedList<int[]> fin = this.enumerate(n, k, prev, result, 2);

            
        while(fin.isEmpty() == false){
           int[]  arr = fin.removeFirst();
            System.out.println(Arrays.toString(arr));
        }
        

    }



    public void swap (int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }



    public static void main (String args[]){
        Stairs stairs = new Stairs();

        stairs.pre_process(5, 5);


    }
    
}
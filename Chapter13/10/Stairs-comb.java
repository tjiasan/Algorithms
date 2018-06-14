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
Solution for combinations only:

*/
import java.util.LinkedList;
import java.util.Arrays;

public class Stairs {

    public LinkedList<int[]> enumerate (int n, int k, int[] prev, LinkedList<int[]> result){
        int iteration = prev[0] - 1;
        int[] blanked = new int [n + 1 - iteration];
       
        if (iteration == 0){
            return result;
        }

        for (int i = 1; i < prev.length; i ++){
            blanked[i + 1] = prev[i];
        }        
        
        int i = 1;

        int j = iteration;

        while(i <= j){
            int[] new_blank = blanked.clone();

            new_blank[0] = j;
            new_blank[1] = i;

            i++;
            j--;
    
            if (j <= k){
                result.addLast(new_blank);
            }
            
        }

        blanked[1] = 1;
        blanked[0] = iteration;
    
        return this.enumerate(n, k, blanked, result);

    }

    public void pre_process(int n, int k){

        LinkedList<int[]> result = new LinkedList<int[]>();

        int[] start = new int[1];

        start[0] = n;

        if (n == k){
            result.addLast(start);
        }

        LinkedList<int[]> fin = this.enumerate(n, k, start, result);

            
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
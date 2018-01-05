/*
Problem: Increment an arbitrary precision integer
Solution : use carryover
*/
import java.util.Arrays;
public class Increment {
    public int[] Inc  (int[] arr){
        int carryover = 1;

        for (int i = arr.length - 1; i >= 0; i-- ){
            if (carryover == 1){
                arr[i] ++;
                carryover = 0;
            }           

            if (arr[i] == 10){
                arr[i] = 0;
                carryover = 1;
            }
        }
        return arr;
    } 

    public static void main (String args[]){
        Increment increment = new Increment();
        int[] arr = {0, 9, 9, 9};
        int[] result = increment.Inc(arr);

         System.out.println(Arrays.toString(result));
    }
}


/*
Problem: Compute pascal's triangle
Solution:  Using Previous array, calculate the next one;
            default first and last to one, 
            calculate middle from  the previous array position and the one before it;
*/
import java.util.Arrays;
public class Pascal {

    public int[] generateNext(int [] arr, int k){
        int [] result = new int [k + 1];

        result [0] = 1;
        result [k] = 1;

        for (int i = 1; i < k; i++ ){
            result [i] = arr[i] + arr[i - 1];
        }

        return result;

    }

    public void computePascal (int k){
        k--;
        int [] arr = { 1 };
        if (k == 0) {
            return;
        }
        for (int i = 1; i < k; i ++){
            arr = this.generateNext(arr, i);
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void main (String args[]) {

        Pascal pascal = new Pascal();

        pascal.computePascal(7);
    }
}
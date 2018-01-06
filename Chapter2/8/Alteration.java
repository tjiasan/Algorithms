
import java.util.Arrays;

public class Alteration {

    public int  [] Swap (int[] arr, int i, int j ) {
        int temp = arr[i];

        arr[i] = arr[j];

        arr[j] = temp;

        return arr; 
    }

    public int [] alterate(int[] arr) {
        boolean greater = false;
        int previous = arr[0];

        for (int i = 1; i < arr.length; i++) {
            //System.out.println(greater);
            
            if (greater) {
                if (arr[i] > previous) {
                    arr = this.Swap(arr, i, i -1);
                }
                greater = false;
            } else {
                if (arr[i] < previous) {
                    arr = this.Swap(arr, i, i -1);
                } 
                greater = true;
            } 
            previous = arr[i];
                    
        }
        return arr;
    }

    public static void main (String args[]) {

        Alteration alteration = new Alteration();

        int [] arr = {1, 5, 2, 7, 8};

        int [] result = alteration.alterate(arr);

        System.out.println(Arrays.toString(result));
    }
}
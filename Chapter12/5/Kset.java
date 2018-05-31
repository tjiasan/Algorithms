
import java.util.Arrays; 
public class Kset{


    //copies i to j inclusive
    public int[] deepCopyRange(int[] arr, int i, int j){
        int[] result = new int[j - i + 1];
        
        for (int k = i; k <= j; k++){
            result[k - i] = arr[k];
        }
        return result;
    }


    public int[][] subset( int[] arr, int size ){
        if (arr.length == size){
            int[][] result = new int[1][];
            result[0] = arr;
            return result;
        }

        if (size == 1){
            int[][] result = new int[arr.length][1];

            for (int i = 0; i < arr.length; i ++){
                result [i][0] = arr[i];
            }
            return result;
        }

        int [] subarray = this.deepCopyRange(arr, 1, arr.length - 1);
        int [] subarray1 = this.deepCopyRange(arr, 1, arr.length - 1);

        int[][] subresult = this.combine(arr[0], this.subset(subarray, size - 1));        

        int[][] result = this.concatenate (subresult, this.subset(subarray1, size));

       
        return result;
    }

    public int[][] concatenate (int[][] arr1, int[][] arr2){
        int [][] result = new int[arr1.length + arr2.length][];

        int counter = 0;
        for (int i = 0; i < arr1.length; i++){
            result[counter] = arr1[i];

            counter++;
        }

        for (int i = 0; i < arr2.length; i++){            
            result[counter] = arr2[i];
            counter++;
        }


        return result;
    }

    public void printresult (int[] [] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    public int[][] combine(int val, int[][] arr2){
    
        int [][] result = new int[arr2.length][];

        for (int i = 0; i < arr2.length; i ++){

            int[] combined = new int[arr2[i].length + 1];
            combined[0] = val;

            for (int n = 1; n < combined.length; n ++){
                combined[n] = arr2[i][n-1];
            }    
            
            result [i] = combined;
        }
        return result;

    }


    public static void main (String args[]){
        Kset kset = new Kset();

        int[] input = { 1, 2, 3, 4 };

        int[][] result = kset.subset(input, 3);
        kset.printresult(result);


    }
}
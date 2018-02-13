/*
    Problem: Merge two arrays assuming the first has enough space to fit the second array

    e.g. {1,2,3, blank, blank} 

    merged with {4,5}


    Solution:
            Solve backwards:
                find end of 1st array
                find end of 2nd array;
                use pointer from last position (end1 + end2 +1);
                 while (true){        

                    whichever one is greater, set pointer = end, decrease end, decrease pointer;

                }
                if (1st finishes before 2nd){
                    copy the remaining 2nd backwards to zero;
                }
                other scenario is where end1 = pointer, no point continuing;


                Complexity O(M +N);

                this assumes you already know what N is (not size of the array, because it contains nulls);
*/
import java.util.Arrays;
public class Merge{

    public Integer[] swap (Integer[] arr, int i , int j){
        Integer tmp = new Integer(arr[i]);

        arr[i] = arr[j];
        arr[j] = tmp;

        return arr;


    }

    public Integer[] merge (Integer[] arr1, Integer[] arr2){

        int i  = 0;
        int j = arr2.length - 1;

        while(arr1[i] != null){
            i++;
        }
        i--;

        int pointer = i + j + 1;
    
        while (pointer >= 0){
            if (arr1[i] > arr2[j]){
                arr1[pointer] = arr1[i];
                pointer --;
                i--;
                if (i < 0){
                    break;
                }
            } else{

                arr1[pointer] = arr2[j];
                pointer --;
                j--;

                if (j < 0){
                    //pointer == i;
                    break;
                }
            }
        }

        while (j > -1){
            arr1[pointer] = arr2[j];
            pointer --;
            j--;
        }

        System.out.println(Arrays.toString(arr1));


        return arr1;

    }

    public static void main (String args[]){


        Merge merge = new Merge();

        Integer[] arr1 = { 3, 13, 17, null, null, null, null, null };
        Integer[] arr2 = { 3, 7, 11, 19};


        Integer[] merged = merge.merge(arr1, arr2);
    }

}
/*
    Divide and conquer: basically pick a random pivot;
            partition smaller, left, larger right,

            recurse left and recurse right;


            basic algo implementation:
                really bad if have many repeated elements;

            partition v2:
                Ducth national flag problem;
                
                partition shcueme:
                    parse left, center, right;


*/
import java.util.Arrays;

public class Quicksort{


    public void swap(int arr[], int i , int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public int[] dutchPartition(int arr[], int left, int right){
        int[] index = new int[2];

        int pivot = arr[(left + right)/2];
        int i = left;
        int j = right;

        while(i <= j){
            while (arr[i] < pivot){ 
                i++;
            } 

            while (arr[j] > pivot){
                j--;
            }

            if(i <= j){
                //condition still satisfied, prevent bad swap
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        
            //top half same as original algo;
        
        int left_offset = 0;
        int right_offset = 0;

        //swap to center left side
        //deal with same ; (o- left offset prevent double processing);
        for (int k = 0 ; k < i - left_offset; k++){
            if(arr[k] == pivot){                
                this.swap(arr, k, i - 1 - left_offset);
                left_offset++;               
            }
        }

        //swap to center right side;
        for(int k = i; k < right; k++){
            if(arr[k] == pivot){
                this.swap(arr, k, i + right_offset);
                right_offset++;
            }
        }
        
        index[0] = i - 1 - left_offset; //end of first array;
        index[1] = i + right_offset; //end of second array;

        return index;
    }


    void quickSortDutch(int arr[], int left, int right) {

        int[] index = dutchPartition(arr, left, right);
  
        if (left < index[0]){
            quickSortDutch(arr, left, index[0]);
        }              
  
        if (index[1] < right) {
            quickSortDutch(arr, index[1], right);
        }           
  
    }

    int partition (int arr[], int left, int right){
        int i = left;
        int j = right;

        int pivot = arr[(left + right)/2];

        while(i <= j){
            while (arr[i] < pivot){ //ignore repeated pivot, it'll be sorted in teh future
                i++;
            } 

            while (arr[j] > pivot){
                j--;
            }

            if(i <= j){
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        //by the time it's over i > j;
        // i is index of right start
        // i-1 is left end;
        //System.out.println(Arrays.toString(arr));
        return i;
    }


     void quickSort(int arr[], int left, int right) {

        int index = partition(arr, left, right);
  
        if (left < index - 1){
            quickSort(arr, left, index - 1);
        }              
  
        if (index < right) {
            quickSort(arr, index, right);
        }           
  
    }

    public static void main (String args[]){

        Quicksort quicksort = new Quicksort();

        int[] input = {1, 2, 4, 1, 3, 3, 7, 1, 2, 1, 3};

       // quicksort.quickSort(input, 0, input.length - 1);


       //quicksort.dutchPartition(input, 0, input.length - 1);
      
       quicksort.quickSortDutch(input, 0, input.length - 1);
       System.out.println(Arrays.toString(input));

    }



}
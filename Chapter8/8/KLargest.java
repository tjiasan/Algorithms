/*
    Problem : find K largest

    Solution : 
            (easy): convert into min/max heap
                    dequeue k times or (n-k) times for min heap;
                    compelxity o(N) + k (logk);

            (harder):   pick a random number in the middle, 
                        partition into left, right
                        random number is at (n- right) largest,
                    
                        set pivot as arr[start]; //same as random
                        
                        if (swap), set current to pivot offest
                        if encounter pivot,
                            cache amount,
                            reset loop to same i,
                            swap = true;
                         
                        if encounter less;
                            swap = true;
                            continue
                            
                        if encounter more, 
                            swap to last index,
                            decrease last index,
                            repeat the same i,
                            but set swap to false;

                            repeat until processed = (end - start)

                 fill in duplicates, 
                 
                 if outside duplicates,
                    recurse
                        if (left of array){
                            recurse left start to i -1
                        } if (right of array){
                            recurse right i + pivot_duplicates, end;
                        } else 
                        return pivot 
                            
            (easier) O (K) space;
                        use min heap size of k, 
                        load up min heap, 
                        once full, if bigger, 
                        dequeue min heap, 
                        add new value

                        value will be peek ()

                        Complexity : O (N log k)

     Variant: median : find k, where k is halfway;
     variant : with duplicates, already solved
     variant : mailbox problem; minimize T;
                {
                    residents: 9000
                    distance from start of street: 2
                }
                T = Math.abs(d - building_dist )*R + ...; 
                
                each graph (d - buliding_dist) *R is a linear abs value graph

                the addition of each graph results in one abs graph with a min

                use binary search to find min
                from min dist to max dist; 

                left = a, right = b,

                mid = halfway point, 
                    if mid + 1 is up, right = mid
                    else left = mid;

                    repeat until left - right = precision;

*/

import java.util.Arrays;

public class KLargest {
    public int [] swap (int[] arr, int i, int j){
        int tmp = arr[i];

        arr[i] = arr[j];
        arr[j] = tmp;

        return arr;
    }

    public int find (int[] arr, int k, int start, int end){
        
        int pivot = arr [start];
        int pivot_amount = 0;
        int last_index = end;
        int processed = 0;
        int amount = (end - start);
        int i;
        boolean swap = true;
   
        for (i = start; processed <= amount ; i++){
            if (swap){
                arr[i] = arr[i + pivot_amount];
            }                     
            if (arr[i] < pivot){
                processed ++; 
                swap = true;             
            } else if (arr[i] > pivot){
                arr = this.swap(arr, i, last_index);
                swap = false;
                last_index --;
                i--;
                processed ++;           
            } else {
               processed ++;
               pivot_amount ++;
               i --;        
               swap = true;       
            }            
        }
         
       

        for (int n = 0; n < pivot_amount; n++){
            arr[i + n] = pivot;
        }

        int position = arr.length - k;
        System.out.println(Arrays.toString(arr));

        if (position < i){
            return this.find(arr, k, start, i -1);
        } else if (position > i + pivot_amount - 1){ //pivot amount is original + duplicates
            return this.find (arr, k, i + pivot_amount, end);
        } else {
            return pivot;
        }
   
   
    }

    public static void main (String args[]){

        int [] input = { 3, 2, 8, 5, 4 ,3};

        KLargest largest = new KLargest();


        int posK = largest.find(input, 3, 0, input.length -1);
        System.out.println(posK);
    }
}
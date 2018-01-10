/*
    Problem: given a character array {a, b, b, c, c, d, d};
            replace a with 2 ds and delete b,
            assume the array size is twice as large as num of chars
    Solution: iterate forwards, deleting bs, taking note of number of a,
             copy array from last position, 
             replace a with 2 ds,
*/
import java.util.Arrays;

public class InsertRemove {

    public char[] swap (char [] arr, int i , int j){
        char temp = arr[i];
        arr[i] = arr [j];
        arr[j] = temp;

        return arr;
    }

    public char [] ins_rem (char [] arr){
        int a = 0;
        int b = 0;
        int length = arr.length / 2;

        //delete b's, enumerate a's;
        for (int i = 0; i < length - b; i++) {

            char current = arr[i + b];

            //enumerate a
            if(current == 'a'){
                a++;
            }

            //delete b
            while (current == 'b'){
                arr[i + b] = '\u0000'; //set null
                b++;
                current = arr[i + b];     
            }

            if (b > 0){
                arr = this.swap(arr, i, i + b);
            }
        }
     
        length -= b;
        int start = length - 1 + a;

        // System.out.println(Arrays.toString(arr));
        
        //backfill d;
        // start from calculated index of final array,
        // if encounter a, override current and next to d, move cursor only once for two steps
        // else swap with current cursor, move cursor back once 

        boolean dupl = false;
        for (int i = start; i >= 0; i--){
            if (dupl) {
                arr[i] = 'd';
                dupl = false;
                continue;
            }         
            
            if (arr[length - 1] == 'a'){
                dupl = true;
                arr[i] = 'd';
            } else {                
                arr = this.swap(arr, i, length - 1);                    
            } 

            length --;             
          
            System.out.println(Arrays.toString(arr));
        
        }


        return arr;
    }

    public static void main (String args[]){
        InsertRemove ins = new InsertRemove();
        char [] input = new char[16];
        input [0] = 'a';
        input [1] = 'b';
        input [2] = 'b';
        input [3] = 'c';
        input [4] = 'd';
        input [5] = 'b';
        input [6] = 'c';
        input [7] = 'a';

        // input = {'a' , 'b', 'b', 'c', 'd', 'd', 'c', 'a'};

        char [] result = ins.ins_rem(input);
        System.out.println(Arrays.toString(input));
    }
}
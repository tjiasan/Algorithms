/*


    brute force method:

    Find pattern[0]s,
        probe 4 directions,

        recurse next potential results


        complexity time n + a *k, space a*k
        where a is the amount of 1s
        and k is the length of pattern
*/
import java.util.Arrays;

public class Pattern2D {

    public void find (int[][] arr, int [] pattern){

        

        for (int i = 0; i < arr.length; i ++){
            for (int k = 0; k < arr[0].length; k++){
                if (arr[i][k] == pattern[0]){
                    int[][] pat = new int[pattern.length][2];
                    int[] initial = {i, k};
                    pat[0] = initial;                   
                    this.recurse (arr, pattern, pat, 1);
                }
            }
        }
    }


    public void recurse (int[][] arr, int [] pattern, int[][] prev, int counter){
        int [] current = prev[counter -1];

        if (counter == pattern.length){
            //print answer
           
            for (int i = 0; i < prev.length; i++){
                int[] print = prev[i];
                System.out.println(Arrays.toString(print));
            }


            return;
        }

        //check left
        if (current[1] != 0){
            int left []= new int[2];
            left[1] = current[1] - 1;
            left[0] = current[0];

            if (arr[left[0]][left[1]] == pattern[counter]){
                int[][] next = prev.clone();
                next[counter] = left;
                this.recurse(arr, pattern, next, counter +1);
            }
        }

        //check right
        if (current[1] != arr[0].length -1){
            int right []= new int[2];
            right[1] = current[1] + 1;
            right[0] = current[0];

            if (arr[right[0]][right[1]] == pattern[counter]){
                int[][] next = prev.clone();
                next[counter] = right;
                this.recurse(arr, pattern, next, counter +1);
            }
        }

        //check down
        if (current[0] != arr.length -1){
            int down []= new int[2];
            down[1] = current[1];
            down[0] = current[0] + 1;

            if (arr[down[0]][down[1]] == pattern[counter]){
                int[][] next = prev.clone();
                next[counter] = down;
                this.recurse(arr, pattern, next, counter + 1);
            }
        }

        //check up
        if (current[0] != 0){
            int up []= new int[2];
            up[1] = current[1] ;
            up[0] = current[0] - 1;

            if (arr[up[0]][up[1]] == pattern[counter]){
                int[][] next = prev.clone();
                next[counter] = up;
                this.recurse(arr, pattern, next, counter +1);
            }
        }
        
        
    }


    public static void main (String args[]){


        Pattern2D pat = new Pattern2D();


        int[][] arr = {
          {1, 2, 3},
          {3 ,4, 5},  
          {5 ,6, 7} 
        };
        
        
        int[] pattern = {1, 3, 4, 6};

        pat.find(arr, pattern);




    }

}
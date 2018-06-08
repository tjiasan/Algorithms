/*


    brute force method:

    Find pattern[0]s,
        probe 4 directions,

        recurse next potential results
*/
import java.util.Stack;

public class Pattern2D {

    public void find (int[][] arr, int [] pattern){

        Stack <int[][]> results = new Stack <int[][]>();

        for (int i = 0; i < arr.length; i ++){
            for (int k = 0; k < arr[0].length; k++){
                if (arr[i][k] == pattern[0]){
                    int[][] pat = new int[pattern.length][2];
                    int[] initial = {i, k};
                    pat[0] = initial;
                }
            }
        }

        int counter = 1;
        Stack <int[][]> next = new Stack <int[][]> ();
        while(results.isEmpty() == false){

            int[] check = results.pop();

            //check left
            int[] index = check[counter -1];


            if (results.isEmpty() == true && next.isEmpty() == false){


                counter ++;
            }

        }

    }


    public static void main (String args[]){


        Pattern2D pat = new Pattern2D();


        int[][] arr = {
          {1, 4, 3, 2},
          {1 ,2, 2, 1}  
        };
        
        
        int[] pattern = {1, 2, 3, 4};

        pat.find(arr, pattern);




    }

}
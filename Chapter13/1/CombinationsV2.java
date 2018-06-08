/*

    Use book solution :


    for an extra combination :

    e.g. score 12:

        2 only = 2 * 6;

        2,3 = 2* 6   +  combo 2 for 9 with 3 added + combo 2 for 6 with 3 added


        similarly to calculate this could also do:

        2 *6 + combo for 2,3 for 9 + 3 added

*/

import java.util.Arrays;
public class CombinationsV2 {


    public void enumerate(int total, int[] scores){

        int [][][] answers = new int[total + 1][][];

        for (int i = 0; i <= total; i += scores[0]){
            int[] ans = new int[scores.length];
            ans[0] = i/scores[0];           
            answers[i] = new int[1][];
            answers[i][0] = ans;
        }
        
        
        for (int i = 1; i < scores.length; i++){
            for (int k = 1; k <= total; k ++){
                int[][] combine = new int [0][];

                if (k - scores[i] >= 0){
                    combine = answers[k - scores[i]];
                }
                System.out.println("");
                System.out.println(k);
                
               
                answers[k] = this.combine (answers[k], combine, i);
                
                
            }
        }

        //not cloning properly!!
        System.out.println(Arrays.toString(answers[12][1]));
      
    }

  
    public int[][] combine (int [][] a, int [][] b, int index){

        if (a == null){
           a = new int [0][];
        }
        
        int[][] answer = new int[a.length + b.length][];

        for (int i = 0; i < a.length; i ++){
            answer[i] = a[i].clone();
        }
        
        int counter = 0;
        for (int i = a.length; i < a.length + b.length; i ++ ){
            answer[i] = b[counter].clone();
            answer[i][index] ++;
            counter++;
        }
   
        return answer;
    }

    public static void main (String args[]){

        CombinationsV2 comb = new CombinationsV2();

        int[] scores = {2, 3, 7};

        comb.enumerate(12, scores);


    }
}
/*

*/
public class Traverse2D {

    public int count (int [] [] input, int i, int j){

        if (i == input.length -1 || j == input[0].length -1){
            return 1;
        }


        return this.count(input, i + 1, j)  + this.count (input, i, j + 1);


    }


    public void countv2 (int [] [] input){

        for (int j = 0; j < input[0].length; j++){
            for (int i = 0; i < input.length; i ++){

                int sum = 0;
                if(i > 0){
                    sum += input[i -1][j];
                }
                if (j > 0){
                    sum += input[i][j - 1];
                }

                if (sum == 0){
                    sum = 1;
                }

                input[i][j] = sum;
            }
        }

 
        System.out.println(input[input.length -1][input[0].length -1]);



    }
    public static void main (String args[]){
        
        Traverse2D trav = new Traverse2D();

        int [] [] input = new int[5][5];
 

        int result = trav.count(input, 0, 0);

        System.out.println(result); //should return 70

          trav.countv2(input);
    }
}
/*
    Problem : Sinusoidal Ordering

        "Hello World" becomes 
         E    W   D  
        H L O  O L
           L    R

        translates to EWDHLOOLLR  

    Solution : if k is even, belongs to middle;
                if you add k + 3, it's top if it's modulo by 4 equals 0;

                Complexity O(N);

 */


public class Sine {

    public int where (int k){
        //middle 
        if (k % 2 == 0){
            return 1;
        }
        // top
        if ((k + 3) % 4 == 0){
            return 0;
        } else {
            //bottom
            return 2;
        }
    }

    public String getSine(String input){

        String top = "";
        String mid = "";
        String bottom = "";

        for (int i = 0; i < input.length(); i++){

            int pos = this.where(i);

            if (pos == 1){
                mid += input.charAt(i);
            } else if (pos == 2){
                bottom += input.charAt(i);
            } else {
                top += input.charAt(i);
            }
        }

        return top + mid + bottom;

    }


    public static void main (String args []){

        Sine sine = new Sine();

        String input = "Hello World!";

        String result = sine.getSine(input);

        System.out.println(result);
    }
}        
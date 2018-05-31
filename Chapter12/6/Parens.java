/*
Solution: 
        use previous result to add a complete parens at every position
        and a hashmap to eliminate duplicates
*/

import java.util.HashMap;
import java.util.Arrays;

public class Parens {


    public void all (int n, String[] prev){
        if (n == 1){

            System.out.println(Arrays.toString(prev));
            return;
        }

        if (n == 0){
            System.out.println("");
            return;
        }

        HashMap<String, Boolean> result = new HashMap<String, Boolean> ();

        for (int i = 0; i < prev.length; i++){
           
            for (int k = 0; k < prev[i].length(); k++){
                String before = prev[i].substring(0, k);
                String after = prev[i].substring(k, prev[i].length());
                String current = before + "()" + after;
               // System.out.println(current);
                result.put(current, true);
            }

        }

        String[] next = new String[result.size()];
        int counter = 0;

        for (String key: result.keySet()){
            next[counter] = key;
            counter++;
        }        

        this.all(n - 1, next);

    }



    public static void main (String args[]){
        Parens parens = new Parens();
        String[] prev = { "()" };
        parens.all(4, prev);
       /*
        String test = "abc";
        System.out.println (test.substring(0,0).equals(""));
        System.out.println (test.substring(0,1).equals("a"));
        System.out.println (test.substring(1,2).equals("b"));
        */
    }


}
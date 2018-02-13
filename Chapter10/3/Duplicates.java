/*
Problem: given an array with { {first, last} ...},
        eliminate dupliate first names;


Solution: 
            brute force: use hash table,
                Space O(N), time O(N);
                
                
            OR: Sort, time O(nlogn), space O(1)

            then O(N) algo to get rid of duplicates
*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;

class StringComparator implements Comparator <String []> {
    @Override
    public int compare(String[] a, String[] b){
        // -1 if a < b
        // 0 if equal
        // 1 if a > b
        return a[0].compareTo(b[0]);
    }

}

public class Duplicates {

    public String[] [] eliminate (String[] [] input){

        StringComparator comparator = new StringComparator();


        Arrays.sort(input, comparator);


        //eliminate duplicates
        ArrayList<String[]> result = new ArrayList<String[]> ();      
        String first = input[0][0];
        result.add(input[0]);
        System.out.println(first);
        
        for (int i = 1; i < input.length; i++){
            if (input[i][0].equals(first) == false){
                result.add(input[i]);
                first = input[i][0];
                System.out.println(first);
            }
        }

        //convert to final output
        String [] [] eliminated = new String[result.size()][2];

        for (int i = 0; i < result.size(); i++){
            eliminated[i] = result.get(i);
        }

        return  eliminated;


    }


    public static void main (String args[]){

        Duplicates duplicates = new Duplicates();


        String [] [] input = { {"bob", "dole"} , {"bob", "ane"}, {"aunique", "one"} };

        String [] [] elim = duplicates.eliminate(input);


        //System.out.println(Arrays.toString(elim[0]));
    }


}
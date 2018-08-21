import java.util.Arrays;
/* 
Problem: Search engine impelementation,
        retreive documents that match the set of words in query by maintaining
        an inverted index. 
        Each page is assigned an integer identifier,
        An inverted index is a mapping that takes a word, 
        and returns a sorted array of page ids contain w, 
            e.g. page rank in descending order
        when there's multiple words, it returns the intersection of these arrays
        i.e. having all words in the query

Solution: iterate through two lists simultaneously;
        while first list less than second;
        first ++;

        if equal,
            add intersect
            iteratre until unequal

        while second list less tha first,
        second ++;
        if equal,
            add intersect
            iteratre until unequal

        repeat until end of either list    

        */

import java.util.ArrayList;
import java.util.Arrays;

public class Intersection {

    public int[] find(int[] arr1, int[] arr2){
        ArrayList <Integer> result = new ArrayList<Integer>();

        int i = 0;
        int j = 0;

        outerLoop:
        while (true){
            while (arr1[i] < arr2[j]){
                i++;
                if (i == arr1.length){
                    break outerLoop;
                }
            }

            if (arr1[i] == arr2[j]){
                result.add(arr1[i]);     
                //System.out.println(arr1[i]);        
                while (arr1[i] == arr2[j]){
                    i++;

                    if (i == arr1.length){ 
                        break outerLoop;
                    }
                }
            }

    
            while (arr2[j] < arr1[i]){
                j++;
                if (j == arr2.length){
                    break outerLoop;
                }
            }

            if (arr1[i] == arr2[j]){
                result.add(arr2[j]);
               // System.out.println(arr2[j]); 
                while (arr1[i] == arr2[j]){
                    j++;
                    if (j == arr2.length){
                        break outerLoop;
                    }
                }
                
            }


        }




        int [] resultArray = new int[result.size()];


        for (int k = 0; k < result.size(); k++){
            resultArray[k] = result.get(k);
        }

        System.out.println(Arrays.toString(resultArray));
        return resultArray;
    }


    public static void main (String args[]){

        Intersection intersect = new Intersection ();

        int [] arr1 = {2, 3, 3, 5, 5, 6, 7, 7, 8, 12};
        int [] arr2 = {5, 5, 6, 8, 8, 9, 10, 10};

        int [] intersection = intersect.find(arr1, arr2);


       
    }

}
/*
    Otherwise known as bucket sort:

    Initialze buckets, either 256 LInked lists, if you want
    alphanumeric, or 10 if just numbers;

    Usually used with strings;

    Simply, iterate through list from Least Significant character (rightmost);

    if (can't get character, digit = 0),

    else add to linked list at specific digit, (queue);
    
    after looping through everything, 
    iterate through queue and empty it out to original array;

    if there are no more digits break;

    Complexity (n *m) where M is length of characters;
    and O(N)space;

    each passthrough to buckets is 2N; 
*/


import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

public class Radix {

    public void sort (String [] arr){

        //easier to do if integers are string;

        ArrayList<LinkedList<String>> buckets = new ArrayList<LinkedList<String>>(10);      
        
        for (int i = 0; i < 10; i ++){
            buckets.add(new LinkedList<String>()); //create queue
        }
      
        int counter = 0;
        while (true){
            boolean any_digits = false;
    
            for (int i = 0; i < arr.length; i++){
                String current = arr[i];
                int digit = 0;

                if (current.length() > counter){
                    any_digits = true;
                    digit = Character.getNumericValue(current.charAt(current.length() - 1 - counter));
                } 

          

                buckets.get(digit).addLast(current); //add to que
            }

            //empty queue;
            int pos = 0;
            for (int i = 0; i < 10; i ++){            
                while(buckets.get(i).isEmpty() == false){
                    arr[pos] = buckets.get(i).removeFirst();
                    pos++;
                }              
            }
            counter ++;


            if (any_digits == false){
                break;
            }
        }

        System.out.println(Arrays.toString(arr));
    }


    public static void main (String args[]){

        String[] input = {"3", "2", "1", "33", "11", "111"};

        Radix radix = new Radix();

        radix.sort(input);
    }


}
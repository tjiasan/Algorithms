/*
Problem: Given a game that allows you to know how far
you can advance through the array in each element, 
Determine an algorithm to know whether or not it's possible
to finish the game'

Solution: use max advance for each element of an array;
if want to know min steps, survey max reach of within chosen step element, and reiterate:
e.g. 
 from arr[0], arr[1] has max reach, 
 from arr[1], iterate forward to reach of arr[1] to see what has max reach;

*/

public class Advance {
    public boolean Possible (int[] arr) {
        boolean possible = false;

        int max_advance = 0;

        for (int i = 0; i < arr.length; i++){
            if ((i + arr[i]) > max_advance){
                max_advance = i + arr[i];              
            }
  
            if ( max_advance >= (arr.length - 1)) {
                possible = true;
                break;
            }
            if (i >= max_advance) {
                break;
            } 
        
        }


        return possible;
    }

    public static void main (String args[]) {
        Advance advance = new Advance();
        int [] arr = { 3, 3, 1, 0, 2, 0, 1 };
        
        boolean result = advance.Possible(arr);

        System.out.println(result);
    }
}
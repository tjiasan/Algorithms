/*
    Problem: Given that phone digits have Letters representing a number,
            write a code that generates all mnemonics;
            e.g 2 = 'A B C';
            this gives 3 possible letters, write all combosd

     Solution: use recursion to do a brute force approach instead for 7 for loops      



    if don't want to create a new array every time, do an O(n) to create array of all initial results, 
    then, fill from the back from calculated size, the algorithm below mimics table doubling in
    vector libraries;
*/


import java.util.Arrays;

public class Phone {

    public int length_needed (char i){        
        if (i == '7' || i =='9'){
            return 4;
        } else {
            return 3;
        }
    }

    public char [] get_chars (char n){
        int index = Character.getNumericValue(n);

        char [] [] result =  {
            { '0' },
            { '0' },
            {'A', 'B', 'C'},
            {'D', 'E', 'F'},
            {'G', 'H', 'I'},
            {'J', 'K', 'L'},
            {'M', 'N', 'O'},
            {'P', 'Q', 'R', 'S'},
            {'T', 'U', 'V'},
            {'W', 'X', 'Y', 'Z'},
        };

        return result [index];    

    }


    public String [] generate (char[] input, int input_index, String[] previous){
        if (input_index == input.length){
            return previous;
        }
        
        int multiplier = this.length_needed(input[input_index]);
        char [] chars = this.get_chars(input[input_index]);
        int new_length = previous.length * multiplier;

        String [] next = new String [new_length];
        
        //cound use i*chars.length + k instead of index, but don't want to calculate everytime
        int counter = 0;
        for (int i = 0; i < previous.length; i ++){
            for (int k = 0; k < chars.length; k ++){
                next[counter] = previous[i] + chars[k];
                counter ++;
            }
        }

        return this.generate(input, input_index + 1, next);

    }


    public static void main (String args[]) {
        Phone phone = new Phone ();

        String phone_number = "227";
        char [] number_chars = phone_number.toCharArray();
        String [] previous = new String[1];
        previous[0] = "";

        String [] result = phone.generate(number_chars, 0, previous);

        System.out.println(Arrays.toString(result));

    }
}
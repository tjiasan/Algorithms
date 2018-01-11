/*
    Problem: Given a string that has digits, minus the dots, write a code that ennumerates all possible ip adresses (4 periods);
    Max IP parameters are 255.255.255.255

    Solution: Going from left to right, sample 3 digit numbers, write and combine all numbers with less than or equal to 255;
                except for the last iteration, where you need an exact amount of digit;
             Terminate all loops early where it is no longer to finish with all numbers

*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class Ip {

    public String [] getValid(String input, int offset, int  iteration){
    
        List<String> interim_res = new ArrayList<String>();
     
        int max;
        int exact = input.length() - offset;

        //max looping, only sample 3 at a time
        if (input.length() < offset + 3){
            max = input.length();
        }      
        else {
            max = offset + 3;
        }

       
        //optimization for not possible outcomes, terminate loop early if it isn't possible to finish
        int interations_left = 4 - iteration;

        if (exact - interations_left * 3 > 3){
            return interim_res.toArray(new String[0]);
        }

        int current = 0;     
        int counter = 0;
        String res = "";

        for (int i = offset; i < max ; i++) {
            current *= 10;

            int digit = Character.getNumericValue(input.charAt(i));
            current += digit;
         
            counter ++;
            res += input.charAt(i);

            //put in array if less than 255
            if (current <= 255){
                //for last iteration need ONLY exact length put in, implement counter;
                if (iteration == 4 ){
                    if (counter == exact){
                        interim_res.add(res);
                    }                   
                } else {
                    interim_res.add(res);
                }
                
            } else {
                //too high, so ignore;
                break;
            }           
        }    

        return interim_res.toArray(new String[0]);

    }

    public String[] generate (String input, int iteration, int size, String[] previous){

        //trim empty arrays;
        if (iteration == 5){
            String [] final_result = new String [size];

            for (int k = 0; k < size; k ++) {
                final_result[k] = previous[k];
            }

            return final_result;
        }

        int next_size = size * 3;
        String [] next = new String[next_size];
        int current_size = 0;


        for (int i = 0; i < size; i++){
            String current = previous[i];

            //starting position;
            int offset = current.length() - iteration + 1;

            if (iteration > 1){
                offset += 1;
            }

            String[] possibilities = this.getValid(input, offset, iteration);

            for (int k = 0; k < possibilities.length; k ++){
                if (iteration == 1){
                    next[current_size] = possibilities[k];
                    current_size++;
                } else {
                    next[current_size] = current + "." + possibilities[k];
                    current_size++;
                }
                
               
            }

        }

        return this.generate(input, iteration +1, current_size, next);


    }


    public static void main (String args[]){
        Ip ip = new Ip();
        
        String input = "19216811";
       // String input = "1111";
        String [] input_s = new String[1];
        input_s[0] = "";

        String[] result = ip.generate(input, 1, 1, input_s);

        System.out.println(Arrays.toString(result));
    }
}
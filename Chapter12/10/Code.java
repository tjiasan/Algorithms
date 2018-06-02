

import java.util.HashMap;
import java.util.Arrays;

public class Code {
    
    public void allPermutations(int power){

        int max = (int)Math.pow(2, power) -1;

        for (int i = 0; i <= max; i++){
            HashMap<Integer, Integer> location = new HashMap<Integer, Integer>();
            location.put (i, 0);
            this.recurse(power, i, 0, location);
        }

    }

    public void recurse(int power, int last, int last_location,  HashMap<Integer, Integer> location ){

        int max = (int)Math.pow(2, power) - 1;

        if (last_location == max){
            int[] result = new int[max + 1];

            for (Integer key: location.keySet()){
                int loc = (int) location.get(key);
                result[loc] = (int) key; 
            }
            System.out.println(Arrays.toString(result));
            return;
        }

        for (int i = 0; i < power; i ++){
            int mask = 1 << i; 
            int next = last ^ mask;

            if (location.get(next) == null && next <= max){
                HashMap<Integer, Integer> next_location =  new HashMap<Integer, Integer>();
                next_location.putAll(location);

                next_location.put(next, last_location + 1);           

                this.recurse(power, next, last_location + 1, next_location);
            }
        }
    }

    public static void main (String args[]){
         Code code = new Code();

        code.allPermutations(3);

    }
}
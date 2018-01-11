/*
    Problem : Given a string like "aaabbcc", compress the string into "3a2b2c"
    Solution: O(N) result pretty much;
*/

public class Encode {

    public String compress (String input){

        String result = "";
        char current = input.charAt(0);
        int counter = 1;

        for (int i = 1; i < input.length(); i++){
            if (current != input.charAt(i)){
                result += Integer.toString(counter) + current;
                current = input.charAt(i);
                counter = 1;
            } else {
                counter ++;
            }

        }


        result +=  Integer.toString(counter) + current;
        
        return result;
    }

    public boolean is_digit (char n) {
        if (Character.getNumericValue(n) <= 9){
            return true;
        }
        return false;
    }

    public String uncompress (String input){

        String result = "";
        int sum = 0;
        for (int i = 0; i < input.length(); i ++){
            char current = input.charAt(i);
            if (this.is_digit(current)){
                sum *= 10;
                sum += Character.getNumericValue(current);
            } else {
                for (int n = 0; n < sum ; n++){
                    result += current;
                }  
                sum = 0;              
            }

        }

        return result;
    }

    public static void main (String args[]){

        Encode encode = new Encode ();

        String input = "aaabbcc";

        String result = encode.compress(input);
        System.out.println(result);

        String input1 = "3a3b3c";
        String result1 = encode.uncompress(input1);
        System.out.println(result1);

    }


}
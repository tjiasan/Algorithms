/*
    Problem: Write an algorithm that converts a number encoded in one base, to another number encoded in another base
    Solution: Same method for converting to base 10, 
              modulo the base, 
              add remainder to string,
              repeat until less than zero;
*/

public class Base  {

    public int convertFromHex (char c){


        if (c == 'A'){
            return 10;
        } else if (c == 'B'){
            return 11;
        } else if (c == 'C'){
            return 12;
        } else if (c == 'D'){
            return 13;
        } else if (c == 'E'){
            return 14;
        } else if (c == 'D'){
            return 15;
        } else {
            return  Character.getNumericValue(c);
        }
    }

    public String convertToHex(int i){
        if (i < 9){
            return Integer.toString(i);
        } else if (i == 10){
            return "A";
        } else if (i == 11){
            return "B";
        } else if (i == 12){
            return "C";
        } else if (i == 13){
            return "D";
        } else if (i == 14){
            return "E";
        } else {
            return "F";
        }
    }

    public int convertInteger (String input, int input_base){

        int result = 0;        
        int running = 1;
        for (int i = input.length() - 1; i >=0 ; i--) {                    
            int digit = this.convertFromHex(input.charAt(i));         
            result += digit * running;
            running *= input_base;                  
     }

        return result;
    }

    public String convert (String input, int input_base, int output_base){
        String result = "";

        int input_integer = this.convertInteger (input, input_base);

        while (input_integer > 0){
            String digit = this.convertToHex(input_integer % output_base);

            input_integer /= output_base;

            result += digit;//reverse later
        }

        //reverse it because started at end
        char[] res = result.toCharArray();
        int begin = 0;
        int end = res.length - 1;
        char temp;
        while(end > begin){
            temp = res[begin];
            res [begin] = res[end];
            res [end] = temp;
            end--;
            begin++;
        }
        result = new String(res);
        return result;

    }

    public static void main (String args[]) {

        Base base = new Base ();

        String input = "615";
        int input_base = 7;

        int output_base = 13;

        String result = base.convert(input, input_base, output_base);

        System.out.println(result);
    }
}
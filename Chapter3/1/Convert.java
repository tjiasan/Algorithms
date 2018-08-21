/*
    Problem : Do a conversion from string to integer and vice versa depending ont he inptu

    Solution: brute force
 */


public class Convert {

    public String convertString(int input) {
        boolean negative = false;
        if (input < 0){
            input *= -1;
            negative = true;
        }

        String result = "";

        while (input > 0){
            result += Integer.valueOf(input % 10);
            input /= 10;
        }

        char[] res = result.toCharArray();
        int begin = 0;
        int end = res.length - 1;
        char temp;
        //reverse
        while(end > begin){
            temp = res[begin];
            res [begin] = res[end];
            res [end] = temp;
            end--;
            begin++;
        }
        result = new String(res);

        if (negative){
            return "-" + result;
        } else {
            return result;
        }

    }

    public int convertInt(String input){

        boolean negative = false;
        int limit = 0;
        if (input.charAt(0) == '-'){
            negative = true;
            limit = 1;
        }

        int result = 0;

        for (int i = limit; i < input.length(); i++){
            result *= 10;
            int digit = Character.getNumericValue(input.charAt(i));
            result += digit;            
        }

        if (negative){
            return -1 * result;
        } else {
            return result;
        }

    }

    public Object convert (Object input){

        if (input instanceof Integer){
            int subinput = (int) input;
            String result =  this.convertString(subinput);
            System.out.println(result);

        } else if (input instanceof String){
            String subinput = (String) input;
            int result = this.convertInt(subinput);
            System.out.println(result);
        }

        

        return input;
    }

    public static void main (String args[]){

        int input = 123;

        String input1 = "123";

        Convert convert = new Convert();

        Object output = convert.convert(input);
        Object output1 = convert.convert(input1);
    }
}
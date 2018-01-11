/*
    Problem: Convert Roman numerals to decimal

*/

public class Roman {

    public int translate (char a){
        if (a == 'I'){
            return 1;
        } else if (a == 'V'){
            return 5;
        } else if (a == 'X'){
            return 10;
        } else if (a == 'L') {
            return 50;
        } else if (a == 'C') {
            return 100;
        }  else if (a == 'D') {
            return 500;
        } else if (a == 'M') {
            return 1000;
        }

        return -1;
    }

    public int exceptions (int i){
        if (i == 10 || i == 5){
            return 1;
        } else if (i == 50 || i == 100){
            return 10;
        } else if (i == 500 || i == 1000 ){
            return 100;
        } else {
            return -1;
        }
    }

    public int convert (String input){
        int result = 0;
        int exception = -1;
        for (int i = input.length() - 1; i >=0 ; i--){

            int digit = this.translate(input.charAt(i));
            boolean just_excepted = false;
            if (digit == exception){
                result -= digit;
                just_excepted = true;
            } else {
                result += digit;
            }


            exception = this.exceptions(digit);
            if (
                just_excepted == true && 
                 (i - 1) >= 0 &&
                 exception == this.translate(input.charAt(i-1))
            ){                
                return -1;
            }

        }

        return result;
    }



    public static void main (String args[]){
        Roman roman = new Roman();

        String input = "LIX";

        int result = roman.convert(input);

        System.out.println(result);

    }


}
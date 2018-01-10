/*
    Problem: Spreadsheets encode columns by letters
    A - Z (or 1-26);

    ZZ = 26 * 26 ^1 + 26 = 702;
 
    Solution: same as converting string to int with base of 26;

    Variant A = 0 instead of 1; (subtract result by 1?)

*/

public class Spreadsheet {

    public int convertInt (char c) {

        int num = Character.getNumericValue(c);

        num -= 9;

        return num;

    }

    public int convertSpread(String input){

        int result = 0;
        int running = 1;
        int multiplier = 26;

        for (int i = input.length() -1; i >= 0; i--){
            int digit = this.convertInt(input.charAt(i));

            result += digit * running;            

            running *= multiplier;          
        }

        return result;

    }


    public static void main (String args[]){
        Spreadsheet spreadsheet = new Spreadsheet();

        int result = spreadsheet.convertSpread("AAA");

        System.out.println(result);

    }


}
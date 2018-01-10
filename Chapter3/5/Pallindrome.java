


public class Pallindrome {
    public boolean isAlpha (char a){
         int num = Character.getNumericValue(a);     
         if (num > -1 && num <= 35){
             return true;
         } else {
             return false;
         }
    }

    public boolean isPallindrome(String input){
        boolean is_pal = true;

        int i = 0;
        int j = input.length() - 1;

        while (i < j){
            while (this.isAlpha(input.charAt(i)) == false) {
                i ++;
            }

            while (this.isAlpha(input.charAt(j)) == false) {
                j--;
            }

            int num1 = Character.getNumericValue(input.charAt(i));   
            int num2 = Character.getNumericValue(input.charAt(j)); 

            if (num1 != num2){
                is_pal = false;
                break;
            }

            i ++;
            j --;
        } 

        return is_pal;
    }


    public static void main (String args[]){
        Pallindrome pallindrome = new Pallindrome ();
        String input = "Bob sis bob!";


        boolean result = pallindrome.isPallindrome(input);

        System.out.println(result);
    }
}
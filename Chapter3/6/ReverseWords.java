/*
    Problem: Reverse words in order 
            for example "all is good"
            to "good is all";

    Solution: reverse everything; 
              reverse individual words;

              complexity O(N), space O(1);        
*/


public class ReverseWords {

    public char[] swap (char [] arr, int i , int j){
        char temp = arr[i];
        arr[i] = arr [j];
        arr[j] = temp;

        return arr;
    }

    public char [] reverseIndex ( char [] input, int start, int stop){

        int o = start;
        int p = stop;

        while (o < p){

            input = this.swap(input, o, p);
            o++;
            p--;
        }

        return input;
    }

    public String reverseAll(char [] input){

        //reverse all
        input = this.reverseIndex(input, 0, input.length -1);

        int start = 0;

        //reverse words
        for (int n = 0; n < input.length; n ++){
            if (input [n] == ' '){
                input = this.reverseIndex(input, start, n -1);
                start = n + 1;
            }
        }

        return new String(input);
    }


    public static void main (String args[]){
        ReverseWords reverseWords = new ReverseWords();

        String input = "bob is so good";

        char [] chars= input.toCharArray();

        String result = reverseWords.reverseAll(chars);

        System.out.println(result);
    }

}
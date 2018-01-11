/*
    Problem: 
            Starting with "1", write an algorithm that uses the previous result to generate the next 
            There is 1 "1" so the result is "11";

            there is  2233 result is there are 2 "2" and 2 "3" thus answer is "2223"

            Iterate through previoous result, and construct the enxt one;

*/

public class Look {
    public String getNext (String prev){
        String result = "";

        char current = prev.charAt(0);
        int counter = 1;

        for (int i = 1; i < prev.length(); i ++){

            if (prev.charAt(i) != current){
                result += Integer.toString(counter) + current;
                
                current = prev.charAt(i);
                counter = 1;
            } else {
                counter ++;
            }

        }

        result += Integer.toString(counter) + current;

        return result;
    }

    public String get_k (int k){

        String start = "1";

        for (int i = 0; i < k; i ++){
            start = this.getNext(start);
        }

        return start;

    }

    public static void main (String args[]){
        Look look = new Look();

        String result = look.get_k (3);

        System.out.println(result);
    }
    
}
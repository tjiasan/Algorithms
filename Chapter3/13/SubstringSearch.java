/*
    Problem: find a good way to search a substring within a text;

    Solution: Running window of rolling hash;

      1) inefficient rolling hash algorigthm, multply by 31, add subtract
      2) Rubin Karp fingerprint :
    

        assuming 3 length

        a * 26 ^2 + b * 26 ^ 1 + c * 26 ^0 ;

        to get the next number  assuming, the next is d ,
        subtract a*26^2
        multiply everything by 26;
        add d * 26^0; 

*/

import java.util.LinkedList;
import java.util.Arrays;

public class SubstringSearch {
    
    public int sum_string (String sub){
        int sum = 0;

        for (int i = 0; i < sub.length(); i ++){
            int add = sub.charAt(i) * 31;          
       
            sum += add;
        }
        return sum;

    }

    public int modify_sum (int running, char input, char del){

        running +=  input * 31;
        running -=  del * 31;

        return running;
    }

    public int search (String text, String searchstring){
        int index = 0;

        int running_sum = this.sum_string(text.substring(0, searchstring.length()));
        int search_sum = this.sum_string(searchstring);


        LinkedList <Character> queried_string = new LinkedList <Character>();

        for (int n = 0; n < searchstring.length(); n ++){
            queried_string.addLast(text.charAt(n));
        }
       
        for (int i = searchstring.length(); i <= text.length(); i ++){
            
            //verify anti-collission
            if (running_sum == search_sum){    
                System.out.println(Arrays.toString(queried_string.toArray()));            
               
                boolean found = true;
                for (int k = 0; k < searchstring.length(); k ++){
                    if (searchstring.charAt(k) != text.charAt(k + i - searchstring.length())){
                        found = false;
                    }
                   
                }
                if (found){       
                    System.out.println("found");             
                    return i;
                }
                
            }        


            if  (i == text.length()) {
                break;
            }

            char del = queried_string.removeFirst();
            char input = text.charAt(i);
            queried_string.addLast(input);

            running_sum = this.modify_sum (running_sum, input, del);

        }

      

        return index;

    }

    public static void main (String args[]){

        String text = "This is a text that I just made up so i can search this.";
        String search = "search";

        SubstringSearch sub = new SubstringSearch();

        int index  = sub.search(text, search);

    }
}
/*

    Knuth-Morris-Pratt

    1) Preprocess search string to get self prefix matches;
    2) use knowledge of these matches to reset naive search to
       known location if mismatch;

    3) time complexity O (n + m), where m is the string;


    my preprocessing algo: worst case o(m);


    what happens when mismatch?
    if everything is 0 in the prefix table,
    safe to assume skip to mismatch position with naive algo

    if there is a 1 in the prefix table position 3, jump 3 positions and restart from 1;

    so formula where if mismatch at nth position at the array, jump to that array,
    restart matching from the number at the value of the array;
    thus, start position is (old + n - value);

    it doesn't matter if the match table is 0012301 and if mismatch at 7, because 
    if restart from 3, we already know it's going to mismatch;

    Complexity roughly O (m + n);

*/
import java.util.Arrays;
public class SubstringKMP {

    public int[] preprocess(String  search){

        int [] location = new int [search.length()];
        // {prefix_length, last known offset};

        int max; 

        location[0] = 0;

        for (int i = 1; i < search.length(); i++){
            int counter = 0;
            for (int k = 0; k < search.length() -i ; k++){
                if (search.charAt(k) == search.charAt(k + i)){
                    counter ++;
                    location [i + k] = counter;
                    
                } else {
                    break;
                }

            }
            i += counter;
            counter = 0;

        }        

        return location;
    }

    public int find (String text, String search, int[] location){

        int search_index = 0;
        int offset = 0;
        for (int i = 0; i < text.length(); i ++){
            
            for (int k = offset; k < search.length(); k++){
                
                System.out.println(text.charAt(i + k));
                if (text.charAt(i + k) == search.charAt(k)){                  
                    search_index ++;              
                } else {
                    break;
                }
            }
            offset = 0;
       
            if (search_index == search.length()){
                System.out.println("FOUND");
                break;
            }

            if (search_index > 0 ) {
                offset = location [search_index]; // location of start search -1, can't go below zero
                i += search_index - offset; //it adds 1 in next iteration
                if (offset > 0){
                    offset -= 1;   
                }

                search_index = offset;  // has offset -1 matches (as above)
                
            } else {
                search_index = 0;  
            }
            
            
        }

        return 0;
    }


    public static void main (String args[]){
        SubstringKMP sub = new SubstringKMP();

        String text = "abcabcFabcabcabcabc";
        String search = "abcabcabcabc";

        int[] locations = sub.preprocess (search);
        System.out.println(Arrays.toString(locations));

        sub.find(text, search, locations);
    }   
}
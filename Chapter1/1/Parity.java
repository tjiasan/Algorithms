
/*

Computing the parity of a word
Problem: In computing, the word integrity is checked by computing its parity (either odd or even number of 1s)
        How do you compute the parity of a byte in less than O(n) operations?

Solution: The parity of a word is equal to the parity of another word computed from (bits of word from first half ^ bits of word from second half);
        eg. 1001 0010 parity (odd) is equal to 
            1001 ^ 0010 = 1011; (odd)
            repeat until word length = 8, where you can use lookup table to be constant time;

*/
import java.util.HashMap;

public class Parity {
    public HashMap <Integer, Integer> LookupTable;

    public int BruteForceCompute(int x) {
        int result = 0;

        while (x > 0) {
            //flips result if different then least significant bit;
            result ^= (x & 1);        
            x >>= 1;
        }
        return result;
    }

    public int XORCompute (int x) {
        int word_length = 32;

        while (word_length > 8) {
            x ^= (x >> word_length/2);
            word_length /= 2; 
        } 

        //bitmask to zero for all but the least significant 8;
        int bitmask = Integer.parseInt("FF", 16);
        return this.LookupTable.get(x & bitmask);
    }
    
    public void InitializeLookupTable() {
        this.LookupTable = new HashMap<Integer, Integer>(); 
        for (int i = 0; i < 256; i ++){
           this.LookupTable.put(i, this.BruteForceCompute(i));
        }       
    }

    public static void main (String args[]){   

        Parity parity = new Parity();

        // initialize LookupTable;        
        parity.InitializeLookupTable();
        
        int input = Integer.parseInt(args[0]);
        int result;

        result = parity.XORCompute(input);

        System.out.println("XOR computed parity is : " + result);
        System.out.println("Brute force computed parity is: "+ parity.BruteForceCompute(input));

    }
}
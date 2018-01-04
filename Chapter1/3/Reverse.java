/*    Problem: Given an input in 64 bits, reverse the input as the output, must make sure all inputs and vars are 64 bits;
 *    Solution: Use a Lookup table to reverse bits at 16 bit blocks at a time;
 *              
 */
import java.util.HashMap;

public class Reverse {

      public HashMap <Long, Long> LookupTable  = new HashMap <Long ,Long> ();  

      public void ReverseBits(long input){
           long result = 0;   

           for (int i = 0; i < 4; i++){
              long bitmask = Long.parseLong("FFFF", 16);
              long intermediate = (input >> i * 16) & bitmask;  

              int shift = (3 - i) * 16;           
              result |= (this.LookupTable.get(intermediate) << shift );
           }

           String in = String.format("%16s", Long.toBinaryString(input)).replace(' ', '0');
           String out = String.format("%16s", Long.toBinaryString(result)).replace(' ', '0');
           System.out.println(in);
           System.out.println(out); 

      }

      //modified to accept longs
      public long SwapBits(long word, int i, int j){
          long bit1 = (word >> i) & 1;
          long bit2 = (word >> j) & 1;
          long one = 1;
          if (bit1 != bit2) {
             long bitmask = (one << i) | (one << j);  
                
             return word ^ bitmask;
          } else {
            return word;
          }
      }

     //swap only up to 16 bit words
      public void initializeLookupTable() {
        for (long i = 0; i < 65536; i++){
            long reversed = i; 
            for (int n = 0; n < 8; n ++) {
                reversed = this.SwapBits(reversed, n, 15 - n);
            } 
            this.LookupTable.put(i, reversed);          
        }
      }

      public static void main (String args[]){ 
           Reverse reverse = new Reverse();

           reverse.initializeLookupTable();
           long input = 5665165114441231315L;
           reverse.ReverseBits(input);
      }
}
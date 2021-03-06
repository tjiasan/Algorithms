/*    Problem: Swap bits inside a 64 bit word given two indexes
 *    Solution: 
 *              extract bits (AND), if different flip using bitmask (1 bitshift)
 *               flip using XOR
 */

public class Swap {

      public void SwapBits(int word, int i, int j){
          int bit1 = (word >> i) & 1;
          int bit2 = (word >> j) & 1;

          if (bit1 != bit2) {
             int bitmask = (1 << i) | (1 << j);  
            
             System.out.println("original :" + Integer.toBinaryString(word)); 
             System.out.println("bitmask  :" + Integer.toBinaryString(bitmask)); 
             System.out.println("result   :" + Integer.toBinaryString(word ^ bitmask)); 
          } else {
            System.out.println("not swapped" + Integer.toBinaryString(word)); 
          }

      }
      public static void main (String args[]){ 
            Swap swap = new Swap(); 

            swap.SwapBits(4, 2, 31);

      }
}
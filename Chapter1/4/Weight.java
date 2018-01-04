/*
Problem : Given a binary integer, assuming that the binary is not all 1s or 0s, find
a binary with the same number of 1s (same weight), but also has the lowest difference

Solution:
    1) Brute Force: O(n), loop through the whole 64 bits, and if find (01) or (10), swap the two bits
    2) Smarter way: O(1), find least significant digit with input & -input, if lsb is at position 0, then find least significant zero,
       how? flip everything using mask, and then repeat;

*/
import java.util.HashMap;
public class Weight {

    public HashMap <Integer, Integer> AllOnes = new HashMap <Integer, Integer> (); 
    public HashMap <Integer, Integer> SingleOnes  = new HashMap <Integer, Integer> (); 

    public void Swap (int input, int i, int j){
        return;
    }

    public void FindLowest(int input) {
        
        int position;

       
       //find least significant bit;
        int lsb = input & (input * -1);

        String hex = "FFFFFFFF";
        int flip_mask = (int) Long.parseLong(hex, 16);

        if (input == 0 || input == flip_mask) {
            // input all 1s or all zeros;
        }

        int position_lsb = this.SingleOnes.get(lsb);
    
        int lsb_mask = flip_mask << position_lsb;

        //find least significant zero;
        int all_flip = input ^ flip_mask & lsb_mask;
        
        int lsz = all_flip & (all_flip * -1);
        int position_lsz = this.SingleOnes.get(lsz);
        int position_to_swap = position_lsz - 1;
        System.out.println("input             : " + Integer.toBinaryString(input));
        System.out.println("flip mask         : " + Integer.toBinaryString(flip_mask));
        System.out.println("lsb_mask          : " + Integer.toBinaryString(lsb_mask));
        System.out.println("pos lsb           : " + position_lsb);
        System.out.println("pos lsz           : " + position_lsz);

        // work out logic here
        // if lsb == 0, means there are leading 1s 1110111 for example, so we use lsz
        if (lsb == 0) {
            System.out.println("Position of swap  : " + position_lsz + " and  one before it" );
        } else {
            //there are no leading 1s   100010, swap the lsb with one before it;
            System.out.println("Position of swap  : " + position_lsb + " and  one before it");
        }       
    
    }

    public void InitializeLookupTable () {
    
        String add = "1";
        String current = "1";
        for (int i = 0; i < 31; i ++){
           this.AllOnes.put(i, Integer.parseInt(current, 2));
           current += add;
        }
       
        add = "0";
        current = "1";
        for (int n = 0; n < 31; n ++){
            this.SingleOnes.put(Integer.parseInt(current, 2), n);
            current += add;
        }   
    }

    public static void main (String args[]){ 
       
  
        Weight weight = new Weight();

        weight.InitializeLookupTable();

        weight.FindLowest(444);
    }
}
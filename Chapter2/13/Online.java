/* Problem : Given a stream of packets, generate a random subset of size k
    Solution: New Packets have a k/n probabilty of staying, if it is staying, the one of the original subset have a (1/k) 
                probability of being replaced by the new one;

    Proof: Assume there is a size k of 10,

          if n = 11, the new packet has a 10/11 probability of staying
                     the original packet has a 1 - 10/11 * 0.1  === 1 - 1/11 = 10/11;

          if n = 12, the new packet has a 10/12 probability of staying OR 5/6;
                    while the original has a (1 - 10/12  * 0.1)* previous =====  11/12 * 10/11 = 110/132 = 5/6 ;                  

*/
import java.util.Arrays;
import java.util.Random;


public class Online {

    public int[] generate (int[] orig, int k, int n, int next_val ){
        Random rand = new Random();
        int value = rand.nextInt(n + 1);

        boolean stay;

        if (value < k){
            int position = rand.nextInt(k);
            orig[position] = next_val;        
        } 

        return orig;
    }

    //simulate streaming
    public void stream(){

        int [] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18}; //stream from 10
        int [] orig =  {0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }; //original 10 values, for size k 10

        int iterations = arr.length - orig.length - 1;

        for (int i = orig.length; i < arr.length; i++) {
             this.generate(orig, 10, i, arr[i]);    
        }

        System.out.println(Arrays.toString(orig));
        

    }



    public static void main (String args[]){

        Online online = new Online();

        online.stream();

    }
}
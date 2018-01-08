
import java.util.Arrays;
public class Prime {

    public boolean [] makePrime (int limit) {
        boolean [] result = new boolean [limit + 1];

        Arrays.fill(result, true);
        result[0] = false;
        result[1] = false;

        for (int i = 2; i < limit; i++) {
            if (result[i] == true){
                int start = i * 2;
                for (int k = start; k <= limit; k += i){
                    result[k] = false;
                }
            }
        }

        return result;


    }

    public static void main (String args[]) {
        Prime prime = new Prime();

        boolean [] result = prime.makePrime(10);

        System.out.println(Arrays.toString(result));
    }
}
/*
    Problem: calculate popwer a^b using binary
    Solution: 1) brute force, multiply a by b times;
              2) saved progress, save previous multiplcations

                e.g. problem : 2 ^ 7; 7 in binary is 111; 
                    7 is decomposed into   2^2 +2^1 + 1  OR 4 + 2 + 1;
                and 2^3 decomposed into  2^2 * 2^1;
                to get from 2^1 to 2^2, multiply 2^1 by itself;
*/

public class Power {

    public int CalculatePower (int a, int b){
        int power = 1;

        int current = a;

        while (b > 0) {
            if ((b & 1) == 1) {
                power *= current; 
            }
            current = current*current; 
            b >>= 1;
        }
        System.out.println(power);
        return power;


    }

    public static void main (String args[]){

        Power power = new Power();
        power.CalculatePower(3, 2);
    }

}
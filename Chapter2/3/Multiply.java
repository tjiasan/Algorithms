/*
Problem: Multiply arbitrary precision integers;
Solution: loop through lowest multiplier, 
    Keep track of the offsets, to figure out the 10 ^ offset of the multiplication product
    initialize zero array of the mid result
    and add product to final result;

*/
import java.util.Arrays;
public class Multiply {

    public int [] Add (int[] result, int[] add) {
        int carryover = 0;
        int index = result.length -1;
        for (int k = add.length -1; k >= 0; k--){
            result[index] += add[k] + carryover;

            carryover = 0;
            if (result[index] > 10){
                result[index]= result[index] %10;
                carryover = 1;
            }

            index --;
        }

        while (carryover > 0) {

            result [index] += 1;

            if (result[index] < 10){
                carryover = 0;
            }
            index --;
        }

        return result;

    }

    public int[] Multiply_arb (int[] a, int[] b){
        int result_size = a.length + b.length + 2;
        int[] result = new int [result_size];

        int counter = 0;
        for (int i = b.length -1; i >= 0; i -- ){
            int counter1 = 0;
            for (int n = a.length -1; n >= 0; n--){
               int multiply = a[n] * b[i];
               int offset = counter + counter1;// calculate power 10 of result

               int [] add = new int[offset + 2];
               Arrays.fill(add, 0);
               
               add[1] = multiply %10;

               multiply /=10;
               if (multiply > 0){
                   add[0] = multiply %10;
               }

               result = this.Add(result, add);

               counter1++;
            }

            counter ++;
            
        }

        return result;
    }

    public static void main (String args[]){
        int [] a = {1, 2, 7};
        int [] b = {2, 3};

        Multiply multiply = new Multiply();
        int[] result = multiply.Multiply_arb(a, b);

        System.out.println(Arrays.toString(result));
        //answer 2921;
    }
}
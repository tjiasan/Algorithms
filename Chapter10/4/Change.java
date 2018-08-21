/*
    Problem: given an array of coins,
            return the smallest integer where coins cannot be summed up to;


            e.g. {1, 1, 1, 1, 1, 5, 10, 25 }


            cannot make 21;


   Solution: Sort the array in ascending order;

            the next coin must be able to replace partial or full amount of the sum of all previous coins + 1, 
            or else, prev sum + 1 is impossible;

            iterate through the coins:


            if (there isn't a 1 at A[0], ){
                return 1;
            }


            else {
                next impossible =  previous sum + 1; //array up to now + 1;

                if next digit is more than impossible,
                then return impossible;

                else sum += current digit;
            }



*/

import java.util.Arrays;

public class Change {

    int not_possible (int[] inventory){

        Arrays.sort(inventory);

        if (inventory[0] != 1){
            return 1;
        }

        int sum = 1;

        for (int i = 1; i < inventory.length; i++){
            int next = sum + 1;

            if (inventory[i] > next ){
                break;
            } else {
                sum += inventory[i];
            }

        }
        System.out.println(sum + 1);
        return sum;

    }


    public static void main (String args[]){

        Change change = new Change();

        int[] inventory = {1, 1, 1, 1, 1, 5, 10, 25 };

        change.not_possible (inventory);


    }


}
/* Problem: Given an array of coins
            where two players can choose the last 2 coins, 
            skipping a turn is not possible, 

            design an algorithm where 
            player 1 can choose max coins;

*/

class Result {
    public int player0;
    public int player1;
}

public class Coins {


    public Result brute_force (int[] coins, int index1, int index2, int player){

        if(index2 - index1 == 1){
            Result res = new Result();
            int min = Math.min(coins[index1], coins[index2]);
            int max = Math.max(coins[index1], coins[index2]);

            if (player == 0){
                res.player0 = max;
                res.player1 = min;
            } else {
                res.player0 = min;
                res.player1 = max;
            }
            return res;
        }

        Result result1 = this.brute_force(coins, index1 + 1, index2, player ^ 1);
        if (player == 0){
            result1.player0 += coins[index1];
        } else {
            result1.player1 += coins[index1];
        }
       
  

        Result result2 = this.brute_force(coins, index1, index2 - 1, player ^ 1);
        if (player == 0){
            result2.player0 += coins[index2];
        } else {
            result2.player1 += coins[index2];
        }
   

        if (player == 1){
            if(result1.player1 < result2.player1){
                return result2;
            } else {
                return result1;
            }
        } else {
            if(result1.player0 < result2.player0){
                return result2;
            } else {
                return result1;
            }
        }

    }
    
    

    public static void main (String args[]){

        int[] input = { 25, 5, 10, 5, 10, 5, 10, 25, 1, 25, 1, 25, 1, 25, 5, 10 };
        //int [] input = { 1, 25, 10, 5 };
        //10, 25, 25, 25, 25, 10, 10, 25
        Coins coins = new Coins();

        Result max = coins.brute_force(input, 0, input.length - 1, 0);
        System.out.println(max.player0);
        System.out.println(max.player1);

    }
}
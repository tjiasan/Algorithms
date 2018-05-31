/*
    Problem: Determine the max profit you can get if you can buy and sell twice;
    Solution: Keep track of negative movements within  days reaching to the max profit, and keep track of two max profits ,
             If breaking up one large maximum profits results in a larger profit than the two original ones, do that instead;


             2N solution: 1) run buysell once, get biggest, 
                         2) run buysell once inside biggest window,
                         3) run buysell once outside biggest window, 
*/


public class BuySell2 {

    public void MaxProfit (int [] arr){
        int current = arr[0];
        int current_index = 0;
        int current_profit = 0;
        int current_lowest = 1;

        int current_negative = arr[0];
        int current_negative_index = 0;
        int current_negative_amount = 0;
        int current_negative_start = 0;
        int current_negative_stop = 0;

        int max_profit1 = 0;
        int buy1 = -1;
        int sell1  = -1;
        int negstart1 = -1;
        int negstop1 = -1;
        int negamount1 = 0;

        int max_profit2 = 0;
        int buy2 = -1;
        int sell2 = -1;
        int negstart2 = -1;
        int negstop2 = -1;
        int negamount2 = 0;

        for (int i = 1; i < arr.length; i++){
            if (arr[i] < current){
                current = arr[i];
                current_index = i;
                current_profit = 0;
                
                //reset
                current_negative = arr[i];
                current_negative_index = i;
                current_negative_amount = 0;
                current_negative_start = 0;
                current_negative_stop = 0;

                if (max_profit1 > max_profit2){
                    current_lowest = 2;
                } else {
                    current_lowest = 1;
                }
            }

            if (arr[i] > current_negative) {
                current_negative = arr[i];
                current_negative_index = i;
            }

            if (arr[i] - current_negative  < current_negative_amount){
                current_negative_amount = arr[i] - current_negative;
                current_negative_start = current_negative_index;
                current_negative_stop = i;
            }

            current_profit = arr[i] - current;            
                
            if (current_lowest == 2){
                if (current_profit > max_profit2){
                    buy2 = current_index;
                    sell2 = i;
                    max_profit2 = current_profit;

                    negstart2 = current_negative_start;
                    negstop2 = current_negative_stop;
                    negamount2 = current_negative_amount;
                }
   
            } else {
                if (current_profit > max_profit1){
                    buy1 = current_index;
                    sell1 = i;
                    max_profit1 = current_profit;

                    negstart1 = current_negative_start;
                    negstop1 = current_negative_stop;
                    negamount1 = current_negative_amount;
                }        
            }       
        }

        int total_profit = max_profit1 + max_profit2;

        int break_1 = max_profit1 - negamount1;
        int break_2 = max_profit2 - negamount2;     

        if (Math.max(break_1, break_2) > total_profit){
            if (break_1 > break_2) {
                sell2 = sell1;
                buy2 = negstop1;
                
                sell1 = negstart1;
            
            } else {         
                buy1 = buy2;
                sell1 = negstart2;

                buy2 = negstop2;      
            }
        }



        System.out.println(buy1);
        System.out.println(sell1);
        System.out.println(buy2);
        System.out.println(sell2);


    }

    public static void main (String args[]){

        BuySell2 buysell = new BuySell2();

        int [] arr = { 1, 10, 7, 17, 7, 24, -1, 5 };

        buysell.MaxProfit(arr);

    }
}
public class BuySell1 {

    public void BuySell (int[] arr){
        int current = arr[0];
        int current_index = 0;
        int max_profit = 0;
        int buy = -1;
        int sell = -1;

        for (int i = 1; i < arr.length; i++){

            if (arr[i] < current){
                current = arr[i];
                current_index = i;
            }

            if (arr[i] - current > max_profit) {
                buy = current_index;
                sell = i;
                max_profit = arr[i] - current;
            }

        }

        System.out.println(buy);
        System.out.println(sell);


    }

    public static void main (String args[]){

        BuySell1 buysell = new BuySell1();
        int [] arr = {0, 3, 2, 5, -20, 2 };
        buysell.BuySell(arr);

    }
}
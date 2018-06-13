/*

Solution: 
    the algorithm is iterative and recursive, recursing
    n - 1 subset down and sideways. 
    it does a max comparison inside the loop rather than coming out of the loop/recursive

    time complexity is n*w for n-1 each n -1 iteration, estimated n^2 * w^2,
    , space complexity is stack depth which at worst is n if the answer is n
    

    optimization: before jumping in for loop, 
    can sort by weight + do binary search to get the right starting weight,
    depends on the data distribution (high spread), can 
    be helpful, 
*/



import java.util.Stack;
import java.util.Iterator;
import java.util.Arrays;


public class Knapsack {

    public int max_value;
    public int[] max_items;

    public Knapsack (){
        max_value = 0;
        max_items = new int[0];
    }

    public void max (int[][] items, int weight, int value, int index, Stack<Integer> bag, int max_weight){
        
        if (weight + items[index][1] <= max_weight){
            bag.push(index);

            if (value + items[index][0] > max_value){
                max_value = value + items[index][0];
               
                max_items = new int[bag.size()];

                Iterator item_iterator = bag.iterator();

                int counter = 0;
                while(item_iterator.hasNext()){
                    max_items[counter] = (int) item_iterator.next();
                    counter++;
                }
                

            }
        
            for (int i = index + 1; i < items.length; i ++){
    
                this.max(items, weight + items[index][1], value + items[index][0], i, bag, max_weight);             

            } 

            bag.pop();

        }


    }

    public void find (int[][] items, int max_weight){
        Stack<Integer> bag = new Stack<Integer> ();

        for (int i = 0; i < items.length; i ++){
            this.max(items, 0, 0, i, bag, max_weight);
        }
        System.out.println(max_value);
        System.out.println(Arrays.toString(max_items));
    }

    public static void main (String args[]){
        
        int [][] items = {
            { 65, 20 },//a
            { 35, 8 },
            { 245, 60 },
            { 195, 55 },
            { 65, 40 },
            { 150, 70 },
            { 275, 85 },
            { 155, 25 }, //H
            { 120, 30 },
            { 320, 65 }, //J
            { 75, 75 },
            { 40, 10 },
            { 200, 95 },
            { 100, 50 },
            { 220, 40 },//O
            { 99, 10 }
         }; 
    

         Knapsack knapsack = new Knapsack();
         
         knapsack.find(items, 130);
        
         
        
    }

}
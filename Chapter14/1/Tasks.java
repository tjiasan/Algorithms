/* 
    Problem: 
        Each worker must be assigned exactly two tasks
        tasks are independent, 
        any task can be assigned to any worker,
        tasks take time to complete

      Solution: 
        Sort, tasks by time
        
        pair last with first in greedy way

 */       

import java.util.Arrays;
public class Tasks {

    public void assign(int[] assignments){
        Arrays.sort(assignments);

        int i = 0;
        int j = assignments.length -1;
        while(i < j){
            System.out.println("worker:");
            System.out.println(assignments[i]);
            System.out.println(assignments[j]);
            i ++;
            j--;
        }


    }

    public static void main (String args[]){

        Tasks task = new Tasks();

        int [] assignments = {1, 5, 4, 4, 6, 2};


        task.assign(assignments);


    }
}
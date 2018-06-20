/*
    Problem: 
        Given a set of tasks happening on the floor

        e.g. [0,3], [2, 6], [3,4],[6,9]

        minimize the amount of visits such that all tasks are visited
        ans visit [0, 2, 3, 6]
        [3, 6] also cover all tasks


        Solution: 

        Start at lowest, pick highest of the lowest interval,

        iterate through list as long as the highest is in the interval, 

        if not, add the highest in the next one,
*/
import java.util.LinkedList;
import java.util.Arrays;

public class Interval {

    //assuming sorted list by first number, if equal then second number
    public void visit (int[][] tasks) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        result.addLast(tasks[0][1]);
        
        int last = tasks[0][1];
       
        for (int i = 1; i < tasks.length; i ++){
            if (last >= tasks[i][0] && last <= tasks[i][1]){
               
                continue;
            } else {
                last = tasks[i][1];
                result.addLast(tasks[i][1]);
                
            }
        }


        while(result.isEmpty() == false){
            System.out.println(result.removeFirst());
        }
    }

    public void visit_unsorted (int[][] tasks) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        result.addLast(tasks[0][1]);
        Arrays.sort(tasks, (a, b) -> Integer.compare(a[0], b[0]));
        int last = tasks[0][1];
       
        for (int i = 1; i < tasks.length; i ++){
            if (last >= tasks[i][0] && last <= tasks[i][1]){
               
                continue;
            } else {               
                int min = tasks[i][1];
                int counter = 1;
                last = tasks[i][1];

                while(i + counter < tasks.length){               
                    if (tasks[i][0] != tasks[i + counter][0]){
                        break;
                    } else {
                        if (tasks[i + counter][1] < min){
                            last = tasks[i + counter][1];
                        }
                    }
                    counter ++;
                }

                i += counter - 1;
                result.addLast(tasks[i][1]);
                
            }
        }


        while(result.isEmpty() == false){
            System.out.println(result.removeFirst());
        }
    }


    public static void main (String args[]){

        Interval interval = new Interval();


        int[][] tasks = {
            {0, 3},
            {2, 6},
            {3, 4},
            {6, 7},
            {6, 9}
           
        };

        interval.visit(tasks);
    }
}
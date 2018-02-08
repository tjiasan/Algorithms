/* Problem: Given A list of test scores and ids,
            calculate the top 3 average test score of a student;
            return the student with the highest set of top 3 scores;
            Ignore students with less than 3 scores

    Solution : Use a hashmap to store a heap of 3 size
    for all students;

    maintain min heap; 
        if a new score comes in, if less than 3,
         put in heap arr[0], heapify

         if at 3, compare with arr[0], if more,
            override arr[0], heapify;

    iterate through hashmap,
    find max avg

    Time O(N), Space O(N) //max space is number of students;


*/          
import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;

import java.util.Comparator;

class Node {
    public int[] scores;
    public int id;
    public int size;

    public Node () {
        scores = new int[3];
        id = 0;
        size = 0;
    }
}




public class ComputeTop{

    public int[] swap (int[] arr, int i, int j){
        int tmp = arr[i];

        arr[i] = arr[j];

        arr[j] = tmp;

        return arr;
    }

    public void compute (int[] [] scores){

        HashMap<Integer, Node> top = new HashMap<Integer, Node> ();

        for (int i =0; i < scores.length; i ++){
            Node student;
            if (top.get(scores[i][0]) == null){
                student = new Node();
                student.id = scores[i][0];
                student.scores[0] = scores[i][1];
                student.size = 1;
                top.put(student.id, student);

            } else {
                student = top.get(scores[i][0]);
                if (student.size < 3){
                    student.scores[0] = scores[i][1];
                    student.size ++;

                } else {
                    if (student.scores[0] < scores[i][0]){
                        student.scores[0] = scores[i][1];
                    }
                }         
                
            }

            //maintain min heap
            if (student.scores[0] > student.scores[1]){
                student.scores = this.swap(student.scores, 0, 1);
            }
            if (student.scores[0] > student.scores[2]){
                student.scores = this.swap(student.scores, 0, 2);
            }
        }

        ArrayList<Integer> keys = new ArrayList<Integer>(top.keySet());

        int max = 0;
        int max_id = -1;
        for (int i = 0 ; i < keys.size(); i ++){
            Node student = top.get(keys.get(i));
      
            if (student.size < 3){
                continue;
            }
            int avg = student.scores[0] + student.scores[1] + student.scores[2];
            System.out.println(avg);
            avg /= 3;
            
            if (avg > max){
                max_id = student.id;
                max = avg;
            }
        }   

        System.out.println(max_id);


    }


    public static void main (String args[]){

       ComputeTop computeTop = new ComputeTop();

       int [] [] input = {
           {1, 20},
           {1, 30},
           {1, 40},
           {2, 52},
           {2, 90},
           {2, 70},
           {3, 40},
           {3, 90},
           {3, 90},
           {5, 70},
           {5, 90},
           {5, 90},
           {4, 100}
       };

       computeTop.compute(input);

    }


}
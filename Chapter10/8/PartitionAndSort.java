/*
    Problem : Given an array of objects,
                partition the array such that the objects that have similar
                property (Student.age) appear together.


    Solution: Space O(N), Time O(N);
            count all similar elements (age) of the array O(N) time O(N)space with hashmap;

            iterate through hashmap and assign starting popsition in new array;

            iterate througgh all students again;
                find the position it should be in the new array;
                put it in that position;
                increase position;

            if Don't want extra space to initialize result array, and use swaps;
            
                3rd step
                    encounter student;
                      swap to final pos (get final from table);
                      if not where it's supposed to be, swap to the final pos again;
                      until true;
                      this uses O(N) extra time, but o(H) space, where H is distint elements;

           bst is counting sort;           

           since swapping objects,
           swap all internal values.
           
*/
import java.util.HashMap;
import java.util.ArrayList;

class Student {
    public int age;

    public Student(int a){
        age = a;
    }
}

class Counter {
    public int count;
    public int current;
}

public class PartitionAndSort{


    public Student[] partition(Student[] students){

        HashMap<Integer, Counter> counts = new HashMap<Integer, Counter> ();

        for (int i = 0; i < students.length; i++){
            if (counts.get(students[i].age) == null){

                Counter count = new Counter();
                count.count = 1;

                counts.put(students[i].age, count);

            } else{
                Counter count = counts.get(students[i].age) ;
                count.count++;                
            }        
        }

        ArrayList<Integer> keys = new ArrayList<Integer>(counts.keySet());

        int pos = 0;
        for (int i =0; i < keys.size(); i++){
            Counter count = counts.get(keys.get(i));
            count.current = pos;
            pos+= count.count;
        }
        
        Student[] result = new Student[students.length];

        for (int i = 0; i < students.length; i++){
            Counter count = counts.get(students[i].age);

            result[count.current] = students[i];

            count.current++;
        }

        //checking
      
        for (int i = 0; i < students.length; i++){
            System.out.println(result[i].age);
        }

        //alternate solution
        /*
        for (int i = 0; i < students.length; i++){

            while(true){
                Counter count = counts.get(students[i].age);

                if (count.current > i){
                    students = this.swap(students, i , count.current);
                    count.current ++;
                } else if (count.current == i){
                    count.current ++;
                }
                else {
                    break;
                }


            }           

        }       
       */
      
        return result;

    }

    public static void main (String args[]){
        PartitionAndSort part = new PartitionAndSort();

        Student[] students = new Student[5];

        students[0] = new Student (3);
        students[1] = new Student (2);
        students[2] = new Student (1);
        students[3] = new Student (3);
        students[4] = new Student (1);

        students = part.partition(students);


    }

}
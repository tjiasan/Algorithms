/*
    Problem: the CEO of a company wants to set a budget for payrolls;

            e.g. 210k, 

            compute a cap such that, salaries above the cap are trimmed to the cap
            and the sum of the salaries equal the budget;

    Solution:
            compute sum;
            
            sort salaries;


            calcuate savings needed = sum - budget;


            starging backwards from highest salary;

            person affected = 1;

            difference between the next person is how much you can change without affecting
            the next person; 

            so possible savings = fixed + affected * difference;// fixed + variable;
                if greater than savings, then
                    calcualte thee exact diff needed in variable, 
                    and cap is current - exact; 
            
            fixed += difference * affected; // cant make savings so sum all bars, affected at iteration;

            affected ++;

            Complexity O(Nlogn) + O (N), space O(1);

            book solution:

            binary search, 
                sort salaries

                create an O(N) space array of the sum of salaries, 
                if the cap was at that i iteration,

                do binary search for just less ,
                calculate exact;
                nlogn +n time and n space;


*/
import java.util.Arrays;
public class Payroll{
    
    public int cap (int[] salaries, int budget){
        Arrays.sort(salaries);
        int sum = 0;

        for (int i = 0; i < salaries.length; i++){
            sum += salaries[i];
        }
        int cap = 0;
        int save = sum - budget;
        int fixed = 0;
        int affected = 1;
        
        for (int i = salaries.length - 1; i >= 0; i --){
            int diff = salaries[i];
            if (i != 0){
                diff -= salaries[i - 1];
            }           

            if (diff * affected + fixed > save){               
                int exact = (save - fixed)/ affected;
                cap = salaries[i] - exact;
                break;
            }
            fixed += diff * affected;
            affected ++;
           
         
        }

        System.out.println(sum);
        System.out.println(cap);



        return cap;     


    }


    public static void main (String args[]){

        int[] salaries  = {90, 30, 100, 40, 20};

        int budget = 150;

        Payroll payroll = new Payroll();


        payroll.cap(salaries, budget);

    }

}
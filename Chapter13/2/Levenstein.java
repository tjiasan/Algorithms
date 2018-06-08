/*
    Recursive brute force method for calculating levenstein V1

    each spawns 

    Complexity 3 ^ n where n string length;


    could use indexes to save a bit of space, but space complexity doesn't change

    correct with book answer
    except for 1 condition, if same prefix, skip character, no need to explore deletion/insertion

    
*/


public class Levenstein {


    public int calculate(String a, String b, int distance){

  
        if(a.length() == 0) {    
            //insertions      
            return distance + b.length();     
        }
        if (b.length() == 0){        
            //insertions
            return distance + a.length();
        }


        char a_prefix = a.charAt(0);
        char b_prefix = b.charAt(0);

        
        if (a_prefix == b_prefix){         
            return this.calculate(a.substring(1), b.substring(1), distance);
        } 

        int substitution = 0;
        int deletion = 0;
        int insertion = 0;


        // deletion from b
        deletion =  this.calculate(a, b.substring(1), distance + 1);

        substitution = this.calculate(a.substring(1), b.substring(1), distance + 1);

        // deletion from a
        insertion =  this.calculate(a.substring(1), b, distance + 1);

        int min = Math.min(deletion, substitution);

        return Math.min(insertion, min);
   
        
    }

    public static void main (String args[]){

        Levenstein lev = new Levenstein();

        int dist = lev.calculate("Saturday","Sundays" , 0);
        System.out.println(dist);
    }
}
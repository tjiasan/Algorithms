
/*
    Problem: Given a name, e.g. "bedbathandbeyond",
            and a dictionary of words (think english dictionary),

            find decompositions of the name that translates to words:

            e.g. bed bath beyond,
                bed bat hand beyond,
                
      Solution: find all dictionary words within the name 
      
                n ^2/2 time complexity O (1) space, 

                use hashmap as a result mapping start of where each word is in a hashmap;


                re-stitch the result 

                O(N*W) 


*/


import java.util.HashMap;
import java.util.Arrays;
import java.util.Stack;
import java.util.Iterator;

class Result {
    public int end;
    public Stack<String> wordlist;

    public Result(){
        end = 0;
        wordlist = new Stack<String>();
    }
}

public class BedBath {

    public HashMap<String, Boolean> dictionary;
    public HashMap<Integer, Stack<String>> words;

    public BedBath(){
        dictionary = new HashMap<String, Boolean> ();
        words = new HashMap<Integer, Stack<String>> ();
        dictionary.put("bed", true);
        dictionary.put("bath", true);
        dictionary.put("beyond", true);
        dictionary.put("bat", true);
        dictionary.put("hand", true);
    }

    public void decompose (String name, int iteration){      

        for (int k = 0; k < name.length(); k++){
            if (dictionary.get(name.substring(0, k)) != null){

                words.get(iteration).push(name.substring(0, k));   

            }

            if (k + 1 < name.length()){
                if (dictionary.get(name.substring(k, name.length())) != null) {               
                    words.get(k + iteration).push(name.substring(k, name.length()));
                }
            }                  
        }       
     
        int end = name.length();

        if (end <= 2){
            return;
        }
        else {
            this.decompose(name.substring(1, name.length() -1), iteration + 1);
        }
    }

    public Stack<Result> restitch(String name){

        Stack<Result> all_results = new Stack<Result>();

        for (int i = 0; i < name.length(); i ++){

            Stack<String> words_loc = words.get(i);

            if (words_loc.isEmpty() == false){
             
                boolean found = false;

                Stack<Result> next_results = new Stack<Result> ();

                while(all_results.isEmpty() == false){
                    Result current = all_results.pop();
                   
                    if (current.end <= i){
                        Iterator<String> words_iterator = words_loc.iterator();              
                       
                        while(true){
                            String next_word = words_iterator.next();  
                            if (words_iterator.hasNext() == true){
                                Result next = new Result();                                               
                                next.end = i + next_word.length();                    
    
                                next.wordlist = (Stack<String>) current.wordlist.clone();                 
                                next.wordlist.push(next_word);
    
                                next_results.push(next);
                                found = true;
                            
                            } else {
                                //save some copying
                                current.end = i + next_word.length(); 
                                current.wordlist.push(next_word);
                                next_results.push(current);
                                found = true;
                                break;
                               
                            }
                              
                        }
                    } else {
                        next_results.push(current);
                    }
                }

                all_results = next_results;

                if (found == false){
                    Iterator<String> words_iterator = words_loc.iterator();   

                    while (words_iterator.hasNext()){
                        String next_word = words_iterator.next();
                        Result next = new Result();
                        next.end = i + next_word.length();
                        next.wordlist.push(next_word);
                        all_results.push(next);
                    }
                }    
            }
        }

        return all_results;

    }

    public void valid_decomposition (String name){
        for (int i = 0; i< name.length(); i ++){
            Stack<String> location = new Stack<String>();
            words.put(i, location);
        }


        this.decompose(name, 0);
        Stack<Result> ans = this.restitch(name);

        while (ans.isEmpty() == false){
            Result current = ans.pop();
            while(current.wordlist.isEmpty() == false){
                System.out.println(current.wordlist.pop());
            }

            System.out.println("");
        }

    }

    public static void main (String args[]){

        BedBath bedbath = new BedBath();

        bedbath.valid_decomposition("bedbathandbeyond");
        



    }



}
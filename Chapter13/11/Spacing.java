/*
    Problem: 
        Given an array of strings, representing a paragraph,
        organize it into lines of max length L, 
        such that it look pretty,
        i.e. the trailing spaces a ^2 + b^2 + c^2 ...
        is minimized 


      Solution:
        Start with a greedy approach, 
            establish maximul line amount and starting point ,

            recurse line by line forward, 
            max time complexity is N + N * 2 ^ L
        

         book solution:
            start with last word, 
            load it to max length, 
            recurse every possible iteration backwards,
            except cache previous result to prevent duplicate calls;   

        
*/
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Arrays;

public class Spacing {
    
    int min;
    int [] min_index;

    public void prettify(String[] paragraph, int length){

        HashMap<Integer, Integer> spaced = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> line_length = new HashMap<Integer, Integer>(); // with spaces
    
        line_length.put(0 ,0);
        int counter = 0;
       
        spaced.put(counter, 0);
    
        for (int i = 0; i < paragraph.length; i ++){
    
            int current_line = line_length.get(counter); 
            int is_start = 1;
    
            if (current_line == 0){
                is_start = 0;
            }
    
            if (current_line + paragraph[i].length() + is_start < length ){
               line_length.put(counter, (line_length.get(counter) + paragraph[i].length() + is_start));              
               
            } else {                  
                counter ++;
                spaced.put(counter, i);
                line_length.put(counter, paragraph[i].length());             
            }    
        }
        
        int [] indexes = new int[counter + 1];
        int [] line_lengths = new int[counter + 1];
        for (int i = 0; i <= counter; i++){
            indexes[i] = spaced.get(i);
            line_lengths[i] = line_length.get(i);
        }
        // double check code test
        int score = 0;
        min =  counter * (int) Math.pow(length,2);
        for (int i = 0; i <= counter; i ++){        
            int line_len = length - line_length.get(i);            
            score += line_len*line_len;
        }       

        System.out.println(score);   

        this.recurse(paragraph, length, counter, score, line_lengths, indexes);
    }

    public void recurse (String[] paragraph, int length, int counter, int score, int[] line_lengths, int[] indexes ){
        if (counter == 0){
            if (score < min){
                min = score;
                min_index = indexes;
                System.out.println(score);
                System.out.println(Arrays.toString(indexes));
            }
            
            return;
        }

        int index = indexes[counter];        
        int iteration = 0;
        int[] next = indexes.clone();
        int[] next_lengths = line_lengths.clone();    
        int next_score = score;
       
        while (index - iteration > 0 || index - iteration > indexes[counter - 1]){
           
            //calculate score
            next[counter] = next[counter] - iteration;

            int transferred = paragraph[index - iteration].length();
            if (iteration == 0){
                transferred = 0;
            }

            next_lengths[counter] += transferred;
            next_lengths[counter - 1] -= transferred;

            if (next_lengths[counter] > length || next_lengths[counter -1] < 0){
                break;
            }          

            int diff1 = (int) Math.pow(length - next_lengths[counter], 2) - (int) Math.pow(length - line_lengths[counter], 2);// old score is higher diff 1 is positive

            int diff2 = (int) Math.pow(length - line_lengths[counter - 1], 2) - (int) Math.pow(length - next_lengths[counter - 1], 2);// old score is lower diff 2 is positive

            next_score -= diff1;
            next_score += diff2;

            iteration ++;

            this.recurse(paragraph, length, counter - 1, next_score, next_lengths.clone(), next.clone());
                
        }


    }

    public static void main(String args[]){

        Spacing spacing = new Spacing();

        String[] paragraph = {
            "I",
            "have",
            "inserted",
            "a",
            "large",
            "number",
            "of",
            "new",
            "examples",
            "from",
            "the",  //10
            "papers",
            "for",
            "the",
            "mathematical",
            "tripos",//15
            "during",
            "the",
            "last",
            "twenty",
            "years,",//20
            "which",
            "should",
            "be",
            "useful", //24
            "to",
            "Cambridge",
            "students." //27
        };

        spacing.prettify(paragraph, 36);
        //System.out.println(min);
    }



}
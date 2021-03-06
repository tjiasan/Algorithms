/*
Problem: Given a text and and array of strings,
    e.g. "amanaplanacanal" and { "can", "apl", "ana"},
    "aplanacan" is a substring that is the concatenation of all the words;
    give the index of the substring, assume all string in words are equal length
    it's possible to contain dupplicates in words array;

    Solution: 



    Complexity O(N) time O (W*n + n*W) space, where n is the length of words;

    worst time backtrack is 2N;
*/

import java.util.HashMap;

class Node {

    public int order;
    public int hash;
    public String query;
    public int count;
    public int [] [] offset;


    public Node () {
        query = "";
        order = 0;
        hash = 0;
        count = 0;
    }
}

class NodeRun {
    public int iteration;
    public int sum;

    NodeRun(){
        sum  = 0;
        iteration = 0;
    }
}


public class Decomposition{

    //assuming a-z only (26 characters, probablity of collision = 0)
    public int hash(String word){
        int result = 0;
        int exponent = word.length() - 1;
        for (int i = 0; i < word.length(); i ++ ){
            int exp = (int) Math.pow(26, exponent);
            result += (int) word.charAt(i) * exp;
            exponent --;
        }
        return result;
    }

    public int roll(int hash, int next, int previous, int length){
        int exp = (int) Math.pow(26, length - 1);
        int result = hash - previous *  exp;
        result*= 26;
        result += next;

        return result;
    }

    public void decompose (String text, String[] words){
      
        HashMap<Integer, Node> counts = new HashMap<Integer, Node> ();

        int sum = 0;
        int length_0 = words[0].length(); //all same length

        for (int i = 0; i < words.length; i ++){
            if (counts.get(this.hash(words[i])) == null){
                Node input = new Node();

                input.order = i + 1;
                input.offset = new int [length_0] [2];
                input.count = 1;
                sum += input.order;

                counts.put(this.hash(words[i]), input);
            } else {
                Node exist = counts.get(this.hash(words[i]));
                sum += exist.order;       
                exist.count ++;      
            }      
        }
  
        //rolling hash
       
        int rolling_hash = this.hash(text.substring(0, length_0));      

        int offset = 0;
        int found = -1;
        HashMap<Integer, NodeRun> running_offset = new HashMap<Integer, NodeRun> ();
      
        for (int i = words[0].length() - 1; i < text.length(); i ++){
 
            if (counts.get(rolling_hash) != null){    
                Node count = counts.get(rolling_hash);   
                NodeRun running =  running_offset.get(offset);  
                int order = count.order;                    
                
                if (running == null){
                    running = new NodeRun();
                    running_offset.put(offset, running);
                } 
                
                int iteration = running.iteration;

                if (count.offset[offset][0] == iteration){
                    count.offset[offset][1] ++;
                } else {
                    count.offset[offset][0] = iteration;
                    count.offset[offset][1] = 1;
                }

                //invalid too many encounters of the same word; //special rare case
                if (count.count < count.offset[offset][1]){
                    running.iteration ++;
                    running.sum = order;
                    count.offset[offset][0] = iteration;
                    count.offset[offset][1] = 1;

                    int counter = 1;
                    String back = text.substring(i - length_0 * counter -length_0, i - length_0 *counter);
                    int back_hash = this.hash(back);
                    //backtrack until encounter the same word
                    while (back_hash != rolling_hash){
                        Node back_count = counts.get(back_hash);
                        if (back_count.offset[0] != iteration){
                            back_count.offset[0] = iteration;
                            back_count.offset[1] = 1;
                        } else {
                            back_count.offset[1] ++;
                        }
                        
                        running.sum += back_count.order;
                        counter ++;
                        back = text.substring(i - length_0 * counter -length_0, i - length_0 *counter);
                        back_hash = this.hash(back);
                    }


                } else {
                    running.sum += order;
                }

                if (running.sum == sum){
                    System.out.println("Found at" + i);
                    break;
                }
            } else {
                NodeRun running =  running_offset.get(offset);  
                if (running != null){
                    running.iteration ++;
                    running.sum = 0;
                }
            }

            offset++; 
            if (offset == length_0){
                offset = 0;
            }

            //calc next rolling hash
            if (i + 2 > text.length()){                    
                break;
            } else {
                rolling_hash = this.roll( rolling_hash, (int) text.charAt(i + 1), (int) text.charAt(i - length_0 + 1), length_0);
                continue;
            }          

        }


        if (found > 1){

            int min = found - words.length * length_0;

            String found_string = text.substring(min + 1, found + 1);

            System.out.println(found_string);

        }



    }

    public static void main (String args[]){
        Decomposition decomposition = new Decomposition();
        
        
        String text = "amanaplanacanal";

        String [] words = { "can", "apl", "ana" };


        decomposition.decompose (text, words);
    }



}
/*
    Problem: you have a file that contains one billion ip addresses

    how do you find an ip adrres that is not in the file?
    RAM is limited;
    disk space isnt;

    Solution : 1)in disk sort: very slow, sorting
    
               2) Disk space hash table;
                    Nested Hashtable;


               loop through all possible ips
               O(N), where N is all possible ips
               O(N),  
               vs book solution: 
               more space by 8x,
               but much faster, book solution would require extra memory to keep track
               of missing ips; whereas mine would compress the solution
               to 192.xxx.xx.xx missing

               book solution: 
                    Assign a 32 bit array integer representation of every entry;
                    loop through every entry:
                        find out with counts, wether a 0 or 1 should be missing;

                    loop at least 32 times;
                    once have matching candidates, 
                    only loop through things that match leading bits;

                    very time expensive;
                    Complexity O (32 N)
                    Space O(N) 1 integer long;
*/  

import java.util.HashMap;

public class MissingIp {
    public HashMap <String, HashMap> allIps; 


    public MissingIp (){
        allIps = new HashMap <String, HashMap>();
    }

    public String[] breakdown (String toBreak){
        String[] result = new String[4];
        
        String tmp = "";
        int counter = 0;
        for (int i = 0; i < toBreak.length(); i++){
            if (toBreak.charAt(i) != '.'){
                tmp += toBreak.charAt(i);
            } else {
                result[counter] = tmp;
                tmp = "";
                counter ++;
            }
        }

        result[counter] = tmp;

        return result;

    };


    public void putIn (String [] input){

        for (int i = 0; i < input.length; i ++){

            String[] arr = this.breakdown(input[i]);
            HashMap <String, HashMap> currentMap = allIps;

            for (int n = 0; n < arr.length - 2; n++){
                if (currentMap.get(arr[n]) == null){                   
                    HashMap <String, HashMap>  inside = new HashMap <String, HashMap>();
                    currentMap.put(arr[n], inside);                   
                }

                currentMap = currentMap.get(arr[n]);
            }

            HashMap <String, Boolean> finalMap = new HashMap <String, Boolean>();
            finalMap.put(arr[arr.length -1], true);

            currentMap.put(arr[arr.length -2], finalMap);

        }

    }

    public Boolean get (String input){
        
        HashMap <String, HashMap> currentMap = allIps;
        String[] arr = this.breakdown(input);

        for (int i = 0; i < arr.length - 2; i ++){
            if (currentMap.get(arr[i]) == null){
                return false;
            } else {
                currentMap = currentMap.get(arr[i]);
            }       

        }

        HashMap <String, Boolean> finalMap = currentMap.get(arr[arr.length -2]);

        if (finalMap.get(arr[arr.length -1]) != null){
            return true;
        } else {
            return false;
        }

    }


    public static void main (String args[]){
        MissingIp missingIp = new MissingIp();

        String [] input = {
            "192.168.0.1",
            "192.186.0.1",
        };
        
        missingIp.putIn(input);

        System.out.println(missingIp.get("192.168.0.1"));

    }
}
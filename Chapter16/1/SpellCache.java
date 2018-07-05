/*
    Problem: 
            Given an online server that uses cache to 
            save previous results, 

            improve the original code.

    Solution:
            The problem with the original code is that if there are
            concurrent processes, if both access the cache at the
            same time, it will return a wrong answer or a null answer
            if cache is full/too small, and the earliest answer is pushed out. 
            
            So, implement locking for cache access

*/
import java.util.concurrent.TimeUnit;
class original {

    public String [] check (String n){

        if (n.equals(last_word) == false){

            last_word = n;

            closest_words = this.process(n);
        }

        return closest_words;
    }

    private String last_word;
    private String[] closest_words;    

    public String []process (String n){
        // see levenstein
        String[] fake_result = {"abc", "def"};
        return fake_result;
    }
}


class Cache {
    private String last_word;
    private String[] closest_words;

    public String[] exists (String n) {

        if (n.equals(last_word)){
            return closest_words;
        }  else {
            return null;
        }

    }

    public  void update(String n, String[] cl)  {
        last_word = n;
        closest_words = cl;
    }

}

public class SpellCache {

    public static void main (String args[]) {

        SpellCache spell = new SpellCache();
        Cache cac = new Cache();
        String n = "bob";
        spell.check(n, cac);
    
    }

    
    public String [] check (String n, Cache cac)  {
        String[] ans;

        synchronized(cac){ // sync access to cache only
            ans = cac.exists(n);

            if (ans == null){
    
                //accessing cache
                ans = this.process(n);
                cac.update(n, ans);
    
            } 
        }



        return ans;
    }


    public String []process (String n){
        // see levenstein
        String[] fake_result = {"abc", "def"};
        return fake_result;
    }
}

/*
normalize('/abc/def/../') #=> 'abc/'
normalize('/abc/def/../../') #=> ''
normalize('abc/def/../../../') #=> '../'
normalize('/abc/def/../../../') #=> Invalid path name
normalize ('/abc/def////////') # => '/abc/def/'

Problem: normalize pathnames to their shortest



*/


class Node {
    protected String data;
    protected Node link;

    /* Constructor */
    public Node ()
    {
        link = null;
        data = "";
    }

    public Node (String d, Node n){
        data = d;
        link = n;
    }

    public void setLink (Node n){
        link = n;
    }

    public void setData (String d){
        data = d;
    }

    public Node getLink() {
        return link;
    }
    
    public String getData(){
        return data;
    }
}

class Stack {

    protected Node start;
    public int size;

    public Stack () {
        size = 0;
        start = null;
    }


    public void push (String data){

        Node push = new Node(data, start);
        start = push;
        size ++;
    }

    public String pop (){
        if (size == 0){
            return null;
        }

        String pop = start.data;
        
        start = start.getLink();
        size --;
        return pop;
    }

    public void reverse() {
        if (size <= 1){
            return;
         }          

        Node previous = start;
        Node cursor = start.getLink();
        Node next = null;

        previous.setLink(null); //prevent infinite loops during print;

        for (int i = 1; i < size; i ++){
            
            next = cursor.getLink();
            cursor.setLink(previous);

            if (next != null){
                //move references;
                previous = cursor;
                cursor = next;
            } else {
                start = cursor;
            }          
        }       


    }

}

public class Path {

    public String normalize (String input) {

        boolean absolute = false;
        int start = 0;
        if (input.charAt(0) == '/'){
            absolute = true;
            start = 1;
        }

        Stack pathnames = new Stack();

        String partial = "";

        for (int i = start; i < input.length(); i++){
            if (input.charAt(i) != '/'){
                partial += input.charAt(i);
            } else {
                if (partial.equals("..")){
                    String popped = pathnames.pop();

                    if (popped == null){
                        if (absolute){
                            return "invalid pathname";
                        } else {
                            pathnames.push("..");
                        }
                    }

                } else if (partial.equals("") || partial.equals(".")){
                    //do nothing;
                } else {
                    pathnames.push(partial);
                }         

                partial = "";
            }           
        }

        pathnames.reverse();

        String result = "";

        while (true){
            String add = pathnames.pop();
           

            if (add == null){
                break;
            }
            result += "/" + add;
        }

        return result;

    } 
   

    public static void main (String args[]){

         Path  path = new  Path();

         String input = "abc/def/.././///";


         String normalized = path.normalize(input);

         System.out.println(normalized);
    }
}
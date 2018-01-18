/*
   Problem:  
*/

class Node {
    protected Character data;
    protected Node link;

    /* Constructor */
    public Node ()
    {
        link = null;
        data = null;
    }

    public Node (Character d, Node n){
        data = d;
        link = n;
    }

    public void setLink (Node n){
        link = n;
    }

    public void setData (Character d){
        data = d;
    }

    public Node getLink() {
        return link;
    }
    
    public Character getData(){
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


    public void push (Character data){

        Node push = new Node(data, start);
        start = push;
        size ++;
    }

    public Character pop (){
        if (size == 0){
            return null;
        }

        Character data = start.data;

        start = start.getLink();
        size --;
        return data;
    }
    

}


public class ExpressionBalanced {

    public boolean eval(String input){
      

        Stack checks = new Stack();

        for (int i = 0; i < input.length(); i++){
            Character curr = input.charAt(i);

            if (curr == '{'){
                checks.push('}');
            } else if (curr == '['){
                checks.push(']');
            } else if (curr == '('){
                checks.push(')');
            } else if (curr == '}'){
                Character other = checks.pop();
                if (other != curr){
                    return false;
                }
            } else if (curr == ']'){
                Character other = checks.pop();
                if (other != curr){
                   return false;
                }
            } else if (curr == ')'){
                Character other = checks.pop();
                if (other != curr){
                    return false;
                }
            }

        }

        return true;
    }


    public static void main (String args[]){

        ExpressionBalanced balance = new ExpressionBalanced();

        String input = "{ [] [] { } ( ) }";


        boolean is_balanced = balance.eval(input);

        System.out.println(is_balanced);
    }
}
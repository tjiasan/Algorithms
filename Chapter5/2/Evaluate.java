/*
    Problem : Evaluate Reverse Polish Notations in Strings

    RPNs are "-123" OR "23"

    Structure A, B, o

    where o is any of X, - , /, +

    thus,  2,3,x,1,+  = 2 * 3 + 1;

    Solution: 1) parse the string into a stack;
                stack => + 1, x, 2, 3;

               2) use recursion fn(Stack input):
                     if (length ==1) return a // base case///
                     parse o;   
                     parse b;

                     return fn(input) o b    ///   return  this + 1;
                     return fn(input) o b   ///    return  this * 2;
                  
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

    public Node pop (){
        if (size == 0){
            return null;
        }

        Node pop = new Node(start.data, null);
        
        start = start.getLink();
        size --;
        return pop;
    }

}

public class Evaluate {

    public Stack parseToStack(String input){

        Stack result = new Stack();
        String partial = "";

        for (int i = 0; i < input.length(); i ++){
            
            if (input.charAt(i) != ','){
                partial += input.charAt(i);
            } else {
                result.push(partial);
                partial = "";
            }
        }
        result.push(partial);

        return result;
    }

    public int eval (Stack input){
        if (input.size == 1){
            return Integer.parseInt(input.pop().data);
        }
        
        Character operator = input.pop().data.charAt(0);
       
        int b = Integer.parseInt(input.pop().data);      
        
        if (operator == '+'){
            return b + this.eval(input);
        } else if (operator == '-') {
            return this.eval(input)/b;
        } else if (operator == '-') {
            return this.eval(input) -b;
        } else {
            return this.eval(input) * b;
        }
     
    }
   

    public static void main (String args[]){

        Evaluate evaluate = new Evaluate();

        String input = "-1,3,+,20,x";


        Stack input_stack = evaluate.parseToStack(input);

        int result = evaluate.eval(input_stack);

        System.out.println(result);




    }
}
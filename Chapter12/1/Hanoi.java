/*
    Problem:

    you have three pegs and rings ing peg 1 are sorted in order from largest to smallest,

    the only operation you can do is to transfer top ring to another peg with a smaller ring
    
    transfer all rings to peg 2 from peg 1

    Solution: (Recursive)
            1) move top n -1 rings to spare peg
            2) move bottom ring to final peg
            3) move back spare peg to final

*/
import java.util.Stack;

public class Hanoi {

    public Stack<Integer>  peg1;
    public Stack<Integer>  peg2;
    public Stack<Integer>  peg3;



    private void begin (int depth) {
        // initialize stack; 

        peg1 = new Stack<Integer> ();
        peg2 = new Stack<Integer> ();
        peg3 = new Stack<Integer> ();
    
        for (int i = depth; i > 0; i --){
            peg1.push(i);
        }

      
        this.recurse(depth, peg1, peg2, peg3);
        this.check();
    }

    private void recurse (int height, Stack origin, Stack destination, Stack spare) {
        if (height == 1){
            destination.push(origin.pop());
        } else {
            this.recurse(height - 1, origin, spare, destination);
            destination.push(origin.pop());
            this.recurse(height - 1, spare, destination, origin);
        }

    }

    private void check (){
        while (peg2.isEmpty() == false){
            System.out.println(peg2.pop());
        }
    }

    public static void main (String args []) {
        
        Hanoi hanoi = new Hanoi();

        hanoi.begin(5);


        
    }
}


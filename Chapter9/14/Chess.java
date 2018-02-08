/*
Problem : a square in a chess game have 16 possiblities, 
          which can be represnted by 4 bits of info,

          There are 64 squares in chess,


          Write a hash function that can effciently Compute the next hash

Solution: because it's 32 bit system/ 64 bit system,
          and all possible states in chess is 256 bit, 
          There are 64 bit, 
          there will be 8 to 4 collisions if all states were present;

          because max array representation is 2 ^ 64;


          THUS:
        use hashmap to compute the 16 states from 0 to 13:
          black/white (2), chess piece (6), empty (1) = 13 possible states

         So, if using 64 bit, we can assign 
         all of A = 0, b = 1, c = 0, d = 21 and so forth;
         and 1 = 0, 2 = 1, 3 = 2 ... 8=7;                
         such that if A2 for example, it's repersented by
                given             A    2       
        State(0 - 13) *  16 ^ (8* 0  + 1)
       such that the chessboard looks like this:
            A  B
         1  0  8
         2  1  9
         3  2  10
         4  3  11
         5  4  12
         6  5  13
         7  6  14
         8  7  15

        To compute, subtract square previous value from hash, add new one,
        Collision = 4;

        Complexity hashing O(64) where 64 is all pieces in the board;
        next_hash is O(1);

        Collision = 4;
        Space O(8);
*/

import java.util.HashMap;

public class Chess {

    public HashMap<Character, Long> letter;

    public Chess(){

        letter = new HashMap <Character, Long> ();
        letter.put ('A',(long) 0);
        letter.put ('B',(long) 1);
        letter.put ('C',(long) 0);
        letter.put ('D',(long) 1);
        letter.put ('E',(long) 0);
        letter.put ('F',(long) 1);
        letter.put ('G',(long) 0);
        letter.put ('H',(long) 1);
    }


    public long calculateSquare(String [] square){
        Long a = Long.parseLong(square[1]);    

        long b = letter.get(square[0].charAt(0));

        String second = square[0].charAt(1) + "";
        long c = Long.parseLong(second) - 1;

        long exponent = (long) Math.pow(16, (8 * b + c ));

        return a * exponent;
    }

    public long  hash (String[] [] board){

        long result = 0;

        for (int i = 0; i < board.length; i ++){ 
            result += this.calculateSquare(board[i]);
        }

        return result;
    }

    public long roll (long hash, String [] old_value, String[] new_value){
        hash -= this.calculateSquare(old_value);
        hash += this.calculateSquare(new_value);

        return hash;
    }


    public static void main (String args[]){
        Chess chess = new Chess();

        String [][] board = {
            {"A3", "5"},
            {"H5", "1"},
            {"H6", "9"},
            {"F3", "15"}
        };

        long hash = chess.hash(board);

        System.out.println(hash);

        //change A1 from 0 to 2;
        String[] old_value = {"A1", "0"};
        String[] new_value = {"A1", "2"};

        long new_hash = chess.roll(hash, old_value, new_value);

        System.out.println(new_hash);
    }
}
/* Problem: Solve sudoku puzzle

    Divide each sector from 1  to 9:
    1 2 3
    4 5 6
    7 8 9


    For each number : 
    3 datapoints : linkedlist of sections to search
                   row restricted;
                   column restricted;

    each sector, list of empty
    
    
    for each section, 
        if more than one empty possible, 
        skip, 
        else 
        if only one, that's the answer

 */       
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

 public class Sudoku {

    public int[][] solve (int[][] input){
        
        HashMap<Integer, HashMap<Integer, Boolean>> row = new HashMap<Integer, HashMap<Integer, Boolean>> ();
        HashMap<Integer, HashMap<Integer, Boolean>> column = new HashMap<Integer, HashMap<Integer, Boolean>> ();
        HashMap<Integer, Integer[] > fill = new HashMap<Integer, Integer[]>();

        for (int i = 1; i < 10; i ++){
            row.put(i, new HashMap<Integer, Boolean>());
            column.put(i, new HashMap<Integer, Boolean>());
            fill.put(i, new Integer[9]);
        }


        for (int i = 0; i < 9; i ++){
            for (int k = 0; k < 9; k++){
                if (input [i][k] != 0){
                    row.get(input[i][k]).put(i, true);
                    column.get(input[i][k]).put(k, true);    
                    
                    int section = ( i / 3) * 3 +  (k / 3);
                    fill.get(input[i][k])[section] = 1;

                }
            }
        }



        return this.recursive(input, row, column, fill);
    }

    public int[][] recursive (
        int[][] input, 
        HashMap<Integer, HashMap<Integer, Boolean>> row,
        HashMap<Integer, HashMap<Integer, Boolean>> column,
        HashMap<Integer, Integer[] > fill
     ){

        int[][] section = new int[9][2];

        section[0][0] =  0;
        section[1][0] =  0;
        section[2][0] =  0;
        section[3][0] =  3;
        section[4][0] =  3;
        section[5][0] =  3;
        section[6][0] =  6;
        section[7][0] =  6;
        section[8][0] =  6;

        section[0][1] = 0;
        section[1][1] = 3;
        section[2][1] = 6;
        section[3][1] = 0;
        section[4][1] = 3;
        section[5][1] = 6;
        section[6][1] = 0;
        section[7][1] = 3;
        section[8][1] = 6;

       
        boolean done = false;

        for (int i = 1; i < 10; i ++){

            Stack<Integer> next = new Stack<Integer>();

            Integer[] iterate = fill.get(i);

            for (int a = 0; a < 9; a ++){
                if (iterate[a] == null){
                    int [] start = section[a];

                    Stack<Integer[]> possibilities = new Stack<Integer[]> ();
                    int max_row = start[0]+ 3;
                    int max_col = start[1] + 3;

                    for (int k = start[0]; k < max_row; k++){
                        for (int n = start[1]; n < max_col; n ++){
                            
                            if (input[k][n] == 0){
                                if (row.get(i).get(k) == null && column.get(i).get(n) == null){
                                    Integer [] location = new Integer[2];

                                    location[0] = k;
                                    location[1] = n;                             

                                    possibilities.push(location);
                                }
                            }
                        }
                    }

                    if (possibilities.size() == 1){
               
                        //found 
                        Integer[] location = possibilities.pop();
                        row.get(i).put(location[0], true);
                        column.get(i).put(location[1], true);
                        fill.get(i)[a] = 1;

                        input[location[0]][location[1]] = i;
                        return this.recursive(input, row, column, fill);

                    }


                }

            }        
            

        }



        return input;

     }
 


    public static void main (String args[]){
        Sudoku sudoku = new Sudoku();

        int[][] input = new int[9][9];
        /* easy
        input[0][0] = 8;
        input[0][1] = 4;
        input[0][2] = 6;
        input[0][4] = 3;
        input[0][6] = 5;

        input[1][0] = 2;
        input[1][1] = 7;
        input[1][4] = 9;
        input[1][6] = 4;
        input[1][7] = 8;
        input[1][8] = 1;

        input[2][5] = 8;

        input[3][1] = 6;
        input[3][2] = 8;
        input[3][4] = 7;
        input[3][7] = 1;

        input[4][0] = 5;
        input[4][3] = 6;
        input[4][6] = 7;
      
        input[5][1] = 1;
        input[5][2] = 9;
        input[5][4] = 2;
        input[5][7] = 6;

        input[6][0] = 9;
        input[6][2] = 1;
        input[6][3] = 7;
        input[6][4] = 6;
        input[6][5] = 3;
        input[6][7] = 4;

        input[7][0] = 3;
        input[7][1] = 2;
        input[7][2] = 7;
        input[7][3] = 4;
        input[7][7] = 5;

        input[8][8] = 7;

       */


        input[0][1] = 4;
        input[0][3] = 3;
        input[0][5] = 2;
        input[0][6] = 5;

        input[1][3] = 7;
        input[1][4] = 4;

        input[2][0] = 6;
        input[2][6] = 4;
        input[2][8] = 3;


        input[3][0] = 1;
        input[3][8] = 6;

        input[4][2] = 2;
        input[4][5] = 7;

        input[5][0] = 9;
        input[5][5] = 5;
        input[5][6] = 7;

        input[6][3] = 2;

        input[7][1] = 3;
        input[7][7] = 9;
        input[7][8] = 1;
        
        input[8][0] = 4;
        input[8][3] = 5;
        input[8][4] = 1;
        input[8][6] = 8;
        input[8][8] = 2;

        //sudoku.check_answer (input);

        int[][] answer = sudoku.solve(input);

        sudoku.check_answer (answer);


    }

    public void check_answer(int[][] arr){
        for (int i = 0; i < 9; i ++){
            System.out.println(Arrays.toString(arr[i]));
        }

    }
 }
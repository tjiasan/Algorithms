/* Problem: Solve sudoku puzzle

Book Solution: 

        1) add 1 entry at at time, 
            check puzzle see if it satisfy constrains for row, column and 
            subgrid
        
        2) if satisfies, recurse, if not reset to zero go back

Book solution is stupid because it's pretty much brute foce


 */       
import java.util.Arrays;

 public class BookSudoku {


    public void solve (int[] [] input, int row, int column){
    
        if (input[row][column] == 0){
           // System.out.println("");
            //this.check_answer(input);
            for (int i = 1; i < 10; i++){
                if (this.is_valid (input, row, column, i)){
                   
                    input [row][column] = i;
                    if (row == 8 && column == 8){
                       this.check_answer(input);
                    } else if (column == 8){
                       this.solve(input, row + 1, 0);
                       input[row][column] = 0;
                    } else {
                       this.solve(input, row, column + 1);
                       input[row][column] = 0;
                    }
               
                }
            }
            
        } else {
            if (row == 8 && column == 8){
                this.check_answer(input);
             }
             else if (column == 8){
                this.solve(input, row + 1, 0);
             } else {
                this.solve(input, row, column + 1);
             }
        }
        

    }

    public boolean is_valid (int [][] input, int row, int column, int check){


        for (int i = 0; i < 9; i++){
            if (input[row][i] == check){
                return false;
            }
        }

        for (int i = 0; i < 9; i++){
            if (input[i][column] == check){
                return false;
            }
        }

        int square_row = (row/3) * 3 ; 
        int square_col = (column/3) * 3;

    

        for (int i = square_row ; i < square_row + 3; i ++){
            for (int k = square_col; k < square_col + 3; k++){
                if (input[i][k] == check){
                    return false;
                }
            }
        }

        return true;
    }


    public static void main (String args[]){
        BookSudoku sudoku = new BookSudoku();

        int[][] input = new int[9][9];
         /*
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

       // sudoku.check_answer (input);

        sudoku.solve(input, 0, 0);

       // sudoku.check_answer (answer);


    }

    public void check_answer(int[][] arr){
        for (int i = 0; i < 9; i ++){
            System.out.println(Arrays.toString(arr[i]));
        }

    }
 }
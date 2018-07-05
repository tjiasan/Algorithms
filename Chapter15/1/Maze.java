/*
    Use breadth first search;
    
    increment 1 step at a time
    if encounter previously visited/ wall, end 

    To get shortest path, 
    start from the end, look for 1 lower
    until you get to 2, then look for start
    because I fucked up the wall coding

    Time complexity O(N),Space O(N);

    To get any path,
    just use DFS,
    complexity is similar depending on the problem,
    
*/

import java.util.Stack;
import java.util.Arrays;
public class Maze {

    Stack<int[]> shortest = new Stack<int[]>();

    public void find (int[][] arr){
        int [] start = {arr.length - 1, 0};

        boolean end = false;

        Stack<int[]> process = new Stack<int[]>();
        process.push(start);
        int length = 1;

        while (end == false && process.isEmpty() == false){

            Stack<int[]> next_stack = new Stack<int[]>();

            while(process.isEmpty() == false){
                int[] coord = process.pop();

                if (coord[0] == 0 && coord[1] == arr.length){
                    arr[coord[0]][coord[1]] = length + 1;
                    end = true;
                    break;
                }

                //up
                if (coord[0] - 1 > -1){
                    int next_square = arr[coord[0] - 1][coord[1]];
                    if (next_square == 0){
                        int[] next = { coord[0] - 1, coord[1] };
                        arr[next[0]][next[1]] = length + 1; 
                        next_stack.push(next);         
                    }
                }

                //down
                if (coord[0] + 1 < arr.length){
                    int next_square = arr[coord[0] + 1][coord[1]];
                    if ( next_square == 0){
                        int[] next = { coord[0] + 1, coord[1] };         
                        arr[next[0]][next[1]] = length + 1;    
                        next_stack.push(next);
                    }
                }

                //right
                if (coord[1] - 1 > - 1){
                    int next_square = arr[coord[0]][coord[1] - 1];
                    if (next_square == 0){
                        int[] next = { coord[0], coord[1] - 1 };            
                        arr[next[0]][next[1]] = length + 1;
                        next_stack.push(next);             
                
                    }
                }

                //left
                if (coord[1] + 1 < arr[0].length){               
                    int next_square = arr[coord[0]][coord[1] + 1];
                    if (next_square == 0){
                        int[] next = { coord[0], coord[1] + 1 };                 
                        arr[next[0]][next[1]] = length + 1;    
                        next_stack.push(next);            
                    }
                }
            }
            if (end == true){
                break;
            }
            process = next_stack;
            length ++;
        }
    }


    public static void main (String args[]){


        Maze maze = new Maze();


        int[][] input = {
            { 1, 0, 0, 0, 0, 0, 1, 1, 0, 0 },
            { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
            { 1, 0, 1, 0, 0, 1, 1, 0, 1, 1 },
            { 0, 0, 0, 1, 1, 1, 0, 0, 1, 0 },
            { 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 1, 1, 0, 0, 1, 0, 1, 1, 0 },
            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
            { 1, 0, 1, 0, 1, 0, 1, 0, 0, 0 },
            { 1, 0, 1, 1, 0, 0, 0, 1, 1, 1 },         
            { 1, 0, 0, 0, 0, 0, 0, 1, 1, 0 }
        };

        maze.find(input);
        
        for (int i = 0; i < input.length; i++){
            System.out.println(Arrays.toString(input[i]));
        }
    }
}
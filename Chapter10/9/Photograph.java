/*
    Problem: Given two teams with heights of players,
            see if it's possible to take a photograph, such that
            the team in front needs to have a player that is shorter than the team behind;
    
    Solution: Sort the players,
                Start from the tallest end;
                
                find the tallest player,
                team with tallest player is behind

                compare in tandem;
                if behind not taller, return false;


                Comlexity nlogn to sort

*/
import java.util.Arrays;

public class Photograph{

    public boolean possible(int[] team1, int[] team2){
        boolean possible = true;

        Arrays.sort(team1);
        Arrays.sort(team2);

        int i = team1.length -1;

        while (team2[i] == team1[i]){
            i--;
            break;
        }

        boolean team1_big = true;
        if (team2[i] > team1[i]){
            team1_big = false;
        }


        for (int n = i; n >=0; n--){
            if(team1_big){
                if (team1[n] <= team2[n]){
                    return false;
                }
            } else {
                if(team2[n] <= team1[n]){
                    return false;
                }
            }



        }
        





        return possible;

    }


    public static void main (String[] args){

        Photograph photograph = new Photograph();

        int[] team1 = {3, 4, 2, 5, 10, 22, 11, 2, 5, 4};
        int[] team2 = {2, 3, 1, 2, 3,  22, 3,  3, 2, 1};

        boolean poss = photograph.possible(team1, team2);

        System.out.println(poss);
    }
}
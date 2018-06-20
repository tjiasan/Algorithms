/* Problem:

    Cities are arranged ina circular road,
    need to visit all cities and come back to starting city.
    A certain amount of gas is available at each city,
    the amount of gas summed up over all cities is equal to the gas requuired to go around once 
    gas tank is unlimited capcity.

    An ample city is where you can go around the city with an empty tank, refill at it and travel through remaining city,

    Solution:
        if starting from a city where can't reach the next one (negative),
        then the city isn't the answer


        if starting with a city that is positive, but reaches negative midway,
            every city between start and midway cannot be the answer

        since the problem statement says that there must be an answer (find one), 
        we can skip the looparaound (because if makes it to the end, guarantee it's the answer),

        if assuming no guarantee of a city, perform an extra n iteration to confirm;
       
        Complexity O(N), space O(1);

        book answer: Z city will always be the minimum, 
            find minium tank amount, 
            and start from that city;

*/
import java.util.Arrays;

public class Gasup {

    public void find_ample(int[][] gas, int mpg){
            /* check code
        int[] surplus = new int[gas.length];
        int first = 0;
        for (int i = 0; i < gas.length; i++){
            surplus[i] = gas[i][0] - gas[i][1]/mpg ;// extra space unnecessary
        }
        */
        
        int tank = 0;
        int ans = 0;
        for (int i = 0; i < gas.length; i++ ){
            tank += gas[i][0] - gas[i][1]/mpg; //add surplus

            if (tank < 0){
                tank = 0;
                ans = i + 1;
            }
        }
        System.out.println(ans);
        //if want to find multiple cities, need more code, also this assumes there is an answer
        //if multiple answers are available, complexity is O(N * ans);

    }

    public static void main(String args[]){

        //gass in gallon, distance second in miles
        int[][] gas = {
            { 50, 900 },
            { 20, 600 },
            { 5, 200 },
            { 30, 400 }, //answer
            { 25, 600 },
            { 10, 200 },
            { 10, 100 }
        };

        int mpg = 20;

        Gasup gasup = new Gasup();

        gasup.find_ample(gas, mpg);
    }

}
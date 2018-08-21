/* Problem: given a 2D sorted array (both in columns and rows),
        find if an integer is in the array

   Solution: Crawl Throug the 2D array
                 (Start from top right, go left until next int is lower, then go down one level)

                 Complexity O(M + N);

*/

public class Search2D {



    public boolean crawl (int[] [] arr, int find){
        boolean result = false;

        int[] coord = {0, arr[0].length -1};

        while (coord[0] < arr.length){
            int current = arr [coord[0]][coord[1]];
                

            while (current > find && coord[1] > 0) {
                coord[1] --;
                current = arr [coord[0]][coord[1]];
            }

            if (current == find){
                    return true;
            } else {
                    coord[0]++;
            }

        


        }


        return result;
    }


    public static void main (String args[]){
                    

            int [] [] input = {

                { -1, 2,  4,  4,   6 },
                { 1,  5,  5,  9,  21 },
                { 3,  6,  6,  9,  22 },
                { 3,  6,  8,  10, 24 },
                { 6,  8,  9,  12, 25 },
                { 8,  10, 12, 13, 40 },
            };

            Search2D search = new Search2D();

            boolean result = search.crawl(input, 7);

            System.out.println(result);

    }
}
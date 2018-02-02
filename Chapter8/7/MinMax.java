/* Problem : Find min and max in an unsorted array
                in less than 2 (n -1) comparisons
    Solution: 
                Worst case  2(n - 1),
                average case :
                    if there's more than  consecutive  > > ,

                    define k as consecutive signs

                    Complexitg 2(n-1) -k;


                */


public class MinMax {


    public void find(int [] arr){

        int min = arr [0];
        int max = arr [0];

        boolean inc = true;  


        for (int i = 0; i < arr.length; i++){
            if (inc) {
                while (i + 1 < arr.length){
                    if (arr[i] < arr[i + 1]){
                        i ++;
                    } else {
                        break;
                    }
                }
                inc = false;
                if (arr [i] > max){
                    max = arr[i];
                }

            } else {
       
                while (i + 1 < arr.length){
                    if (arr[i] > arr[i + 1]){
                        i ++;
                    } else {
                        break;
                    }
                }

                inc = true;
                if (arr [i] < min){
                    min = arr[i];
                }
            }
        }


        System.out.println(max);
        System.out.println(min);
    }



    public static void main (String args[]){
        MinMax minmax = new MinMax();

        int [] input = { 7, 2, 5, 1, 0, 5};

        minmax.find(input);

    }
}
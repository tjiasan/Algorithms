/* Problem : Find min and max in an unsorted array
                in less than 2 (n -1) comparisons
    Solution: Use quadratic formula in intercept form 

            y = a (x - p) * (x- q);

            if x = 0 or y - intercept;
            a = y / pq ;

            if assuming a = pq, then a = 1;

            therefore, if the result of
            (x - min)* (x -max) > 0, it's outside the range

            Comparisons: O (N) + k, where k = new max/min values;
            Space = O (1);
    */


                public class MinMax3 {

                    public boolean outside (int p, int q, int query){
                        int answer = (query - p) * (query - q);

                        if (answer > 0){
                            return true;
                        } else {
                            return false;
                        }
                    }


                    public void find(int [] arr){
                
                        int min;
                        int max;
                        
                        if (arr[0] > arr[1]){
                            max = arr[0];
                            min = arr[1];
                        } else {
                            max = arr[1];
                            min = arr[0];
                        }
  

                        for (int i = 2; i < arr.length; i ++){

                            boolean outside = this.outside (min, max, arr[i]);

                            if (outside){
                                if (arr[i] < min){
                                    min = arr[i];
                                } else {
                                    max = arr[i];
                                }
                            }

                        }
                
                        System.out.println(max);
                        System.out.println(min);
                    }
                
                
                
                    public static void main (String args[]){
                        MinMax3 minmax = new MinMax3();
                
                        int [] input = { -7, -2, 5, 1, 0, 5, -9, 20, -30};
                
                        minmax.find(input);
                
                    }
                }
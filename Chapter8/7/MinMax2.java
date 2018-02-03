/* Problem : Find min and max in an unsorted array
                in less than 2 (n -1) comparisons
    Solution: Do a moving window of 2 size; initialize with 1 cycle;
                e.g. {1,2,3,4}

                window 1 : [1, 2]
                since 1 < 2,  compare 1 to min, compare 2 to max

                window 2 [3, 4],
                since 4 > 3, compare 4 to max, compare 3 to min


                Complexity:   1.5(n -2)
                */


                public class MinMax2 {


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

                        for (int i = 2; i < arr.length; i += 2){

                                if (i + 1 < arr.length){
                                    if (arr[i] > arr[i + 1]){
                                        if (arr[i] > max){
                                            max = arr[i];
                                        }
                                        if (arr[i + 1] < min){
                                            min = arr[i];
                                        }
                                    } else {
                                        if (arr[i + 1] > max){
                                            max = arr[i];
                                        }
                                        if (arr[i] < min){
                                            min = arr[i];
                                        }
                                    }

                                } else {
                                    if (arr[i] < min){
                                        min = arr[i];
                                    }
                                    if (arr[i]> max){
                                        max = arr[i];
                                    }
                                }


                        }
                
                
                     
                
                
                        System.out.println(max);
                        System.out.println(min);
                    }
                
                
                
                    public static void main (String args[]){
                        MinMax2 minmax = new MinMax2();
                
                        int [] input = { 7, 2, 5, 1, 0, 5};
                
                        minmax.find(input);
                
                    }
                }
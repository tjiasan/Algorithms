/*
    Problem :
        a + b * sqrt(q) makes calculations easier


        enumerate k numbers starting from a = 0 and b = 0;


        Solution: fill binary search tree with elements from 
                    0 to k ;

                    find and remove min;

                    add min to result;

                    add min + sqrt(q);

                    insert into binary search tree,

                    remove max;

                    repeat

               space O(K), time klogk

         Alt book solution without binary tree:
             The next value is either previous value + 1
             or previous value + sqrt;
             
             start from zero;

             if tracked value 1 + 1 greatuer than
             tracked_value 2 + sqrt, 

                use tracked_value + 1 as next;
                    advance tracked value until,
                    just greater than current value;
                
             vice versa;
             
             
             if they are equal (){
                 advance both until both are greater than value;

             }

             Complexity (3N);


*/
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Square2 {

    public double[] get (int k, int q){
        double[] result = new double[k];
        
        BinarySearchTree res = new BinarySearchTree();
        double sqrt = Math.sqrt((double) q);
        for (int i = 0; i < k; i ++){
            res.insert((double) i);
        }

        for (int i = 0; i < k;i ++){
            double min = res.removeMin();
            System.out.println(min);
            result[i] = min;

            double next = min + sqrt;

            res.insert(next);

            res.removeMax();

        }


        return result;
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
     
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void usePrev (int k){
        double result [] = new double[k];

        result[0] = 0;
        double sqrt = Math.sqrt(2);
        double one = 1.0;
        int track1 = 0;
        int track2 = 0;
        
        for (int i = 1; i < k; i++){
            
            if (this.round(result[track1] + one, 5) == this.round(result[track2] + sqrt, 5)){
                result[i] = result[track1] + one;
                while(track1 <= i){
                    double next = result[track1] + one;
        
                    if (next > result[i]){                     
                        break;
                    }
                    track1++;
                }

                while(track2 <= i){
                    double next = result[track2] + sqrt;
            
                    if (next > result[i]){                       
                        break;
                    }
                    
                    track2 ++;
                }
            } else if (result[track1] + one < result[track2] + sqrt){
       
                result[i] = result[track1] + one;

                while(track1 <= i){
                    double next = result[track1] + one;
        
                    if (next > result[i]){                     
                        break;
                    }
                    track1++;
                }

            } else {
                result[i] = result[track2] + sqrt;
                
                while(track2 <= i){
                    double next = result[track2] + sqrt;
            
                    if (next > result[i]){                       
                        break;
                    }
                    
                    track2 ++;
                }
            }          
            System.out.println(result[i]);        
        }

    }


    public static void main (String args[]){

        Square2 square = new Square2();

       // double [] result = square.get (12, 2);

        square.usePrev(15);
    }
}


class Node {

    public double data;

    public Node left;

    public Node right;


    public Node(double d){
        left = null;
        right = null;
        data = d;
    }
}

class BinarySearchTree {

    public Node head;

    public BinarySearchTree (){
        head = null;
    }

    public void insert (double d){
        Node ins = new Node(d);

        if (head == null){
            head = ins;
            return;
        }

        Node ptr = head;
        while(true){
            if (d > ptr.data){
                if(ptr.right != null){
                    ptr = ptr.right;
                } else {
                    ptr.right = ins;
                    break;
                }
            } else {
                if (ptr.left != null){
                    ptr = ptr.left;
                } else {
                    ptr.left = ins;
                    break;
                }
            }
        }
        
    }


    public double removeMin(){       
        
        Node delete = head;
        Node parent = null;
        
        while (delete.left != null){      
            parent = delete;
            delete = delete.left;       
        }  

        if (parent == null){
            head = head.right;
        } else {
            parent.left = null;  
            if (delete.right != null){
                parent.left = delete.right;
            }
        }       

        return delete.data;
    }

    public void removeMax(){
        Node delete = head;
        Node parent = null;

        if (head == null){
            return;
        }

        while (delete.right != null){      
            parent = delete;
            delete = delete.right;       
        }  

        if (parent == null){
            head = head.left;
        } else {
            parent.right = null;
            if (delete.left != null){
                parent.right = delete.left;
            }
        }        

       

    }

    public Node find (double d){

        Node ptr = head;
        while (ptr != null){
            if (d == ptr.data){
                return ptr;
            } else if (d < ptr.data){
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
        }

        return null;
    }

    

    //preorder traverse;
    public void display (Node head) {
        if (head == null){
            return;
        }
        this.display(head.left);
        System.out.print(head.data);
        this.display(head.right);


    }

}
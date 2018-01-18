/*
    Problem: Given a binary tree, 
            decompose the tree into a list of integers for each level from left to right
            e.g. [  [1], [2, 3], [3, 4 ,4] ]

    Solution: 
        Set Queue1 with parent node;

        iterate through Queue1:
        Use  Queue2 to add children nodes from left to right, while flushing parent into
                a newly created array (with known size);

        set the Queue1 = Queue2;
            
        use table doubling to dynamicalyl resize the array;
        Time Complexity O(N), Space O(N) for the result array, OR O(M) if only considring level depth;
*/



import java.util.ArrayList;
import java.util.Arrays;

class BinaryNode {
    protected Integer data;
    protected BinaryNode left;
    protected BinaryNode right;


    public BinaryNode(){
        left = null;
        right = null;
        data = 0;
    }

    public BinaryNode (int d, BinaryNode l, BinaryNode r){
        data = d;
        left = l;
        right = r;
    }

    public void setData(int d){
        data = d;
    }

    public int getData(){
        return data;
    }

    public void setRight(BinaryNode r){
        right = r;
    }
    public void setLeft(BinaryNode l){
        left = l;
    }

    public BinaryNode getRight(){
        return right;
    }

    public BinaryNode getLeft(){
        return left;
    }

}

class BinaryTree {

    public BinaryNode head;


    public BinaryTree (){
        head = null;
    }

    public void insert (int n){
        if (head == null){
            head = new BinaryNode(n, null ,null);
            return;
        }

        BinaryNode ins = new BinaryNode(n, null, null);
        BinaryNode ptr = head;

        while (true){
            if (ins.getData() > ptr.getData()){
                if (ptr.getRight() == null){
                    ptr.setRight(ins);
                    return;
                } else {
                    ptr = ptr.getRight();
                }
            } else {
                if (ptr.getLeft() == null){
                    ptr.setLeft(ins);
                    return;
                } else {
                    ptr = ptr.getLeft();
                }

            }
        }


    }


}


class Node {
    protected BinaryNode data;
    protected Node link;

    public Node (){
        data = null;        
    }

    public Node (BinaryNode d, Node l){
        data = d;
        link = l;
    }

    public Node getLink(){
        return link;
    }

    public void setLink(Node n){
        link = n;
    }

    public BinaryNode getData(){
        return data;
    }

}


class Queue {

    public Node head;
    public Node end;
    public int size;

    public Queue () {
        size = 0;
        head = null;
        end = null;
    }

    public void insert (BinaryNode data){

        Node ins = new Node(data, null);

        if (head == null){
            size ++;
            head = ins;
            end = head;
            return;
        }

               
        end.setLink(ins);
        
        size ++;
    }

    public BinaryNode remove (){
        if (head == null){
            return null;
        }

        BinaryNode pop = head.data;
        
        head = head.getLink();
        size --;
        return pop;
    }

    public BinaryNode peek (){
        if (head == null){
            return null;
        }
        BinaryNode peek = head.data;
        return peek;

    }

}


public class BinaryReduce {

    public int [] [] reduce (BinaryNode head){

        Queue level = new Queue();
        level.insert(head);

        int current_level = 0;

        //initial size of result
        int level_max = 2;
        int [] [] result = new int[level_max][0];

        
        while (level.size > 0){
            Queue next_level = new Queue();
            int [] result_level = new int[level.size];            
            
            int iterations = level.size;
            for (int n = 0; n < iterations; n++){            
                BinaryNode current = level.remove();
                
                result_level[n] = current.data;
                if (current.getLeft() != null){
                    next_level.insert(current.getLeft());
                } 
                if (current.getRight() != null){
                    next_level.insert(current.getRight());   
                }                
                            
            }

            level = next_level;
            
            result[current_level] = result_level;
            current_level ++;   
            
            //table doubling if hit max;

            if (current_level == level_max){
                level_max *= 2;
                int [] [] tmp = new int [level_max] [0];

                for (int k = 0; k < current_level; k++){
                    tmp[k] = result[k];
                }

                result = tmp;

            }
            
        }

        System.out.println(Arrays.toString(result[1]));  


      
        return result;

    }

    public static void main (String args[]){

        BinaryReduce bin = new BinaryReduce();

        BinaryTree input = new BinaryTree();

        input.insert(5);
        input.insert(3);
        input.insert(4);
        input.insert(6);
        input.insert(7);
        input.insert(8);


        int[][] result = bin.reduce(input.head);
    }
}
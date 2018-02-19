/* Problem: 
    Build a minimum BST from 
    a sorted array

    Solution : (start + end)/2 = mid = middle = head:
       
                head left =   recurse left, 
                head right =  recurse right;

            return head

            for  {1,2,3,4,5,6,7,8,9,10};

            result 

                    5
                   / \
                  2   8
                 /\   /\
               1  3  6  9
                  \  \   \
                  4   7   10
                  Complexity O(N)
                 
  */   



 public class MinBst{

    public Node construct (int[] input, int start, int end){
        int mid = (start + end)/2;    
  
        Node head = new Node(input[mid]);

        if (mid - start > 0){
            head.left = this.construct(input, start, mid -1);
        }

        if (end - mid > 0){
            head.right = this.construct(input, mid + 1, end);
        }

        return head;

    }

    public static void main (String args[]){

        MinBst minBst = new MinBst();

        int[] input = {1,2,3,4,5,6,7,8,9,10};


        Node BST = minBst.construct(input, 0, input.length - 1);

        System.out.println(BST.data);
        //left subtree
        System.out.println(BST.left.data);

        System.out.println(BST.left.left.data);
        System.out.println(BST.left.right.data);

        System.out.println(BST.left.right.right.data);

        //right subtree
        System.out.println(BST.right.data);

        System.out.println(BST.right.left.data);
        System.out.println(BST.right.right.data);

        System.out.println(BST.right.left.right.data);

        System.out.println(BST.right.right.right.data);

        
        
    }
 }


 class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int d){
        data = d;
        left = null;
        right = null;
    }
 }
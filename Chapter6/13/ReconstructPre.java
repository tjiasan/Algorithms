


class Node {

    public Character data;
    protected Node left;
    protected Node right;

    public Node (){
        left = null;
        right = null;
        data = 0;
    }

    public Node (Character d, Node l, Node r){
        data = d;
        left = l;
        right = r;
     
    }

    public void setData(Character d){
        data = d;
    }

    public int getData(){
        return data;
    }

    public void setRight(Node r){
        right = r;
    }
    public void setLeft(Node l){
        left = l;
    }


    public Node getRight(){
        return right;
    }

    public Node getLeft(){
        return left;
    }

}

class HeadNode {
    public Node head;
    public int lowest; 

    public HeadNode(){
        head = null;
        lowest = -1;
    }
}



public class ReconstructPre {

    public HeadNode setLowest(HeadNode head, Node attach){
        Node start = head.head;

        int lowest = head.lowest;

       // System.out.println( (char) attach.getData());
 
        while (lowest >= 0){
            if (start.getLeft() != null){
                start = start.getLeft();
                lowest --;
            } else {
                start = start.getRight(); // a z pattern since right nulls already accounted;
            }
               
        }

        start.setRight(attach);

        head.lowest --;

        return head;

    }

    public int null_counter (Node [] preorder, int pointer){
        int i = pointer;
        int total = 0;
        while (preorder[i] == null){
            total ++;
            i--;
        }

        return total;        

    }

    public HeadNode reconstruct (Node [] preorder, int pointer){

        Node ptr = null;
        
        int null_count =  this.null_counter (preorder, pointer);
  
        pointer -= null_count;
        pointer --;

        ptr = preorder[pointer];
        int lowest_offset = 1;
        lowest_offset += null_count - 2;

        int level = 0;
        
        null_count = 0;

        while (null_count < 2 && pointer > -1){
            ptr = preorder[pointer];
            if (ptr != null){
                //System.out.println((char) ptr.getData());
            }
                
            if (ptr != null && preorder[pointer + 1] != null){
                ptr.setLeft(preorder[pointer + 1]);
                level ++;
            } else if (ptr != null && preorder[pointer + 1] == null){
                ptr.setRight(preorder[pointer + 2]);
                null_count --;
                level ++;
            }
        
            if (ptr == null){
                null_count ++;
            }

            pointer --;
           
        }

   
        if (null_count == 2){
           ptr = preorder[pointer + 3]; 

           HeadNode head = this.reconstruct (preorder, pointer + 2);
           
           HeadNode result = this.setLowest(head, ptr);
           return result;

        }  else {
            HeadNode result = new HeadNode();
            result.lowest = level - lowest_offset;
            result.head = ptr;
            return result;
        }

        

    }

    void recursive (Node head) {

        if (head == null){
            return;
        }
        System.out.println((char) head.getData());   //visiting
        
        this.recursive(head.getLeft());  
        this.recursive(head.getRight());

    }

    public static void main (String args[]){


        Node [] preorder = new Node[19];

        preorder[0] = new Node('H', null, null);
        preorder[1] = new Node('B', null, null);
        preorder[2] = new Node('F', null, null);
        preorder[3] = null;
        preorder[4] = null;
        preorder[5] = new Node('E', null, null);
        preorder[6] = new Node('A', null, null);
        preorder[7] = null;
        preorder[8] = null;
        preorder[9] = null;
        preorder[10] = new Node('C', null, null);
        preorder[11] = null;
        preorder[12] = new Node('D', null, null);
        preorder[13] = null;
        preorder[14] = new Node('G', null, null);
        preorder[15] = new Node('I', null, null);
        preorder[16] = null;
        preorder[17] = null;
        preorder[18] = null;
          
        int pointer = preorder.length - 1;

        ReconstructPre pre = new ReconstructPre();

        HeadNode head = pre.reconstruct(preorder, pointer);

        Node act_head = head.head;

        System.out.println((char) act_head.getLeft().getLeft().getRight().getData());


        //pre.recursive(head.head);
    }


}
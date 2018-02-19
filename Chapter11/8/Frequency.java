import javax.naming.BinaryRefAddr;

/* Problem:
            Given a streaming set of ids, 
            of websites visited, 

            give back k most visited site;

    Solution: use hashtable to keep track of frequencies;


        And use a binary tree to keep it updated;
        updates is O(log D); distinct elements;
        check is O(log D + k);


        Alt Solution : Use linked list to and hashmap to keep track order

                        if doesnt exist; 
                        lookup linkedlist hashmap for existing;
                        if value 1 doesnt exist in start, addFirst(1){
                            Node value
                            count   1
                            HashMap Strings, boolean
                        }

                        put node in hashmap

                        updates: 
                            if found value {
                                if found next (+1 greater)
                                remove from node'shashmap
                                add to the other node's hasmap;
                                update main hashamap;
                            }

                            


 */           

import java.util.HashMap;

 public class Frequency {

    public BinarySearchTree tree;
    public HashMap<String,Node> map;

    public Frequency(){
        tree = new BinarySearchTree();
        map = new HashMap<String, Node>();
    }

    public void binaryTree(String[] input){

        for (int i = 0; i < input.length; i ++){
            Node insert = map.get(input[i]);

            if (insert == null){
                insert = new Node(1, input[i]);
                map.put(input[i], insert);
                tree.insert(insert);
            } else {                
                tree.remove(insert);
                insert.data++;
                tree.insert(insert);
            }
          
        }

    }

    public void display (){
        this.recurse(tree.head);
    }

    //reversed inorder recursive;
    public void recurse (Node head){
        if  (head == null){
            return;
        }

        this.recurse(head.right);
        System.out.println(head.data);
        System.out.println(head.name);
        this.recurse(head.left);         

    }

    public static void main (String args[]){

        Frequency frequency = new Frequency();

        String [] input = {
            "bob",
            "bob",
            "bob1",
            "bob2",
            "bob1",
            "bob3",
            "bob2"
        };

        frequency.binaryTree(input);
        frequency.display();
    }


 }


 

class Node {

    public int data;

    public Node left;

    public Node right;

    public Node parent;

    public String name;

    public Node(int d, String n){
        left = null;
        right = null;
        parent = null;
        data = d;
        name = n;
    }
}


class BinarySearchTree {

    public Node head;

    public BinarySearchTree (){
        head = null;
    }

    public Node insert (Node ins){
        Node parent = null;
        if (head == null){
            head = ins;
            return ins;
        }

        Node ptr = head;
        while(true){
            if (ins.data > ptr.data){
                if(ptr.right != null){
                    parent = ptr;
                    ptr = ptr.right;
                } else {
                    ptr.parent = parent;
                    ptr.right = ins;
                    break;
                }
            } else {
                if (ptr.left != null){
                    parent = ptr;
                    ptr = ptr.left;
                } else {
                    ptr.parent = parent;
                    ptr.left = ins;
                    break;
                }
            }
        }
        return ins;
    }

    public void remove (Node rem){

        if (rem.left == null && rem.right == null){
            if (rem.parent == null){
                head = null;
                return;
            }
            if (rem.parent.left == rem){
                rem.parent.left = null;
            } else {
                rem.parent.right = null;
            }
        } else if (rem.left == null || rem.right == null){
            if (rem.left == null){
                if (rem.parent == null){
                    head = rem.right;
                    return;
                }
                if (rem.parent.left == rem){
                    rem.parent.left = rem.right;
                    rem.right.parent = rem.parent;
                } else {
                    rem.parent.right = rem.right;
                    rem.right.parent = rem.parent;
                }
            } else {
                if (rem.parent == null){
                    head = rem.left;
                    return;
                }
                if (rem.parent.left == rem){
                    rem.parent.left = rem.left;
                    rem.left.parent = rem.parent;
                } else {
                    rem.parent.right = rem.left;
                    rem.left.parent = rem.parent;
                }
            }
        } else {
            Node successor = rem.right;
            Node successor_parent = rem;
            while (successor.left != null){
                successor_parent = successor;
                successor = successor.left;
            }

            if (successor.right != null){
                successor_parent.right = successor.right;
                successor.parent = successor_parent;
            } else {
                successor_parent.right = null;
            }

            if (rem.parent != null){

                if (rem.parent.right == rem){
                    rem.parent.right = successor;            
                } else {
                    rem.parent.left = successor;
                }

            } else {
                head = successor;
            }

            successor.right = rem.right;
            successor.left = rem.left;
        }

        rem.left = null;
        rem.right = null;


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






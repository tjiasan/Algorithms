/*
    Problem: Consider a server with multiple clients
            Each client (String) has a credit(integer positive) associated with it

        Design a data structure to get :
         1) Upsert Client with credit
         2) Remove Client
         3) Lookup Client
         4) Add to All Clients 
         5) Max


     Solution:
           use a comibnation of hashmap and binary search tree;
            
            1) Insert, if exists, remove it first
                then find the spot or node appropriate (hashtable inside ndoe)
                and insert it  complexity O(H)
             2) remove, complexity O(H),
                standard removing BST
                
             3) Find credits O(1), hashtable
             4) Add to all O(1), use public offset
                    return credit + offset;
                    all new inserts become credit - offset;
                 
            5) Max operation: cache the Max node, 
                do a comparison when inserts,
                if max is removed, redo O(H) calcualtion     

*/

import java.util.HashMap;

public class Credits {

    public Node root;
    public int addAll;
    public HashMap<String, Node> allClients;
    public Node max;

    public Credits(){
        root = null;
        addAll = 0;
        allClients = new HashMap<String, Node> ();
    }

    public void insert (String c, int credit){
      
        Node ins = new Node(credit - addAll, c);

        if (allClients.get(c) != null){    
            this.remove(c);
        }    
           
        if (root == null){
            root = ins;
            allClients.put(c, ins);
            return;
        }           

        Node ptr = root;  

        //find location
        while (true){
            if (ptr.data == credit){
                ptr.clients.put(c, true);
                allClients.put(c, ptr);
                return;
            } else if (credit < ptr.data){
                if (ptr.left != null){
                    ptr = ptr.left;
                } else {              
                    ptr.left = ins;                    
                    break;
                }
    
            } else {
                if (ptr.right != null){
                    ptr = ptr.right;
                } else {              
                    ptr.right = ins;                     
                    break;
                }
                
            }
        }      

        allClients.put(c, ins);  

        if (max != null){
            if (ins.data > max.data){
                max = ins;
            }
        } else {
            max = ins;
        }


    }

    public void remove (String c){
        Node client = allClients.get(c);

        if (client == null){
            return;
        }

        allClients.remove(c);
        client.clients.remove(c);

        int d = client.data;

        //delete actual node
        if (client.clients.size() == 0){
            Node delete = root;
            Node parent = null;
        
            while (delete != null){
                if (d == delete.data){                
                    break;
                } else if(d < delete.data){                
                    parent = delete;
                    delete = delete.left;
                } else {
                    parent = delete;
                    delete = delete.right;
                }
            }

            if (delete.left == null && delete.right == null){
                if (parent != null){
                    if (delete == parent.left){
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                } else {
                    root = null;
                }
            } else if (delete.left == null || delete.right == null){
                Node successor;
    
                if (delete.left == null){
                    successor = delete.right;
                } else {
                    successor = delete.left;
                }
    
                if (parent != null){
                    if (parent.right == delete){
                        parent.right = successor;
                    } else {
                        parent.left = successor;
                    }
                } else {
                    root = successor;
                }
            } else {
                //find successor, right subtree minimum
                Node successor = delete.right;
                Node successor_parent = delete;
                while (successor.left != null){
                    successor_parent = successor;
                    successor = successor.left;
                }
    
                successor_parent.left = null; //deleting the min to swap;
    
                //relink  right children, to parent's left
                //because it's still less than successor parent, more than min
                if (successor.right != null){
                    successor_parent.left = successor.right;
                } 
    
                if (parent != null){
                    if (parent.right == delete){
                        parent.right = successor;            
                    } else {
                        parent.left = successor;
                    }
    
                } else {
                    root = successor;
                }
    
                successor.right = delete.right;
                successor.left = delete.left;
    
            }
        }

        if (client == max){
            this.setMax();
        }
    }

    public void setMax (){
        Node ptr = root;

        while (ptr.right != null){
            ptr = ptr.right;
        }

        max = ptr;
    }

    public Integer find (String c){
        Node client = allClients.get(c);
        if (client != null){
            return client.data + addAll;
        } else {
            return null;
        }
       
    }

    public Integer max (){
        if (max != null){
            return max.data + addAll;
        } else {
            return null;
        }
    }

    public void addToAll (int amount) {
        addAll += amount;
    }


    public static void main (String args[]){
        
        Credits credits = new Credits();

        credits.insert("bob", 20);
        credits.insert("bob3", 30);

        System.out.println(credits.max()); //30

        credits.insert("bob3", 50);
        System.out.println(credits.find("bob3")); //prints 50

        credits.remove("bob3");

        System.out.println(credits.max()); //prints 20

        credits.addToAll(5);
        credits.insert("bob3", 30);
        System.out.println(credits.find("bob")); //25
        System.out.println(credits.find("bob3"));//30

        System.out.println(credits.max());// should print 30

  
    }



}

class Node {

    public int data;

    public Node left;

    public Node right;

    public HashMap <String, Boolean> clients;

    public Node(int d, String s){
        left = null;
        right = null;
        data = d;
        clients = new HashMap<String, Boolean> ();
        clients.put(s, true);
    }
}

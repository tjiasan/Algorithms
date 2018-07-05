/*
    Problem : 
        A deadlock happens when there are two or more competing resources waiting for another to finish

        A method of detecting a deadlock is a directed graph,
        a Node is a process, an arrow points to the resource it's holding up
        if there is a cycle, this means a deadlock is happening

     Solutions: 
        DFS + HashMap = O(V + E) time + O(V) storage;      

        if coding leader (2 steps) + follower (1 step), and see if they meet, only works if low branching
        may have exponential time complexity, because have to create multiple leaders + follwoers following
        a branching

*/

import java.util.LinkedList;
import java.util.Iterator;

class Node {

    public int visited;
    public LinkedList<Node> children;
    public Node(){
        visited = 0;
        children = new LinkedList<Node>();
    }
}

public class Deadlock {


    public void detect(Node start){
        if (start.visited == 0){
            start.visited = 1;
        } else {
            System.out.println ("Cycle Detected");
            return;
        }
       
        if (start.children.isEmpty()){
            return;
        } else {
            Iterator<Node> iter = start.children.iterator();
            while (iter.hasNext()){
                this.detect(iter.next());
            }
        }


    }

    public static void main (String args[]){

        Node start = new Node();


        Node child1 = new Node();
        Node child2 = new Node();
        Node child3 = new Node();
        Node child4 = new Node();


        start.children.push(child1);
        child1.children.push(child2);
        child2.children.push(child3);
        child3.children.push(child4);
        child4.children.push(child1);


        Deadlock deadlock = new Deadlock();

        deadlock.detect(start);

    }


}
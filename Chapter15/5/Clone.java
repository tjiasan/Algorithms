/* 
    Problem: Clone a directed graph;

    Solution: Maintain a hashmap of node node

        Use a queue to process children of to be processed nodes,

        if children hasn't been seen,
            create children copy,
            add to hashmap, 
            add to next queue
         else 
            is a cycle,
            add the copy reference to the copy node
            
            
        recurse next queue
        
    return hashmap for start to copy
    
    
    Complexity O(N) time and space

*/


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;

class Node {

    public int data;
    public LinkedList<Node> children;
    public Node(int d){
        data = d;
        children = new LinkedList<Node>();
    }
}

public class Clone {


    public Node duplicate(Node start){

        HashMap<Node, Node> visited = new HashMap<Node, Node>();

        
        LinkedList<Node> process = new LinkedList<Node>();
        process.addLast(start);

        Node copy = new Node(start.data);
        visited.put(start, copy);        

        this.recursive(visited, process);
        

        return visited.get(start);

    }

    public void recursive (HashMap<Node, Node> visited, LinkedList<Node> process){

        LinkedList<Node> next_process = new LinkedList<Node>();

        while(process.isEmpty() == false){
            
            Node current = process.removeFirst();
            Node current_copy = visited.get(current);
            Iterator<Node> child_itr = current.children.iterator();
            
            while(child_itr.hasNext()){
                Node child = child_itr.next();
                if (visited.get(child) == null){
                    Node child_copy = new Node(child.data);
                    current_copy.children.addFirst(child_copy);
                    visited.put(child, child_copy);
                    next_process.addLast(child);
                } else {
                    current_copy.children.addFirst(visited.get(child));
                }
            }
            
        }
       
        if (next_process.isEmpty() == false){
            this.recursive(visited, next_process);
        }
    }

    public static void main (String args[]){


        Clone clone = new Clone();

        Node start = new Node(1);

        Node child1 = new Node(2);
        Node child2 = new Node(3);
        Node child3 = new Node(4);
        Node child4 = new Node(5);


        start.children.push(child1);
        child1.children.push(child2);
        child2.children.push(child3);
        child3.children.push(child4);
        child4.children.push(child1);

        Node dupe = clone.duplicate(start);

        System.out.println(dupe.children.getFirst().children.getFirst().children.getFirst().children.getFirst().data);
        System.out.println(dupe.children.getFirst().children.getFirst().children.getFirst().children.getFirst().children.getFirst().data);

    }
}
/*

    Problem: 
        Given a complete graph,
        with nodes and edges,

        partition the graph into two different colors
        such that a black color node is only connected to white color
        nodes.

        Assume the graph is conected


    Solution:
        BFS
            Even iteration: one color
            next iteration : different color
            
            if ended on different color, 
                2 possibilities:
                    Graph isnt' connected, meaning that there is one node with 1 edge only
                    not possible to partition because colors are arbitrary


           BFS: 1 level black, next level white, next after is black...etc          
*/


import java.util.LinkedList;
import java.util.Iterator;
import java.util.HashMap;
class Pin {

    public LinkedList<Pin> connections;
    public int color;

    public Pin(){
        connections =  new LinkedList<Pin>();
    }
}

public class Pins {

    // any random point works
    public Boolean possible (Pin graph){

        HashMap<Pin, Boolean> visited = new HashMap<Pin, Boolean> ();
        LinkedList<Pin> queue = new LinkedList<Pin>();
        visited.put(graph, true);
        queue.addLast(graph);

        return  this.recurse(queue, visited, 0);


    }

    public Boolean recurse(LinkedList<Pin> queue, HashMap<Pin, Boolean> visited, int color){
        
        LinkedList<Pin> next_queue = new LinkedList<Pin>();

        while(queue.isEmpty() == false){
            Pin current = queue.removeFirst();

            current.color = color;
            
            Iterator<Pin> iter = current.connections.iterator();
          
            while(iter.hasNext()){
                Pin connection = iter.next();
                if (visited.get(connection) == null){
                    next_queue.addLast(connection);
                    visited.put(connection, true);
                }
            }

        }

        if (next_queue.isEmpty()){
            if (color == 0){
                return true;
            } else {
                return false;
            }

        } else {
            return this.recurse(next_queue, visited, color ^ 1);
        }

    }


    public static void main(String args[]){

        Pins pins = new Pins();

        Pin start = new Pin();

        Pin child1 = new Pin();
        Pin child2 = new Pin();

        Pin child3 = new Pin();

        start.connections.addLast(child1);
        start.connections.addLast(child2);

        child1.connections.addLast(child3);
        child1.connections.addLast(start);

        child2.connections.addLast(child3);
        child2.connections.addLast(start);

        child3.connections.addLast(child2);
        child3.connections.addLast(child1);


        System.out.println(pins.possible(start));
        System.out.println(pins.possible(child2));
    }
}
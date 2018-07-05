/*

    Dijkstra + custom min heap with update function;


    min heap update (log n); (NoT VERTEX);
    min heap insert (log n);
    min heap delete (log n);
*/
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;

class Node {
    public char data;
    public LinkedList<Edge> edges;

    public int total_distance;
    public int total_edges;
    Node source;

    public Node (char a){
        data = a;
        edges = new LinkedList<Edge>();
        source = null;
    }
}

class Edge {
    public int dist;
    public Node dest;

    public Edge (int di, Node de){
        dist = di;
        dest = de;
    }
}


class MinHeap {
    public Node[] arr; // auto scale;
    public int index;
    public HashMap<Node, Integer> position;

    public MinHeap(int size){
        arr = new Node[size];
        index = 0;
        position = new HashMap<Node, Integer> ();
    }

    public void add (Node a){
        arr[index] = a;
        position.put(a, index);
        index ++;       

        this.update(a);
    }

    public void update (Node a){

        int pos = position.get(a);
    
        if (pos == 0){
            return;
        }

        int parent_pos = pos/2;

        //if parent bigger
        if (
            arr[parent_pos].total_distance >  a.total_distance ||
            (arr[parent_pos].total_distance == a.total_distance && arr[parent_pos].total_edges > a.total_edges )
            ){
            position.put(a, parent_pos);
            position.put(arr[parent_pos], pos);

            arr[pos] = arr[parent_pos];
            arr[parent_pos] = a;
            this.update(a);
        } 

    }

    public Node poll (){
        index --;

        Node to_return = arr[0];

        arr[0] = arr[index];


        this.min_heapify(0);

        position.remove(to_return);

        return to_return;
    }

    public void min_heapify(int i){

        int left = i * 2;
        int right = i * 2 + 1;

        Node smallest = arr[i];
        int smallest_index = i;
        
        if (left < index){
            if (arr[left].total_distance < smallest.total_distance){
                smallest = arr[left];
                smallest_index = left;
            } else if (arr[left].total_distance == smallest.total_distance && arr[left].total_edges < smallest.total_edges){
                smallest = arr[left];
                smallest_index = left;
            }
        }

        if (right < index){
            if (arr[right].total_distance < smallest.total_distance){
                smallest = arr[right];
                smallest_index = right;
            } else if (arr[right].total_distance == smallest.total_distance && arr[right].total_edges < smallest.total_edges){
                smallest = arr[right];
                smallest_index = right;
            }
        }


        if (smallest_index != i){
            Node tmp = arr[i];
            position.put(arr[i], smallest_index);
            position.put(smallest, i);

            arr[i] = arr[smallest_index];
            arr[smallest_index] = tmp;
            this.min_heapify(smallest_index);
        }
    }

    public Boolean hasElements(){
        if (index > 0){
            return true;
        } else {
            return false;
        }
    }
}


public class ShortestPath {

    public void find_shortest(Node start, Node end){
        HashMap<Node, Boolean> visited = new HashMap<Node, Boolean>();

        MinHeap min_heap = new MinHeap(40);

        min_heap.add(start);
        start.total_distance = 0;
        start.total_edges = 0;

        while(min_heap.hasElements()){
            Node current = min_heap.poll();
            visited.put(current, true);
            
            Iterator<Edge> itr = current.edges.iterator();
        
  

            while (itr.hasNext()){
                Edge edge = itr.next();
          

                if (visited.get(edge.dest) == null){
                    edge.dest.total_distance = current.total_distance + edge.dist;
                    edge.dest.total_edges = current.total_edges + 1;

                    edge.dest.source = current;

                    visited.put(edge.dest, false);
                    min_heap.add(edge.dest);
                    

                } else if (visited.get(edge.dest) == false ){
                    if (edge.dest.total_distance > current.total_distance + edge.dist 
                        || ( edge.dest.total_distance == current.total_distance + edge.dist && edge.dest.total_edges > current.total_edges + 1) 
                        ){
                            edge.dest.total_distance = current.total_distance + edge.dist;
                            edge.dest.total_edges = current.total_edges + 1;
                            edge.dest.source = current;
                            min_heap.update(edge.dest);
                        }
                } //else ignore
            }

        }

    }

    public static void main (String args[]){

        ShortestPath shortest = new ShortestPath();

        Node a = new Node('a');     
        Node b = new Node('b');
        Node c = new Node('c');
        Node d = new Node('d');
        Node e = new Node('e');
        Node f = new Node('f');
        Node g = new Node('g');
        Node h = new Node('h');
        Node i = new Node('i');
        Node j = new Node ('j');
        Node k = new Node ('k');
        Node l = new Node ('l');

        Edge a1 = new Edge(2, c);
        Edge a2 = new Edge(3, b);

        a.edges.addFirst(a1);
        a.edges.addLast(a2);

        Edge b1 = new Edge(1, k);
        Edge b2 = new Edge(4, a);

        b.edges.addLast(b1);
        b.edges.addLast(b2);

        Edge c1 = new Edge(8, e);

        c.edges.addFirst(c1);

        Edge d1 = new Edge(5, c);
        Edge d2 = new Edge(5, h);

        d.edges.addLast(d1);
        d.edges.addLast(d2);

        Edge e1 = new Edge (7, d);
        e.edges.addLast(e1);

        Edge f1 = new Edge(6, g);
        f.edges.addLast(f1);

        Edge g1 = new Edge(4, h);
        Edge g2 = new Edge(7, f);
        g.edges.addLast(g1);
        g.edges.addLast(g2);

        Edge i1 = new Edge (6, j);
        i.edges.addLast(i1);

        Edge j1 = new Edge(1, f);
        Edge j2 = new Edge(7, l);
        j.edges.addLast(j1);
        j.edges.addLast(j2);

        Edge k1 = new Edge(1 ,i);
        k.edges.addLast(k1);

        Edge l1 = new Edge(9, i);
        l.edges.addLast(l1);


        shortest.find_shortest(a, h);
        System.out.println(h.total_distance);
        System.out.println(h.source.data);
      // shortest.test(a);
    }

    public void test (Node a){

        MinHeap minheap = new MinHeap(5);
        Node add1 = a.edges.removeFirst().dest;
        Node add2 = a.edges.removeFirst().dest;
        System.out.println(add1.data);
        add1.total_distance = 5;
        add2.total_distance = 3;
        minheap.add(add1);
        minheap.add(add2);

        System.out.println(minheap.poll().data);
    }
}
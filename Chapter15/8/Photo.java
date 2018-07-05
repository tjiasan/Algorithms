/*
    Problem: 
        Remember the team photo problem:
        two teams need photos together, 
        solved by sorting teams, 
        and iterate through heights starting from tallest

        Solve the problem for multiple teams;

       Solution:
            Sort int[][] array by biggest, and then by arr[i][0] 
            
            make into a graph,
            nodes have int[] array
            compare by int position, if same or smaller, than above eliminate


*/

import java.util.*;

class Node {

    public int[] data;
    public LinkedList<Node> children;

    public Node (int[] d){
        children = new LinkedList<Node>();
        data = d;
    }
}

public class Photo{

    public void reverse (int[] arr){
        int i = 0;
        int j = arr.length -1;

        while (i < j){
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }

    public boolean is_children(int[] team1, int[] team2){
        for (int i = 0; i < team1.length ; i++){
            if (team1[i] <= team2[i]){
                return false;
            }
        }
        return true;
    }

    public void possible (int[][] teams){    
    
        for (int i = 0; i < teams.length; i ++){
            Arrays.sort(teams[i]);
            this.reverse(teams[i]);
        }
        HashMap<Integer, Boolean> parents = new HashMap<Integer, Boolean>();
        HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
        //only for non primitive types
        Arrays.sort(teams, new Comparator<int[]>(){
            public int compare (int[] a, int[] b){
                return Integer.compare(b[0], a[0]);
            }
        });       
       
        for (int i = 0; i < teams.length; i ++){
            Node current = new Node(teams[i]);
            parents.put(i, true);
            nodes.put(i, current);
        }
        //Create DAG
        
        for (int i = 0; i < teams.length; i ++){            
            for (int k = i + 1; k < teams.length; k++){
                if (this.is_children(teams[i], teams[k])){         
                    nodes.get(i).children.addLast(nodes.get(k));           
                    parents.remove(k);
                }
            }
        }

        //do dfs on all parents
        int max = 0;
        for (Integer key: parents.keySet()){
           int parent_max = this.dfs(nodes.get(key));
           if (parent_max > max){
               max = parent_max;
           }
        }
        System.out.println(max);

    }

    public int dfs(Node parent){
        
        if (parent.children.isEmpty()){
            return 1;
        }

        int max = 0;

        Iterator<Node> iter = parent.children.iterator();

        while(iter.hasNext()){
            int child_max = this.dfs(iter.next());
            if (child_max > max){
                max = child_max;
            }
        }

        return max + 1;
    }



    public static void main(String args[]){
        Photo photo = new Photo();

        int [][] teams = {
            {6, 4, 3},
            {7, 5, 5}, // change to 1 to get 2 teams max
            {5, 3, 1}
        };
     
     
        photo.possible(teams);// 3 teams possible

      
    }
}
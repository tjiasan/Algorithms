/* Problem: given a ArrayList, implement a sort that is stable

    Solution: 
            1) use merge sort:
                break down into 1 length subarrays recursively;

                combine arrays one by one while sorting it;

                Space O(N), Complexity O(nlogn);


                if linked list instead of array:
                      breakdown linkedlist with recursive calls;
                      keep track of size,                     


            2) use insertion sort
                start with 1, copy current int, 
                j = i -1;
                    while(j >=0 && arr[j] < current)
                    swap j to j + 1, while it's greater than current int,
                    j--
                then insert at j;
                Space O(1), Compexity O(N ^2);
                

*/
import java.util.ArrayList;

class Node {
    public int data;
    public Node next;
}

public class ListSort{

    public void merge (ArrayList<Integer>arr, int l, int m, int r){
        ArrayList<Integer> result = new ArrayList<Integer>();

        int i = l;
        int j = m + 1;

        while(i <= m && j <= r){
            if (arr.get(i) < arr.get(j)){
                result.add(arr.get(i));
                i++;
            } else{
                result.add(arr.get(j));
                j++;
            }
        }

        while(i <= m){
            result.add(arr.get(i));
            i++;
        }

        while (j <= r){
            result.add(arr.get(j));
            j++;
        }


        for(int n = 0; n < result.size(); n++){
            arr.set(l + n , result.get(n));
        }

    }


    void insertionSort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i)
        {
            int key = arr[i]; //copy
            int j = i - 1;
 
            /* swap ahead while greater*/
            while (j >= 0 && arr[j] > key)
            {
                arr [j + 1] = arr[j];
                j --;
            }
            //insert
            arr[j + 1] = key;
        }
    } 
    public void sort (ArrayList<Integer>arr, int l, int r){
        if (l < r){
            int m = (l + r)/2;

            //sort first and second halve
            sort(arr, l, m);
            sort(arr, m + 1, r);

            //merge sorted halves
            merge(arr, l, m ,r);

        }

    }

    public Node mergeList(Node left, Node right){

        Node prev = null;
        Node ptr = left;

        Node ins = right;

        while(ins != null){
            if (ptr.data > ins.data){
                if(prev != null){
                    prev.next = ins;
                } 
                prev = ins; //set previous as inserted
                ins = ins.next; //iterate forward right list
                prev.next = ptr; //link previous to current;

                ptr = ptr.next; //iterate left list forwrd

            } else {
                prev = prev.next;
                ptr = ptr.next;
            }
        }

        return left;
    }

    public Node sortList (Node list, int size){
        if (size == 1){
            list.next = null;
            return list;
        }

        int half = size/2;
        Node halfway = list;
        for (int i = 0; i < half; i++){
            halfway = halfway.next;
        }
        
        Node left = this.sortList(list, half);
        Node right = this.sortList(halfway, size - half);

        return this.mergeList(left, right);
    }


    public static void main (String args[]){

        ArrayList<Integer> input = new ArrayList<Integer>();
        input.add(3);
        input.add(4);
        input.add(1);
        input.add(5);
        input.add(7);

        ListSort listSort = new ListSort();

        listSort.sort(input, 0, input.size() -1);

        System.out.println(input.get(0));
        System.out.println(input.get(1));
    }

}
// for native implementation use priority queue
// implement using array, childrenare at 2i +1 and 2i +2,
// parents are at ((i-1 )/2)
//heaps are always a prefect binary tree, which is why you can use an array;


class Heap {

    public int size;
    public int [] heap;
    protected int index;


    public Heap (int n){
        heap = new int[n];
        size = n;
        index = 0;
    }

    public void Swap (int i, int j){
        int tmp = heap [i];

        heap[i] = heap[j];
        heap[j] = tmp;
    }


    public void insert(int ins){
        int current = index;      

        heap[index] = ins;
        index++;       

        this.swapUp(current);

    }

    public void delete (int location){
        index--;
        this.Swap(location, index);

        heap[index] = 0; //primitive types can't null;
        
        int current = location;

        if (heap[location] < heap[(location - 1) / 2]){
            this.swapUp(current);
        } else {
            this.swapDown(current);
        }

    }

    public void swapUp(int current){
        
        int parent = (current - 1)/2;
        int current_index = current;

        while (parent > -1){
            if (heap[parent] > heap[currrent_index]){ //min heap property
                this.Swap(parent, currrent_index);
                currrent_index = parent; //since primitive type, copies value;
                parent = (currrent_index - 1)/2;
            } else {
                break;
            }
        }
    }


    public void swapDown(int current){
        int current_index = current;
        while (true){
            int child = 2 * current;
            if ( child + 1 <= index  && heap[index] > heap[child + 1] ){
                this.swap(index, child + 1);
                current_index = child + 1;
            } else if (child + 2 <= index  && heap[index] > heap[child + 2]){
                this.swap(index, child + 2);
                current_index = child + 2;
            } else {
                break;
            }
        }
    }

    //if a tree is a heap except for i;
    // use max heapify
    public static void maxHeapify(int[] arr, int n, int i){
     
        // Find largest of node and its children
        if(i >= n){
            return;
        }
        int l = i * 2 + 1;
        int r = i * 2 + 2;
        int max;
        if(l < n && arr[l] > arr[i]){
            max = l;
        } else {
            max=i;
        }
    
        if(r < n && arr[r] > arr[max]){
            max = r;
        }
        // Put maximum value at root and
        // recur for the child with the
        // maximum value
        if(max != i){
            int temp = arr[max];
            arr[max] = arr[i];
            arr[i] = temp;
            maxHeapify(arr, n, max);
        }
    }

    public void build_max_heap (int [] arr){

        for (int i = arr.length/2; i < 0 ; i --){
            this.maxHeapify(arr, arr.legnth, i);
        }

    }

    public int Peek (){
       return heap[0];
    }

}
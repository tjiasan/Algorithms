/*
public void swap (int [] arr, int i, int j){
    int tmp = arr[i];

    arr[i] = arr[j];

    arr[j] = tmp;
}

public void permute (int[] arr, int i, int n, Stack <int[]>  permutations){
    int j;

    if (i == n){    
        permutations.push(arr.clone());
    } else {
        for (j = i; j < n; j++){
            this.swap(arr, i , j);
            this.permute(arr, i + 1, n, permutations);
            this.swap(arr, i, j); //swap back because we use the same reference to array
        }
    }
    
}

public void create(int nodes){
    Stack <int[]> permutations = new Stack<int[]>();

    int[] inorder = new int[nodes];
    for (int i = 1; i <= nodes; i++){
        inorder[i -1] = i;
    }

    this.permute(inorder, 0, nodes, permutations);

    //create binary trees
    Node[] result = new Node[permutations.size()];

    int counter = 0;
    while(permutations.isEmpty() == false){
        int[] preorder = permutations.pop();
        Node[] preorderNodes = new Node[preorder.length];
        Node[] inorderNodes = new Node[inorder.length];
        for (int i = 0; i < preorder.length; i ++){
            Node current = new Node (preorder[i], null, null, null);
            preorderNodes[i] = current;
            inorderNodes[preorder[i] - 1] = current;
        }

       // System.out.println(preorderNodes[0].getData());
        //System.out.println(inorderNodes[0].getData());
        System.out.println("");
        result[counter] = this.create_tree( inorderNodes,preorderNodes);
        counter++;
    }
}
public Node[] convertNodeArray(Stack n){
    Node[] result = new Node[n.size()];

    int iterations = n.size();
    for (int i = 0; i < iterations; i++){
        result [i] = (Node) n.pop();
        
    }

    return result;
}

public Node create_tree(Node[] preorder, Node[] inorder){
    Node head = null;
    Node start = null;
    int k = inorder.length - 1;
    int n = preorder.length - 1;
   
         
    for (int i = 0; i < preorder.length; i ++){
        Stack <Node> right_inorder = new Stack <Node> ();
        Stack <Node> right_preorder = new Stack <Node> ();

        if (head == null){
            head = preorder[i];
            start = preorder[i];
           System.out.println(n);
            while (inorder[n] != preorder[i]){
                right_preorder.push(preorder[k]);
                right_inorder.push(inorder[n]);
                k--;
                n--;
            }
            n--;            
           
            if (right_inorder.empty() == false){
                Node[] arr_inorder = this.convertNodeArray(right_inorder);
                Node[] arr_preorder = this.convertNodeArray(right_preorder);
                preorder[i].setRight(create_tree(arr_preorder, arr_inorder));
            }
        } else {
            head.setLeft(preorder[i]);
            head = head.getLeft();
     
            while (inorder[n] != preorder[i]){
                right_preorder.push(preorder[k]);
                right_inorder.push(inorder[n]);              
                k--;
                n--;
            }
            n--;

            if (right_inorder.empty() == false){
                Node[] arr_inorder = this.convertNodeArray(right_inorder);
                Node[] arr_preorder = this.convertNodeArray(right_preorder);
                preorder[i].setRight(this.create_tree(arr_preorder, arr_inorder));
            }
        }

        if (preorder[i] == inorder[0]){
            break;
        }            

    }

    return start;
}*/
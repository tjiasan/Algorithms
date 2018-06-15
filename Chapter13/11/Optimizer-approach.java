public void prettify(String[] paragraph, int length){

    HashMap<Integer, LinkedList<String>> spaced = new HashMap<Integer,LinkedList<String>>();
    HashMap<Integer, Integer> line_length = new HashMap<Integer, Integer>(); // with no spaces!

    line_length.put(0 ,0);
    int counter = 0;
    LinkedList<String> line = new LinkedList<String>();
    

    for (int i = 0; i < paragraph.length; i ++){


        int current_line = line_length.get(counter); 
        int is_start = 1;

        if (line.isEmpty()){
            is_start = 0;
        }

        if (current_line + paragraph[i].length() + is_start < length ){
           line_length.put(counter, (line_length.get(counter) + paragraph[i].length() + is_start));
           
           line.addLast(paragraph[i]);

        } else {

            spaced.put(counter, line);

            counter ++;
            line = new LinkedList<String>();
            line_length.put(counter, paragraph[i].length());
            line.addLast(paragraph[i]);

        }

    }

    spaced.put(counter, line);
    
    /* double check contents
    for (int i = 0; i <= counter; i ++){
        LinkedList<String> current = spaced.get(i);
        System.out.println(i);
        while(current.isEmpty() == false){
           System.out.println(current.removeFirst());
        }  
    }
    */

}
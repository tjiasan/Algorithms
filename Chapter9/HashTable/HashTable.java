/*
Implementation :

        1) Store Keys in array;
        2) Hash function to calculate the position of the keys in the array
        3) If collision, maintain linkedlist in the array
        4)

        Complexity is O(1 + n/m), where n is the number of objects, m is the length of array

        if too large, rehash O (n + m) time to a new array;

        To update key, remove key, update, and add back,


        Applications: Anangrams 'logarithmic' and 'algorithmic' are anagrams, 
                        sort and put in hash table;

                        consider caching



*/
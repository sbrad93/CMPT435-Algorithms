class HashTable {

    LinkedList[] hashTable = null;

    HashTable(int len) {
        this.hashTable = new LinkedList[len];
    }

    public void put(int code, String element) {
        if (this.hashTable[code] == null) {
            // Create a new linked list if one doesn't exist for a given hashcode
            this.hashTable[code] = new LinkedList();
        } 
        // Add element to the top of the linked list
        this.hashTable[code].add(element);
    }

    public boolean get(int code, String element) {
        boolean res = false;
        int i = 0;
        while (i<this.hashTable[code].getSize()) {
            if (this.hashTable[code].getNode(i).getName().compareTo(element) == 0) {
                res = true;
                break;
            }
            i++;
        }
        return res;
    }

    // Print the entire hash table
    public void print() {
        for (int i=0; i<hashTable.length; i++) {
            if (hashTable[i] != null) {
                System.out.println("Hash Code: " + i);
                System.out.println("Contains elements: ");
                for (int j=0; j<hashTable[i].getSize(); j++) {
                    System.out.println(hashTable[i].getNode(j).getName());
                }
                System.out.println();
            }
        }
    }
}
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
            if (this.hashTable[code].getVertexByID(i).getID().compareTo(element) == 0) {
                res = true;
                break;
            }
            i++;
        }
        return res;
    }

    // Print the hash table
    public void print() {
        for (int i=0; i<this.hashTable.length; i++) {
            if (this.hashTable[i] != null) {
                System.out.print("Vertex " + i + ": ");
                this.hashTable[i].print();
            }
        }
    }
}
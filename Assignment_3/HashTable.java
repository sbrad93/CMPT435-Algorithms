class HashTable {

    LinkedList[] hashTable = null;
    int numComparisons = 0;
    double comparisonTotal = 0;

    HashTable(int len) {
        this.hashTable = new LinkedList[len];
        this.numComparisons = 0;
        this.comparisonTotal = 0;
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
        // Initial 'get' is 1 comparison
        this.numComparisons = 0;
        this.numComparisons++;

        boolean res = false;
        int i = 0;
        while (i<this.hashTable[code].getSize()) {
            // Increment num comparisons every time another item in linked list is traversed
            this.numComparisons++;
            if (this.hashTable[code].getNode(i).getName().compareTo(element) == 0) {
                res = true;
                break;
            }
            i++;
        }
        // Keep track of total number of comparisons
        this.comparisonTotal += this.numComparisons;
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

    // Print the number of comparisons for each search
    public void printNumComparisons() {
        System.out.println("\nNumber of Comparisons: " + this.numComparisons);
    }

    // Print the average number of comparisons after a certain number of searches
    public void printAvgComparison(int total) {
        System.out.printf("\nHash Table: Average Number of Comparisons:  %.2f %n", this.comparisonTotal/total);
    }
}
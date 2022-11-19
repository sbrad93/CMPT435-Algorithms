class AdjacencyList {

    private HashTable adjTable = null;

    AdjacencyList(int numVertices) {
        // a hash table of vertices
        this.adjTable = new HashTable(numVertices+1);
    }

    // Add each vertice to the other vertice's neighbors list
    public void createEntry(String vertex1, String vertex2) {
        this.adjTable.put(Integer.parseInt(vertex1), vertex2);
        this.adjTable.put(Integer.parseInt(vertex2), vertex1);
    }

    // Print the adjacency list
    public void print() {
        this.adjTable.print();;
    }

    public AdjacencyList getAdjacenyList() {
        return this;
    }
}
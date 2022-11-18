class AdjacencyList {

    private LinkedList[] adjArr = null;

    AdjacencyList(int numVertices) {
        // an array of LinkedLists with a length of the number of vertices
        this.adjArr = new LinkedList[numVertices];
    }

    // Add each vertice to the other vertice's neighbors list
    public void createEntry(int vertex1, int vertex2) {
        for (int i=0; i<this.adjArr.length; i++) {
            if (i == vertex1) {
                // Create a linked list if one doesn't exist
                if (this.adjArr[i] == null) {
                    this.adjArr[i] = new LinkedList();
                }
                this.adjArr[i].add(vertex2+"");
            }
        }
        for (int j=0; j<this.adjArr.length; j++) {
            if (j == vertex2) {
                // Create a linked list if one doesn't exist
                if (this.adjArr[j] == null) {
                    this.adjArr[j] = new LinkedList();
                }
                this.adjArr[j].add(vertex1+"")
;            }
        }
    }

    // Print the Adjacency List
    public void print() {
        for (int i=0; i<this.adjArr.length; i++) {
            if (this.adjArr[i] != null) {
                System.out.print("Vertex " + i + ": ");
                this.adjArr[i].print();
            }
        }
    }

    public AdjacencyList getAdjacenyList() {
        return this;
    }
}
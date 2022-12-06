class Graph {

    private VertexLinkedList vertices = null;

    Graph(String[] verticeArr) {
        this.vertices = new VertexLinkedList();
    }

    public void addVertex(String vid) {
        this.vertices.add(vid);
    }

    // Create an edge between two vertices
    public void createEdge(String vid1, String vid2) {
        Vertex vertex1 = this.vertices.getVertexByID(vid1);
        Vertex vertex2 = this.vertices.getVertexByID(vid2);
        vertex1.getNeighbors().add(vid2);
        vertex2.getNeighbors().add(vid1);
    }

    /* Getters */
    public VertexLinkedList getVertices() {
        return this.vertices;
    } // getVertices
}
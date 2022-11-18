class Graph {

    private LinkedList vertices = null;
    private AdjacencyList adjList = null;
    private Matrix matrix = null;

    Graph(String[] verticeArr) {
        this.vertices = new LinkedList();
        this.adjList = new AdjacencyList(verticeArr.length);
        this.matrix = new Matrix(verticeArr);
    }

    public void addVertex(String vid) {
        this.vertices.add(vid);
    }

    // Create an edge between two vertices
    public void createEdge(String vid1, String vid2) {
        Vertex vertex1 = new Vertex(vid1+"");
        Vertex vertex2 = new Vertex(vid2+"");
        vertex1.getNeighbors().add(vid2+"");
        vertex2.getNeighbors().add(vid1+"");

        this.adjList.createEntry(vid1, vid2);
        this.matrix.createEntry(vid1, vid2);
    }

    /* Getters */
    public LinkedList getVertices() {
        return this.vertices;
    } // getVertices

    public AdjacencyList getAdjacencyList() {
        return this.adjList;
    } // getAdjacencyList

    public Matrix getMatrix() {
        return this.matrix;
    } // getMatrix
}
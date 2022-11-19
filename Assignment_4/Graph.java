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
        Vertex vertex1 = this.vertices.getVertexByID(vid1);
        Vertex vertex2 = this.vertices.getVertexByID(vid2);
        vertex1.getNeighbors().add(vid2);
        vertex2.getNeighbors().add(vid1);

        this.adjList.createEntry(vid1, vid2);
        this.matrix.createEntry(vid1, vid2);
    }

    // Depth-first traveral
    public void DFS(Vertex vertex) {
        if (!vertex.isProcessed) {
            System.out.print(vertex.getID() + " ");
            vertex.isProcessed = true;
        }
        for (int i=0; i<vertex.getNeighbors().getSize(); i++) {
            Vertex neighbor = vertex.getNeighbors().getVertexAt(i);

            // Get the og vertex object with this id
            // This is the object with populated neighbors
            neighbor = this.vertices.getVertexByID(neighbor.getID());
            if (!neighbor.isProcessed) {
                this.DFS(neighbor);
            }
        }
    }

    // Breadth-first traversal
    public void BFS(Vertex vertex) {
        Queue queue = new Queue();
        // I'm still confused by this, but for some reason if I enqueue the original vertex object and then dequeue it,
        // this.vertices somehow is also changed (??)
        // So I created a temp vertex with the same id to avoid this...but I'm certain I'm just not seeing something
        Vertex temp = new Vertex(vertex.getID());
        queue.enqueue(temp);
        vertex.isProcessed = true;

        while (!queue.isEmpty()) {
            Vertex currVertex = queue.dequeue();
            currVertex = this.vertices.getVertexByID(currVertex.getID());

            System.out.print(currVertex.getID() + " ");

            for (int i=0; i<currVertex.getNeighbors().getSize(); i++) {
                Vertex neighbor = currVertex.getNeighbors().getVertexAt(i);
                neighbor = this.vertices.getVertexByID(neighbor.getID());
                if (!neighbor.isProcessed) {
                    // Same reason as mentioned above
                    temp = new Vertex(neighbor.getID());
                    queue.enqueue(temp);
                    neighbor.isProcessed = true;
                }
            }
        }
    }

        // This is my original version that crashes. 
        // Breadth-first traversal
        // public void BFS(Vertex vertex) {
        //     Queue queue = new Queue();
        //     queue.enqueue(vertex);
        //     vertex.isProcessed = true;

        //     // all good here
        //     this.vertices.print();

        //     while (!queue.isEmpty()) {
        //         Vertex currVertex = queue.dequeue();
        //         currVertex = this.vertices.getVertexByID(currVertex.getID());
    
        //         // Why is this [1] ?
        //         this.vertices.print();

        //         // ...And this is still accurate
        //         System.out.println(this.vertices.getSize());

        //         System.out.print(currVertex.getID() + " ");
    
        //         for (int i=0; i<currVertex.getNeighbors().getSize(); i++) {
        //             Vertex neighbor = currVertex.getNeighbors().getVertexAt(i);
        //             neighbor = this.vertices.getVertexByID(neighbor.getID());
        //             if (!neighbor.isProcessed) {
        //                 queue.enqueue(neighbor);
        //                 neighbor.isProcessed = true;
        //             }
        //         }
        //     }
        // }

    public void resetVerticeProcessStatuses() {
        for (int i=0; i<this.vertices.getSize(); i++) {
            this.vertices.getVertexAt(i).isProcessed = false;
        }
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
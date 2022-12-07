class Edge {

    private VertexLinkedList vertices = null;
    private int weight = 0;

    public Edge(Vertex vertex1, Vertex vertex2, int _weight) {
        this.vertices = new VertexLinkedList();
        this.vertices.add(vertex1.getID());
        this.vertices.add(vertex2.getID());
        this.weight = _weight;
        this.distanceInit();
    }

    // Initalize all non-source distances to max int
    // Source diatnace get intialized to 0
    public void distanceInit() {
        for (int i=0; i<this.vertices.getSize(); i++) {
            if (this.vertices.getVertexAt(i).getID().compareTo("1") == 0) {
                this.vertices.getVertexAt(i).setDistance(0);
                this.vertices.getVertexAt(i).setSrc(true);

            } else {
                this.vertices.getVertexAt(i).setDistance(Integer.MAX_VALUE);
            }
        }
    }

    public void print() {
        for (int i=0; i<this.vertices.getSize(); i++) {
            this.vertices.getVertexAt(i).print();
            System.out.println();
        }
        System.out.println();
        System.out.println("weight: " + weight);
        System.out.println();
        System.out.println("-----------------");
        System.out.println();
    }

    public VertexLinkedList getVertices() {
        return this.vertices;
    }

    public int getWeight() {
        return this.weight;
    }
}
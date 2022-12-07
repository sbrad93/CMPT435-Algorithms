import java.util.ArrayList;

class Graph {

    private VertexLinkedList vertices = null;
    private ArrayList<Edge> edges = null;

    Graph(String[] verticeArr) {
        this.vertices = new VertexLinkedList();
        this.edges = new ArrayList<Edge>();
    }

    public void addVertex(String vid) {
        this.vertices.add(vid);
    }

    // Create an edge between two vertices
    public void createEdge(String vid1, String vid2, int weight) {
        Vertex vertex1 = this.vertices.getVertexByID(vid1);
        Vertex vertex2 = this.vertices.getVertexByID(vid2);
        vertex1.getNeighbors().add(vid2);
        vertex2.getNeighbors().add(vid1);
        
        Edge edge = new Edge(vertex1, vertex2, weight);
        this.edges.add(edge);
    }

    public VertexLinkedList getVertices() {
        return this.vertices;
    }

    public boolean bellmanFord() {
        for (int k=0; k<this.vertices.getSize()-1; k++) {
            for (int i=0; i<this.edges.size(); i++) {
                this.relax(this.edges.get(i));
            }
            // // for (int i=0; i<this.edges.size(); i++) {
            //     this.relax(this.edges.get(0));
            // // }
        }
        for (int i=0; i<this.edges.size(); i++) {
            Vertex v1 = this.edges.get(i).getVertices().getVertexAt(0);
            Vertex v2 = this.edges.get(i).getVertices().getVertexAt(1);
            int weight = this.edges.get(i).getWeight();

            if (v2.getDistance() > v1.getDistance() + weight) {
                return false;
            }
        }
        this.updateVertices();
        return true;
    }

    public void relax(Edge edge) {
        Vertex v1 = edge.getVertices().getVertexAt(0);
        Vertex v2 = edge.getVertices().getVertexAt(1);
        int weight = edge.getWeight();

        // System.out.println("Before");
        // System.out.println(v1.getID() + " : " + v1.getDistance());
        // System.out.println(v2.getID() + " : " + v2.getDistance());
        // System.out.println("weight: " + weight);

        if (v1.getDistance() != Integer.MAX_VALUE && v2.getDistance() > v1.getDistance() + weight) {
            v2.setDistance(v1.getDistance() + weight);
            this.updateDistance(v2, v1.getDistance() + weight);
            v2.setPrev(v1);
            this.updatePrev(v2, v1);
        }

        // System.out.println("After");
        // System.out.println(v1.getID() + " : " + v1.getDistance());
        // System.out.println(v2.getID() + " : " + v2.getDistance());
        // System.out.println();
    }

    public void updateDistance(Vertex v, int distance) {
        // System.out.println("updating to distance: " + distance);
        for (int i=0; i<this.edges.size(); i++) {
            for (int j=0; j<this.edges.get(i).getVertices().getSize(); j++) {
                if (this.edges.get(i).getVertices().getVertexAt(j).getID().compareTo(v.getID()) == 0) {
                    // System.out.println("hi");
                    this.edges.get(i).getVertices().getVertexAt(j).setDistance(distance);
                }
            }
        }
    }

    public void updatePrev(Vertex v, Vertex prev) {
        for (int i=0; i<this.edges.size(); i++) {
            for (int j=0; j<this.edges.get(i).getVertices().getSize(); j++) {
                if (this.edges.get(i).getVertices().getVertexAt(j).getID().compareTo(v.getID()) == 0) {
                    // System.out.println("hi");
                    this.edges.get(i).getVertices().getVertexAt(j).setPrev(prev);
                }
            }
        }
    }

    public void updateVertices() {
        // VertexLinkedList temp = new VertexLinkedList();
        for (int k=0; k<this.vertices.getSize(); k++) {
            edgeSearch:
            for (int i=0; i<this.edges.size(); i++) {
                for (int j=0; j<this.edges.get(i).getVertices().getSize(); j++) {
                    if (this.edges.get(i).getVertices().getVertexAt(j).getID().compareTo(this.vertices.getVertexAt(k).getID()) == 0) {
                        this.edges.get(i).getVertices().getVertexAt(j).print();
                        System.out.println();
                        break edgeSearch;
                    }
                }
            }
        }
    }

    public void print() {
        for (int i=0; i<this.edges.size(); i++) {
            this.edges.get(i).print();
        }
    }

    public String getResults() {
        String res = "";
        for (int i=0; i<this.vertices.getSize(); i++) {
            this.vertices.getVertexAt(i).print();
        }
        return res;
    }
}
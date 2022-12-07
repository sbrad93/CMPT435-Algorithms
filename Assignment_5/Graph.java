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

    // Creates an edge between two vertices
    public void createEdge(String vid1, String vid2, int weight) {
        Vertex vertex1 = this.vertices.getVertexByID(vid1);
        Vertex vertex2 = this.vertices.getVertexByID(vid2);
        vertex1.getNeighbors().add(vid2);
        vertex2.getNeighbors().add(vid1);
        
        Edge edge = new Edge(vertex1, vertex2, weight);
        this.edges.add(edge);
    }

    // Bellman-Ford Algorithm, returns boolean indicating if valid
    public boolean bellmanFord() {
        for (int k=0; k<this.vertices.getSize()-1; k++) {
            for (int i=0; i<this.edges.size(); i++) {
                this.relax(this.edges.get(i));
            }
        }

        for (int i=0; i<this.edges.size(); i++) {
            Vertex v1 = this.edges.get(i).getVertices().getVertexAt(0);
            Vertex v2 = this.edges.get(i).getVertices().getVertexAt(1);
            int weight = this.edges.get(i).getWeight();

            if (v2.getDistance() > v1.getDistance() + weight) {
                return false;
            }
        }
        return true;
    }

    public void relax(Edge edge) {
        Vertex v1 = edge.getVertices().getVertexAt(0);
        Vertex v2 = edge.getVertices().getVertexAt(1);
        int weight = edge.getWeight();

        if (v1.getDistance() != Integer.MAX_VALUE && v2.getDistance() > v1.getDistance() + weight) {
            v2.setDistance(v1.getDistance() + weight);
            this.updateDistance(v2, v1.getDistance() + weight);
            v2.setPrev(v1);
            this.updatePrev(v2, v1);
        }
    }

    // Ensures that distances are consistent throughout all occurances of a given vertex
    public void updateDistance(Vertex v, int distance) {
        for (int i=0; i<this.edges.size(); i++) {
            for (int j=0; j<this.edges.get(i).getVertices().getSize(); j++) {
                if (this.edges.get(i).getVertices().getVertexAt(j).getID().compareTo(v.getID()) == 0) {
                    this.edges.get(i).getVertices().getVertexAt(j).setDistance(distance);
                }
            }
        }
    }

    // Ensures that prev vertices are consistent throughout all occurances of a given vertex
    public void updatePrev(Vertex v, Vertex prev) {
        for (int i=0; i<this.edges.size(); i++) {
            for (int j=0; j<this.edges.get(i).getVertices().getSize(); j++) {
                if (this.edges.get(i).getVertices().getVertexAt(j).getID().compareTo(v.getID()) == 0) {
                    this.edges.get(i).getVertices().getVertexAt(j).setPrev(prev);
                }
            }
        }
    }

    // Prints the final results
    public void getResults() {
        ArrayList<Vertex> results = new ArrayList<Vertex>();
        for (int k=0; k<this.vertices.getSize(); k++) {
            edgeSearch:
            for (int i=0; i<this.edges.size(); i++) {
                for (int j=0; j<this.edges.get(i).getVertices().getSize(); j++) {
                    // Find the first occurance of an edge vertex that exists in the vertices list
                    // Add that vertex to a results list for printing later
                    if (this.edges.get(i).getVertices().getVertexAt(j).getID().compareTo(this.vertices.getVertexAt(k).getID()) == 0) {
                        Vertex vertex = this.edges.get(i).getVertices().getVertexAt(j);
                        results.add(vertex);
                        break edgeSearch;
                    }
                }
            }
        }

        for (int i=0; i<results.size(); i++) {
            ArrayList<Vertex> path = new ArrayList<Vertex>();
            if (!results.get(i).isSrc()) {
                Vertex start = results.get(i);
                path.add(start);

                System.out.print("1 --> " + start.getID() + "; ");
                System.out.print("cost is " + start.getDistance() + "; ");
                System.out.print("path is ");
                
                Vertex prev = results.get(i).getPrev();
                while (prev != null) {
                    path.add(prev);
                    prev = prev.getPrev();
                }
            }
            for (int j=path.size()-1; j>=0; j--) {
                if (j != 0) {
                    System.out.print(path.get(j).getID() + " --> ");
                } else {
                    System.out.print(path.get(j).getID());
                }
            }
            System.out.println();
            System.out.println();
        }
    }

    public void print() {
        for (int i=0; i<this.edges.size(); i++) {
            this.edges.get(i).print();
        }
    }
}
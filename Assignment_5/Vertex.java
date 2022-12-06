class Vertex {

    public boolean isProcessed = false;
    private String id = "";
    private Vertex next = null;
    private VertexLinkedList neighbors = null;

    /* Vertex class constructor */
    public Vertex(String id) {
        this.id = id;
        this.next = null;
        this.isProcessed = false;
        this.neighbors = new VertexLinkedList();
    }

    /* Getters and Setters */
    public String getID() {
        return this.id;
    } // getID

    public Vertex getNext() {
        return this.next;
    } // getNext

    public void setID(String newID) {
        this.id = newID;
    } // setID

    public void setNext(Vertex newNext) {
        this.next = newNext;
    } // setNext

    public VertexLinkedList getNeighbors() {
        return this.neighbors;
    } // getNeighbors
}
class Vertex {

    public boolean isProcessed = false;
    private String id = "";
    private Vertex next = null;
    private LinkedList neighbors = null;

    /* Vertex class constructor */
    public Vertex(String id) {
        this.id = id;
        this.next = null;
        this.isProcessed = false;
        this.neighbors = new LinkedList();
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

    public LinkedList getNeighbors() {
        return this.neighbors;
    } // getNeighbors
}
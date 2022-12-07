class Vertex {

    private String id = "";
    private int distance = -1;
    private Vertex next = null;
    private Vertex prev = null;
    private boolean isSrc = false;
    private VertexLinkedList neighbors = null;

    /* Vertex class constructor */
    public Vertex(String id) {
        this.id = id;
        this.distance = -1;
        this.next = null;
        this.prev = null;
        this.isSrc = false;
        this.neighbors = new VertexLinkedList();
    }

    public void print() {
        System.out.println("Vertex: " + this.id + 
                            "\nDistance: " + this.distance);
        if (this.prev != null) {
            System.out.println("Prev: " + this.prev.getID());
        } else {
            System.out.println("Prev: " + this.prev);
        }
    }

    public boolean isSrc() {
        return this.isSrc;
    }

    /* Getters and Setters */
    public String getID() {
        return this.id;
    } // getID

    public Vertex getNext() {
        return this.next;
    } // getNext

    public Vertex getPrev() {
        return this.prev;
    } // getPrev

    public int getDistance() {
        return this.distance;
    } // getDistance

    public void setID(String newID) {
        this.id = newID;
    } // setID

    public void setNext(Vertex newNext) {
        this.next = newNext;
    } // setNext

    public void setPrev(Vertex newPrev) {
        this.prev = newPrev; 
    } // setPrev

    public void setDistance(int newDist) {
        this.distance = newDist;
    } // setDistance

    public void setSrc(boolean bool) {
        this.isSrc = bool;
    } // setSrc

    public VertexLinkedList getNeighbors() {
        return this.neighbors;
    } // getNeighbors
}
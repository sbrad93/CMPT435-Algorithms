class Node {

    private String name = "";
    private Node next = null;

    /* Node class constructor */
    public Node(String name) {
        this.name = name;
        this.next = null;
    }

    /* Getters and Setters */
    public String getName() {
        return this.name;
    } // getName

    public Node getNext() {
        return this.next;
    } // getNext

    public void setName(String newName) {
        this.name = newName;
    } // setName

    public void setNext(Node newNext) {
        this.next = newNext;
    } // setNext

    @Override
    public String toString() {
        return("Name: " + this.getName() + "\n");
    } // toString
} // Node
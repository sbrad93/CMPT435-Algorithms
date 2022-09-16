class Node {

    String name = "";
    Node next = null;

    public Node(String name) {
        this.name = name;
        this.next = null;
    }

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
        return("Name: " + this.getName());
    } // toString
} // Node
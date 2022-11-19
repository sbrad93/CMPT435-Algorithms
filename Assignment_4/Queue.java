class Queue {

    private Vertex head = null;
    private Vertex tail = null;
    private int length = 0;

    /* Queue class constructor */
    Queue() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    public void enqueue(Vertex newVertex) {

        if (this.head == null) {
            // Head and tail are the same if queue length is 1
            this.head = newVertex;
            this.tail = newVertex;
        } else {
            // Make the tail's next the new Vertex
            this.tail.setNext(newVertex);
            // Make the new Vertex the tail
            this.tail = newVertex;
            // Set the new Vertex next value to null
            newVertex.setNext(null);
        }

        this.length++;
    } // enqueue

    public Vertex dequeue() {
        Vertex x = null;
        if (this.head != null) {
            // If the queue isn't empty, grab the current head Vertex
            x = this.head;
            // Update the head
            this.head = x.getNext();
            x.setNext(null);
            // Decrement the queue length
            this.length--;
        }
        return x;
    } // dequeue

    public void print() {
        // Prints all Vertices in the list
        Vertex currVertex = this.head;
        int i = 0;

        while (currVertex != null) {
            System.out.println("Index: "+ i + "\n" +
                                currVertex.toString());

            // Set the new next
            currVertex = currVertex.getNext();
            i++;
        }
        System.out.println();
    }

    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        } else {
            return false;
        }
    } // isEmpty

    /* Getters and Setters */
    public Vertex getHead() {
        return this.head;
    } // getHead

    public Vertex getTail() {
        return this.tail;
    } // getTail

    public int getLength() {
        return this.length;
    } // getLength

    public void setHead(Vertex newHead) {
        this.head = newHead;
    } // setHead

    public void setTail(Vertex newTail) {
        this.tail = newTail;
    } // setTail
}
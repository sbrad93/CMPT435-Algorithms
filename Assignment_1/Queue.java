class Queue {

    private Node head = null;
    private Node tail = null;
    private int length = 0;

    /* Queue class constructor */
    Queue() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    public void enqueue(String str) {
        Node newNode = new Node(str);

        if (this.head == null) {
            // Head and tail are the same if queue length is 1
            this.head = newNode;
            this.tail = newNode;
        } else {
            // Make the tail's next the new node
            this.tail.setNext(newNode);
            // Make the new node the tail
            this.tail = newNode;
            // Set the new node next value to null
            newNode.setNext(null);
        }

        this.length++;
    } // enqueue

    public Node dequeue() {
        Node x = null;
        if (this.head != null) {
            // If the queue isn't empty, grab the current head node
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
        // Prints all nodes in the list
        Node currNode = this.head;
        int i = 0;

        while (currNode != null) {
            System.out.println("Index: "+ i + "\n" +
                                currNode.toString());

            // Set the new next
            currNode = currNode.getNext();
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
    public Node getHead() {
        return this.head;
    } // getHead

    public Node getTail() {
        return this.tail;
    } // getTail

    public int getLength() {
        return this.length;
    } // getLength

    public void setHead(Node newHead) {
        this.head = newHead;
    } // setHead

    public void setTail(Node newTail) {
        this.tail = newTail;
    } // setTail
}
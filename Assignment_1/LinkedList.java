class LinkedList {

    Node head = null;

    LinkedList() {
    }

    public void add(String str) {
        Node newNode = new Node(str);

        if (this.head == null) {
            // If the list is empty, set the head
            this.head = newNode;
        } else {
            // Otherwise traverse entire list and add newNode to the end
            Node lastNode = this.head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }

            lastNode.next = newNode;
        }
    } // add

    public void print() {
        // Prints all nodes in the list
        Node currNode = this.head;
        int i = 0;

        while (currNode != null) {
            System.out.println("Index: "+ i + "\n" +
                                currNode.toString() + "\n\n");

            // Set the new next
            currNode = currNode.next;
            i++;
        }
    } // print

    public LinkedList getLinkedList() {
        return this;
    } // getLinkedList

    public Node getHead() {
        return this.head;
    } // getHead

    public void setHead(Node newHead) {
        this.head = newHead;
    } // setHead

}
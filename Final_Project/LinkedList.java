class LinkedList {

    private Node head = null;
    private int length = 0;

    /* LinkedList class constructor */
    LinkedList() {
        this.head = null;
        this.length = 0;
    } // LinkedList

    // Add an element to the end of the list
    public void add(String str) {
        Node newNode = new Node(str);

        if (this.head == null) {
            // If the list is empty, set the head
            this.head = newNode;
        } else {
            // Otherwise traverse entire list and add newNode to the end
            Node lastNode = this.head;
            while (lastNode.getNext() != null) {
                lastNode = lastNode.getNext();
            }

            // lastNode.next = newNode;
            lastNode.setNext(newNode);
        }
        //increment length
        this.length++;
    }

    // Remove an element from the list
    public void remove(String str) {
        Node currNode = this.head;
        Node prevNode = null;
        int i =0;
        while (currNode != null) {
            if (str.equals(currNode.getName())) {
                if (prevNode == null) {
                    // Removing the head of the list
                    // Update head node
                    this.head = currNode.getNext();
                } else {
                    // Connect prevNode's next to currNode's next
                    prevNode.setNext(currNode.getNext());
                    // CurrNode is removed
                    currNode.setNext(null);
                }
            }
            i++;
            prevNode = currNode;
            currNode = currNode.getNext();
        }
        this.length--;
    }

    // Get target node
    public Node getNode(int targetIndex) {
        Node res = null;
        Node currNode = this.head;
        int i = 0;

        while (currNode != null) {
            if (i == targetIndex) {
                res = currNode;
                break;
            }

            // Set the new next
            currNode = currNode.getNext();
            i++;
        }
        return res;
    }

    // Print the list
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

    // Check if the list is empty
    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        } else {
            return false;
        }
    }

    // Get the size of the list
    public int getSize() {
        return this.length;
    }

    /* Getters and Setters */
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
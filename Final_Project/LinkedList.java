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

    // Remove a Node from the list given a target index
    public String removeAt(int index) {
        Node currNode = this.head;
        Node prevNode = null;
        String removedElement = "";
        int i = 0;
        while (currNode != null) {
            if (index == i) {
                removedElement = currNode.getName();
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
        return removedElement;
    }

    // Remove a target Node from the list
    public String removeNode(String target) {
        Node currNode = this.head;
        Node prevNode = null;
        String removedElement = "";
        while (currNode != null) {
            if (currNode.getName().compareTo(target) == 0) {
                removedElement = target;
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
            prevNode = currNode;
            currNode = currNode.getNext();
        }
        this.length--;
        return removedElement;
    }

    // Get target node given a target index
    public Node getNode(int targetIndex) {
        Node ans = null;
        Node currNode = this.head;
        int i = 0;

        while (currNode != null) {
            if (i == targetIndex) {
                ans = currNode;
                break;
            }

            // Set the new next
            currNode = currNode.getNext();
            i++;
        }
        return ans;
    }

    // Get a Node index given the Node name
    public int getIndex(String target) {
        int ans = -1;
        Node currNode = this.head;
        int i = 0;

        while (currNode != null) {
            if (currNode.getName().compareTo(target) == 0) {
                ans = i;
                break;
            }

            currNode = currNode.getNext();
            i++;
        }
        return ans;
    }

    // Check if the list contains a target string
    public boolean doesContain(String element) {
        boolean ans = false;
        Node currNode = this.head;
        while (currNode != null) {
            if (currNode.getName().compareTo(element) == 0) {
                ans = true;
            }
        }
        return ans;
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
class LinkedList {

    private Vertex head = null;
    private int length = 0;

    /* LinkedList class constructor */
    LinkedList() {
        this.head = null;
        this.length = 0;
    } // LinkedList

    public void add(String str) {
        Vertex newVertex = new Vertex(str);

        if (this.head == null) {
            // If the list is empty, set the head
            this.head = newVertex;
        } else {
            // New elements are added to the top of the list
            newVertex.setNext(this.head);
            this.head = newVertex;
        }
        //increment length
        this.length++;
    } // add

    public void remove(String str) {
        Vertex currVertex = this.head;
        Vertex prevVertex = null;
        while (currVertex != null) {
            if (str.equals(currVertex.getID())) {
                if (prevVertex == null) {
                    // Removing the head of the list
                    // Update head
                    this.head = currVertex.getNext();
                } else {
                    // Connect prevVertex's next to currVertex's next
                    prevVertex.setNext(currVertex.getNext());
                    // currVertex is removed
                    currVertex.setNext(null);
                }
            }
            prevVertex = currVertex;
            currVertex = currVertex.getNext();
        }
        //decrement length
        this.length--;
    } // remove

    public Vertex getVertexByID(int targetIndex) {
        Vertex res = null;
        Vertex currVertex = this.head;
        int i = 0;

        while (currVertex != null) {
            if (i == targetIndex) {
                res = currVertex;
                break;
            }

            // Set the new next
            currVertex = currVertex.getNext();
            i++;
        }
        return res;
    }

    public void print() {
        Vertex currVertex = this.head;

        System.out.print("[");
        while (currVertex != null) {
            System.out.print(currVertex.getID());
            if (currVertex.getNext() != null) {
                System.out.print(" , ");
            } 

            // Set the new next
            currVertex = currVertex.getNext();
        }
        System.out.print("]");
        System.out.println();
    } // print

    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        } else {
            return false;
        }
    } // isEmpty

    /* Getters and Setters */
    public LinkedList getLinkedList() {
        return this;
    } // getLinkedList

    public Vertex getHead() {
        return this.head;
    } // getHead

    public void setHead(Vertex newHead) {
        this.head = newHead;
    } // setHead

    public int getSize() {
        return this.length;
    }
}
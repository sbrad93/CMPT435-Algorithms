class Stack {

    private Node top = null;
    private int length = 0;

    /* Stack class constructor */
    Stack() {
        this.length = 0;
    }

    public Node pop() {
        Node x = null;
        if (top != null) {
            x = this.top;
            this.top = x.getNext();

            this.length--;
        }
        return x;
    } // pop

    public void push(String str) {
        Node newNode = new Node(str);

        // newNode.next = this.top;
        newNode.setNext(this.top);
        this.top = newNode;

        this.length++;
    } // push

    public void print() {
        // Prints all nodes in the stack
        Node currNode = this.top;

        while (currNode != null) {
            System.out.println(currNode.toString());

            // Set the new next
            currNode = currNode.getNext();
        }
    } // print

    public boolean isEmpty() {
        if (this.top == null) {
            return true;
        } else return false;
    } // isEmpty

    /* Getters and Setters */
    public Stack getStack() {
        return this;
    } // getStack

    public Node getTop() {
        return this.top;
    } // getTop

    public int getLength() {
        return this.length;
    } // getLength

    public void setTop(Node newTop) {
        this.top = newTop;
    } // setTop
}

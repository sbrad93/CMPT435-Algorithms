class Stack {

    Node top = null;
    int length = 0;

    Stack() {
        this.length = 0;
    }

    public Node pop() {
        Node x = null;
        if (top != null) {
            x = this.top;
            this.top = x.next;

            this.length--;
        }
        return x;
    } // pop

    public void push(String str) {
        Node newNode = new Node(str);

        newNode.next = this.top;
        this.top = newNode;

        this.length++;
    } // push

    public void print() {
        // Prints all nodes in the stack
        Node currNode = this.top;

        while (currNode != null) {
            System.out.println(currNode.toString());

            // Set the new next
            currNode = currNode.next;
        }
    } // print

    public Stack getStack() {
        return this;
    } // getStack

    public Node getTop() {
        return this.top;
    } // getTop

    public void setTop(Node newTop) {
        this.top = newTop;
    } // setTop
}

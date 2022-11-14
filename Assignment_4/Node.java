class Node {

    private String name = "";
    private Node left = null;
    private Node right = null;
    private Node parent = null;

    /* Node class constructor */
    public Node(String name) {
        this.name = name;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    /* Getters and Setters */
    public String getName() {
        return this.name;
    } // getName

    public Node getLeft() {
        return this.left;
    } // getLeft

    public Node getRight() {
        return this.right;
    } // getRight

    public Node getParent() {
        return this.parent;
    } // getParent

    public void setName(String newName) {
        this.name = newName;
    } // setName

    public void setLeft(Node newLeft) {
        this.left = newLeft;
    } // setLeft

    public void setRight(Node newRight) {
        this.right = newRight;
    } // setLeft

    public void setParent(Node newParent) {
        this.parent = newParent;
    } // setParent

    @Override
    public String toString() {
        return("Name: " + this.getName() + "\n");
    } // toString
}
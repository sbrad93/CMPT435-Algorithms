class BinarySearchTree {

    private Node root = null;
    private int numComparisons;
    private double totalComparisons;

    public BinarySearchTree() {
        this.numComparisons = 0;
        this.totalComparisons = 0;
    }

    // Inserts a new node into the tree
    public void insert(String nodeName) {
        Node node = new Node(nodeName);
        Node curr = this.root;
        Node trailing = null;

        while (curr != null) {
            trailing = curr;
            if (node.getName().compareTo(curr.getName()) < 0) {
                curr = curr.getLeft();
            } else {
                curr = curr.getRight();
            }
        }
        node.setParent(trailing);
        if (trailing == null) {
            this.root = node;
        } else {
            if (node.getName().compareTo(trailing.getName()) < 0) {
                trailing.setLeft(node);
            } else {
                trailing.setRight(node);
            }
        }
    }

    // Prints the path from the root to a target node
    public void printNodePath(String target) {
        Node curr = this.root;
        String path = "";
        Boolean isFound = false;
        this.numComparisons = 0;

        System.out.println("Node path for: " + target);
        while (curr != null) {
            this.numComparisons++;
            if (target.compareTo(this.root.getName()) == 0) {                                                 // target is the root node
                path = "no path to root node";
                isFound = true;
                break;
            } else {
                if (target.compareTo(curr.getName()) < 0) {                                                   // target is left of the current node
                    curr = curr.getLeft();
                    path += " L";
                } else if (target.compareTo(curr.getName()) > 0) {                                            // target is right of the current node
                    curr = curr.getRight();
                    path += " R";
                } else {
                    if (target.compareTo(curr.getName()) == 0 || target.compareTo(curr.getName()) == 0) {     // we found the target node (yay)
                        isFound = true;
                        break;
                    }
                }
            }
        }
        this.totalComparisons += this.numComparisons;
        if (isFound) {
            System.out.println(path);
        } else {
            System.out.println("Cannot find " + target);
        }
    }

    public void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());
            System.out.print(" => " + node.getName());
            inOrderTraversal(node.getRight());
        }
    }

    // Print the number of comparisons for each search
    public void printNumComparisons() {
        System.out.println("Number of Comparisons: " + this.numComparisons);
    }

    // Print the average number of comparisons after a given number of searches
    public void printAvgComparison(int total) {
        System.out.printf("Average Number of Comparisons:  %.2f %n", this.totalComparisons/total);
    }

    /* Getters and Setters */
    public Node getRoot() {
        return this.root;
    } // getRoot

    public void setRoot(Node newRoot) {
        this.root = newRoot;
    } // setRoot
}
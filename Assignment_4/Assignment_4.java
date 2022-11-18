import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Assignment_4 {

    public static void main(String[] args) {

        // Get magic items --------------------------------------------------
        File file = new File("magicitems.txt");
        Scanner myReader = null;
        String[] magicItems = null;
        int i = 0;
        int numLines = 0;
        
        try {
            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                // count the number of lines in the file
                numLines++;
                myReader.nextLine();
            }
            myReader.close();

            // create magic items array
            magicItems = new String[numLines];

            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                // initialize magic items array with magic items
                String data = myReader.nextLine();
                magicItems[i] = data.toLowerCase();
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        // END get magic items ------------------------------------------------


        // Get the target magic items -----------------------------------------
        file = new File("magicitems-find-in-bst.txt");
        String[] targetMagicItems = null;
        i = 0;
        numLines = 0;
        
        try {
            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                // count the number of lines in the file
                numLines++;
                myReader.nextLine();
            }
            myReader.close();

            // create target magic items array
            targetMagicItems = new String[numLines];

            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                // initialize with target magic items
                String data = myReader.nextLine();
                targetMagicItems[i] = data.toLowerCase();
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        // END get the target magic items -----------------------------------------

    
        // Binary Search Tree -----------------------------------------------------
        BinarySearchTree myBST = new BinarySearchTree();
        for (i=0; i<magicItems.length; i++) {
            myBST.insert(magicItems[i]);
        }

        for (i=0; i<targetMagicItems.length; i++) {
            // myBST.printNodePath(targetMagicItems[i]);
            // myBST.printNumComparisons();
            // System.out.println();
        }

        // myBST.printAvgComparison(targetMagicItems.length);

        // Print an in-order traversal of the tree
        // myBST.inOrderTraversal(myBST.getRoot());

        // END Binary Search Tree --------------------------------------------------


        // Undirected Graph --------------------------------------------------------

        // Get the graph file and process graph data
        file = new File("graphs1.txt");
        int numGraphs = 0;

        try {
            // First, count the number of graphs in the file
            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                if (myReader.nextLine().contains("new")) {
                    numGraphs++;
                }
            }
            // Initialize an array of graphs
            Graph[] graphs = new Graph[numGraphs];
            System.out.println("Number of graphs in file: " + numGraphs);
            myReader.close();

            // Next, process the graph data for each graph
            myReader = new Scanner(file);
            i=0;
            while (i < numGraphs) {
                if (myReader.nextLine().contains("new")) {
                    String line = myReader.nextLine();
                    String verticesStr = "";
                    while (line.contains("vertex")) {
                        // Get the vertex id (vid)
                        String vid = line.substring(line.lastIndexOf(" ") + 1);
                        // Keep a string of vid data
                        verticesStr += vid + " ";
                        line = myReader.nextLine();
                    }

                    // Create the graph using an array of vid's
                    String[] verticeArr = verticesStr.split(" ");
                    graphs[i] = new Graph (verticeArr);
                    for (int j=verticeArr.length-1; j>=0; j--) {
                        graphs[i].addVertex(verticeArr[j]);
                    }
            
                    // Create all the edges
                    while (line.contains("edge")) {
                        // The line substring with edge data
                        String edgeStr = line.substring(9);

                        // Get each vid connecting the edge and create the edge
                        String[] vertices = edgeStr.split(" - ");
                        String vertex1 = vertices[0];
                        String vertex2 = vertices[1];
                        graphs[i].createEdge(vertex1, vertex2);

                        if (myReader.hasNextLine()) {
                            line = myReader.nextLine();
                        } else {
                            break;
                        }
                    }
                    System.out.println("--------------------------------");
                    graphs[i].getVertices().print();
                    System.out.println();
                    graphs[i].getAdjacencyList().print();
                    System.out.println();
                    graphs[i].getMatrix().print();
                    System.out.println("--------------------------------");
                    i++;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        // END Undirected Graph ----------------------------------------------------




















        // test file
        /* 
        file = new File("test.txt");
        myReader = null;
        String[] testItems = null;
        i = 0;
        numLines = 0;
        
        try {
            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                // count the number of lines in the file
                numLines++;
                myReader.nextLine();
            }
            myReader.close();

            // create test items array
            testItems = new String[numLines];

            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                // initialize test items array with test items
                String data = myReader.nextLine();
                testItems[i] = data.toLowerCase();
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        BinarySearchTree testBST = new BinarySearchTree();
        for (i=0; i<testItems.length; i++) {
            testBST.insert(testItems[i]);
        }
        testBST.printNodePath("march");
        testBST.printNodePath("january");
        testBST.printNodePath("august");
        testBST.printNodePath("may");
        testBST.printNodePath("summer"); */
    }
}
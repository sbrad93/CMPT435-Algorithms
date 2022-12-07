import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Assignment_5 {

    public static void main(String[] args) {

        // 1) Directed and Weighted Graphs --------------------------------------------------------

        // Get the graph file and process graph data
        File file = new File("graphs.txt");
        int numGraphs = 0;

        try {
            // First, count the number of graphs in the file
            Scanner myReader = new Scanner(file);
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
            int i=0;
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
                    for (int j=0; j<verticeArr.length; j++) {
                        graphs[i].addVertex(verticeArr[j]);
                    }
            
                    // Create all the edges
                    while (line.contains("edge")) {
                        // The line substring with edge data
                        String edgeStr = line.substring(9);

                        // The edgeStr substring with vertice data
                        String verticeStr = edgeStr.substring(0, edgeStr.lastIndexOf(" "));
                        String weight = edgeStr.substring(edgeStr.lastIndexOf(" ") + 1);

                        // Get each vid connecting the edge and create the edge
                        String[] vertices = verticeStr.split(" - ");
                        String vertex1 = vertices[0].trim();
                        String vertex2 = vertices[1].trim();
                        graphs[i].createEdge(vertex1, vertex2, Integer.parseInt(weight));

                        if (myReader.hasNextLine()) {
                            line = myReader.nextLine();
                        } else {
                            break;
                        }
                    }
                    System.out.println("-----------------------------------------------------------------------------");
                    System.out.println("Graph " + i + ":");
                    boolean isValid = graphs[i].bellmanFord();
                    if (isValid) {
                        graphs[i].getResults();
                    } else {
                        System.out.print("not valid :(");
                    }
                    i++;
                    System.out.println("-----------------------------------------------------------------------------");
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        // END Directed and Weighted Graphs -------------------------------------------------------------------------




        // 2) Greedy Algorithms - Spice Heist ----------------------------------------------------------------------
        file = new File("spice.txt");
        int numSpices = 0;
        int numKnapsacks = 0;

        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.startsWith("spice")) {
                    numSpices++;
                } else if (data.startsWith("knapsack")) {
                    numKnapsacks++;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    
        Spice[] spices = new Spice[numSpices];
        Knapsack[] knapsacks = new Knapsack[numKnapsacks];

        try {
            Scanner myReader = new Scanner(file);
            int i = 0;
            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();

                // initialize an array of spices
                if (data.startsWith("spice")) {
                    while (i < numSpices) {
                        // 1. get substring after 'spice'
                        // 2. remove whitespace
                        // 3. split at ';'
                        String[] spiceData = data.substring(6).replaceAll("\\s", "").split(";");
                        if (spiceData.length == 3) { // color, total price, quantity
                            String color = spiceData[0].split("=")[1];
                            String totalPrice = spiceData[1].split("=")[1];
                            String quantity = spiceData[2].split("=")[1];
                            Spice spice = new Spice(color, Float.parseFloat(totalPrice), Integer.parseInt(quantity));
                            spices[i] = spice;
                        }
                        data = myReader.nextLine();
                        i++;
                    }
                  // initialize an array of knapsacks
                } else if (data.startsWith("knapsack")) {
                    i = 0;
                    while (i<numKnapsacks) {
                        // 1. get substring after 'knapsack'
                        // 2. remove whitespace
                        // 3. split at ';'
                        String[] knapsackData = data.substring(9).replaceAll("\\s", "").split(";");
                        if (knapsackData.length == 1) { // capacity
                            String capacity = knapsackData[0].split("=")[1];
                            Knapsack knapsack = new Knapsack(Integer.parseInt(capacity));
                            knapsacks[i] = knapsack;
                        }
                        // check there is another line, otherwise break
                        if (myReader.hasNextLine()) {
                            data = myReader.nextLine();
                        } else {
                            break;
                        }
                        i++;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
       
        // sort the spices by unit price, high to low
        InsertionSort insertionSortObj = new InsertionSort();
        insertionSortObj.sort(spices);

        for (int i=0; i<knapsacks.length; i++) {
            for (int j=0; j<spices.length; j++) {
                int remaining = spices[j].getQuantity();
                // add a given spice until the knapsack is full or a spice has no more scoops remaining
                while (!knapsacks[i].isFull() && remaining != 0) {
                    knapsacks[i].fill(spices[j]);
                    remaining--;
                }
            }
            knapsacks[i].print();
        }
        // END Spice Heist ------------------------------------------------
    }
}
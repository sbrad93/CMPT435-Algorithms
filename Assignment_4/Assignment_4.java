import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Assignment_4 {

    public static void main(String[] args) {

        // Get magic items
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
        // -------------------------------------------------------------------------------

        // Get the target magic items
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
        // -------------------------------------------------------------------------------
        
        BinarySearchTree myBST = new BinarySearchTree();
        for (i=0; i<magicItems.length; i++) {
            myBST.insert(magicItems[i]);
        }

        for (i=0; i<targetMagicItems.length; i++) {
            myBST.printNodePath(targetMagicItems[i]);
            myBST.printNumComparisons();
            System.out.println();
        }

        myBST.printAvgComparison(targetMagicItems.length);
        // myBST.inOrderTraversal(myBST.getRoot());







/*      // test file
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
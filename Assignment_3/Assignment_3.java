import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

class Assignment_3 {

    public static Random randomGen = new Random();

    public static void main(String[] args) {

        // Get magic items from the input file
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

        // Sort the magic items
        InsertionSort sortObj = new InsertionSort();
        sortObj.sort(magicItems);

        // Get 42 random magic items
        String[] randItems = getRandomElements(magicItems);


        /* Linear Search -------------------------------------------------------------- */
        LinearSearch linearSearchObj = new LinearSearch();
        boolean areAllFound = false;
        i = 0;
        while (i<randItems.length) {
            int index = linearSearchObj.search(magicItems, randItems[i]);
            if (index == -1) {
                areAllFound = false;
                break;
            } else {
                areAllFound = true;
            }

            // Print individual number of comparisons
            // System.out.print("\n" + i + ": ");
            // linearSearchObj.printNumComparisons();
            i++;
        }
        // Print average number of comparisons
        linearSearchObj.printAvgComparison(randItems.length);

        System.out.println("-----------------------");
        if (areAllFound) {
            System.out.println("All items found!!");
        } else {
            System.out.println("Couldn't find " + randItems[i]);
        }
        /* END Linear Search -------------------------------------------------------------- */


         /* Binary Search -------------------------------------------------------------- */
        BinarySearch binarySearchObj = new BinarySearch();
        areAllFound = false;
        i = 0;
        while (i<randItems.length) {
            // Reset the number of comparisons each time new seach begins
            binarySearchObj.numComparisons = 0;

            int index = binarySearchObj.search(magicItems, randItems[i], 0, magicItems.length);
            if (index == -1) {
                areAllFound = false;
                break;
            } else {
                areAllFound = true;
            }

            // Keep track of total number of comparisons for all searches in order to find average later
            binarySearchObj.comparisonTotal += binarySearchObj.numComparisons;

            // Print individual number of comparisons
            // System.out.print("\n" + i + ": ");
            // binarySearchObj.printNumComparisons();
            i++;
        }
        // Print average number of comparisons
        binarySearchObj.printAvgComparison(randItems.length);

        System.out.println("-----------------------");
        if (areAllFound) {
            System.out.println("All items found!!");
        } else {
            System.out.println("Couldn't find " + randItems[i]);
        }
         /* END Binary Search -------------------------------------------------------------- */


         /* Hashing -------------------------------------------------------------- */
        Hashing hashingObj = new Hashing();
        HashTable hashTableObj = new HashTable(hashingObj.HASH_TABLE_SIZE);
        for (i=0; i<magicItems.length; i++) {
            int hashCode = hashingObj.makeHashCode(magicItems[i]);
            hashTableObj.put(hashCode, magicItems[i]);
        }

        // System.out.println("-----------------------");
        // hashTableObj.print();
        // System.out.println("-----------------------");

        areAllFound = false;
        i = 0;
        while (i<randItems.length) {
            // Recalculate hashcode value for each random item
            int hashCode = hashingObj.makeHashCode(randItems[i]);
            // Check if the item exists within hashcode list
            boolean found = hashTableObj.get(hashCode, randItems[i]);
            if (!found) {
                areAllFound = false;
                break;
            } else {
                areAllFound = true;
            }
            i++;

            // Print individual number of comparisons
            // System.out.print("\n" + j + ": ");
            // hashTableObj.printNumComparisons();
        }

        // Print average number of comparisons
        hashTableObj.printAvgComparison(randItems.length);

        System.out.println("-----------------------");
        if (areAllFound) {
            System.out.println("All items found!!");
        } else {
            System.out.println("Couldn't find " + randItems[i]);
        }
        /* END Hashing -------------------------------------------------------------- */
    }

    public static String[] getRandomElements(String[] array) {
        String[] randElements = new String[42];
        Random rand= new Random();

        for (int i=0; i<randElements.length; i++) {
            int randIndex = rand.nextInt(666);
            randElements[i] = array[randIndex];
        }
        return randElements;
    }
}
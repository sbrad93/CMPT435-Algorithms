import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javax.naming.BinaryRefAddr;

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

        // Sort the magic items
        InsertionSort sortObj = new InsertionSort();
        sortObj.sort(magicItems);

        // Get 42 random magic items
        String[] randItems = getRandomElements(magicItems);

        // Search for each of the random 42 items
        LinearSearch linearSearchObj = new LinearSearch();
        for (int j=0; j<randItems.length; j++) {
            linearSearchObj.search(magicItems, randItems[j]);

            // Print individual number of comparisons
            // System.out.print("\n" + j + ": ");
            // linearSearchObj.printNumComparisons();
        }
        // Print average number of comparisons
        // linearSearchObj.printAvgComparison(randItems.length);

        BinarySearch binarySearchObj = new BinarySearch();
        for (int j=0; j<randItems.length; j++) {
            // Reset the number of comparisons each time new seach begins
            binarySearchObj.numComparisons = 0;

            binarySearchObj.search(magicItems, randItems[j], 0, magicItems.length);

            // Keep track of total number of comparisons for all searches in order to find average later
            binarySearchObj.comparisonTotal += binarySearchObj.numComparisons;

            // Print individual number of comparisons
            System.out.print("\n" + j + ": ");
            binarySearchObj.printNumComparisons();
        }
        // Print average number of comparisons
        binarySearchObj.printAvgComparison(randItems.length);
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
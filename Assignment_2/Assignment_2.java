import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

class Assignment_2 {

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

// ---------------------------------------------------------------------

        // 1. Selection Sort
        shuffle(magicItems);
        SelectionSort ssObj = new SelectionSort();
        ssObj.sort(magicItems);
        // ssObj.print(magicItems);
        // ssObj.printNumComparisons();

        // 2. Insertion Sort
        shuffle(magicItems);
        InsertionSort isObj = new InsertionSort();
        isObj.sort(magicItems);
        // isObj.print(magicItems);
        // isObj.printNumComparisons();

        // 3. Merge Sort
        shuffle(magicItems);
        MergeSort msObj = new MergeSort();
        msObj.sort(magicItems, 0, magicItems.length-1);
        // msObj.print(magicItems);
        // msObj.printNumComparisons();

        // 4. Quicksort
        shuffle(magicItems);
        QuickSort qsObj = new QuickSort();
        qsObj.sort(magicItems, 0, magicItems.length-1);
        qsObj.print(magicItems);
        qsObj.printNumComparisons();;
    }

    public static void shuffle(String[] array) {
        int n = 0; // number of shuffled elements
        while (n < array.length-1) {
            n++;
            int randIndex = randomGen.nextInt(n); // select a random index value

            // swap the next array element with a random element
            String temp = array[n];
            array[n] = array[randIndex];
            array[randIndex] = temp;
        }
    }
}
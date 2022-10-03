import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

class Assignment_2 {

    public static Random randomGen = new Random();

    public static void main(String[] args) {

        File file = new File("magicitems.txt");
        Scanner myReader = null;
        String[] items = null;
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
            items = new String[numLines];

            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                // initialize magic items array with magic items
                String data = myReader.nextLine();
                items[i] = data;
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void shuffle(String[] magicItems) {
        int n = 0; // number of shuffled elements
        while (n < magicItems.length-1) {
            n++;
            int randIndex = randomGen.nextInt(n); // select a random index value

            // swap the next array element with a random element
            String temp = magicItems[n];
            magicItems[n] = magicItems[randIndex];
            magicItems[randIndex] = temp;
        }
    }
}
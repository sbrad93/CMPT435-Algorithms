import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Assignment_5 {

    public static void main(String[] args) {

        // 2) Greedy Algorithms - Spice Heist --------------------------------------------------
        File file = new File("spice.txt");
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
            int i =0;
            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();

                // collect all the spices in an array
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
                  // collect all the knapsacks in an array
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
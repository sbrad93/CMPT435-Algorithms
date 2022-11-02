import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {

        File file = new File("residentsPref.txt");
        Scanner myReader = null;

        String[] residents = new String[11];
        HashTable residentsPref = null;

        String[] hospitals = new String[5];
        HashTable hospitalsPref = null;

        int i = 0;
        int j = 0;
        try {

            // Get each of the resident names and put them in the resident list
            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                String arr[] = data.split(" ", 2);
                residents[i] = arr[0];

                i++;
            }
            myReader.close();

            // Create and initalize a hash table of resident preferences
            i = 0;
            residentsPref = new HashTable(residents.length+1);
            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                // Split the key value from the rest of the data
                String arr[] = data.split(" ", 2);
                String preferences[] = arr[1].split(" ", 0);

                int resKey = Integer.parseInt(arr[0].substring(arr.length-1));
                for (j=0; j<preferences.length; j++) {
                    // Populate the hash table
                    residentsPref.put(resKey, preferences[j]);
                }
            }
            myReader.close();



            // Get each of the hospital names and put them in the hospital list
            file = new File("hospitalsPref.txt");
            myReader = new Scanner(file);
            i = 0;
            j = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String arr[] = data.split(" ", 2);
                hospitals[i] = arr[0];

                i++;
            }
            myReader.close();

            // Create and initalize a hash table of hospital preferences
            i = 0;
            hospitalsPref = new HashTable(hospitals.length+1);
            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                // Split the key value from the rest of the data
                String arr[] = data.split(" ", 2);
                String preferences[] = arr[1].split(" ", 0);

                int hosKey = Integer.parseInt(arr[0].substring(arr.length-1));
                for (j=0; j<preferences.length; j++) {
                    // Populate the hash table
                    hospitalsPref.put(hosKey, preferences[j]);
                }
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        // -------------------------------------------------------------------------------

        // residentsPref.print();
        // hospitalsPref.print();

        StableMatching matchMaker = new StableMatching();
        matchMaker.doMatching(residents, hospitals, residentsPref, hospitalsPref);
    }
}
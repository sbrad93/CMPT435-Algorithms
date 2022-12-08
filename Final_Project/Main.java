import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Resident[] residents = null;
        HashTable residentsPref = null;

        Hospital[] hospitals = null;
        HashTable hospitalsPref = null;

    try {
        File file = new File("test.txt");
        Scanner myReader = new Scanner(file);
        int i = 0;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            // Get the number of residents and hospitals
            // Use these values to intialize resident and hospital arrays, respectively
            if (data.contains("Config")) {
                String[] configs = data.split(":")[1].trim().split(" ");
                String numResidents = configs[0];
                String numHospitals = configs[1];
                residents = new Resident[Integer.parseInt(numResidents)];
                hospitals = new Hospital[Integer.parseInt(numHospitals)];
            } 
            
            if (data.startsWith("r")) {
                residentsPref = new HashTable(residents.length+1);
                while (data.startsWith("r")) {
                    String[] dataArr = data.split(": ");
                    String resKey = dataArr[0].replaceAll("[^0-9]", "");
                    String[] preferences = dataArr[1].split(" ");

                    // Place the new resident in the resident array
                    residents[i] = new Resident(dataArr[0], preferences[0]);

                    // Put resident preferences in a hashtable
                    for (int k=0; k<preferences.length; k++) {
                        residentsPref.put(Integer.parseInt(resKey), preferences[k]);
                    }
                    data = myReader.nextLine();
                    i++;
                }
                
            } 

            if (data.startsWith("h")) {
                hospitalsPref = new HashTable(hospitals.length+1);
                i = 0;
                while (data.startsWith("h")) {
                    String[] dataArr = data.split(": ");
                    String hosKey = dataArr[0].replaceAll("[^0-9]", "");

                    String[] hosData = dataArr[1].split(" - ");
                    String capacity = hosData[0];
                    String[] preferences = hosData[1].split(" ");

                    // Place the new hospital in the hospital array
                    hospitals[i] = new Hospital(dataArr[0], Integer.parseInt(capacity));
                    
                    // Put the hospital preferences in a hashtable
                    for (int k=0; k<preferences.length; k++) {
                        hospitalsPref.put(Integer.parseInt(hosKey), preferences[k]);
                    }
                    
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
        // -------------------------------------------------------------------------------

        StableMatching matchMaker = new StableMatching(residents, hospitals, residentsPref, hospitalsPref);

        // Pt. 1
        System.out.println("Stable Matching, Pt I");
        HashTable myStableMatches = matchMaker.doMatching();
        myStableMatches.printPairings();
        System.out.println();

        // -------------------------------------------------------------------------------

        // Pt.2
        // Create list of resident first choices
        ArrayList<String> firstChoices = new ArrayList<String>();
        for (int i=0; i<residents.length; i++) {
            firstChoices.add(residents[i].getFirstChoice());
        }
        // Set the 'acceptance rate' for each hospital
        // Multiply the number of occurrences by a constant factor to ensure rate < 1
        for (int i=0; i<firstChoices.size(); i++) {
            int occurrences = Collections.frequency(firstChoices, firstChoices.get(i));
            for (int j=0; j<hospitals.length; j++) {
                if (firstChoices.get(i).compareTo(hospitals[j].getName()) == 0) {
                    hospitals[j].setAcceptanceRate(hospitals[j].getCapacity() / (occurrences*10));
                }
            }
        }

        System.out.println("Stable Matching, Pt II");
        HashTable moreMatches = matchMaker.doMatchingVariation();
        moreMatches.printPairings();
    }
}
import java.io.File;
import java.io.FileNotFoundException;
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
        double numH1 = 0;
        double numH2 = 0;
        double numH3 = 0;
        double numH4 = 0;
        double numH5 = 0;
        for (int i=0; i<residents.length; i++) {
            switch (residents[i].getFirstChoice()) {
                case "h1":
                    numH1++;
                    break;
                case "h2":
                    numH2++;
                    break;
                case "h3":
                    numH3++;
                    break;
                case "h4":
                    numH4++;
                    break;
                case "h5":
                    numH5++;
                    break;
            }
        }

        hospitals[0].setAcceptanceRate(hospitals[0].getCapacity() / (numH1*10));
        hospitals[1].setAcceptanceRate(hospitals[1].getCapacity() / (numH2*10));
        hospitals[2].setAcceptanceRate(hospitals[2].getCapacity() / (numH3*10));
        hospitals[3].setAcceptanceRate(hospitals[3].getCapacity() / (numH4*10));
        hospitals[4].setAcceptanceRate(hospitals[4].getCapacity() / (numH5*10));

        StableMatching matchMaker = new StableMatching(residents, hospitals, residentsPref, hospitalsPref);

        System.out.println("Stable Matching, Pt I");
        HashTable myStableMatches = matchMaker.doMatching();
        myStableMatches.printPairings();

        System.out.println();

        System.out.println("Stable Matching, Pt II");
        HashTable moreMatches = matchMaker.doMatchingVariation();
        moreMatches.printPairings();
    }
}
import java.util.Random;

public class StableMatching {

    private Resident[] residents = null;
    private Hospital[] hospitals = null;
    private HashTable residentsPref = null;
    private HashTable hospitalsPref = null;

    public StableMatching(Resident _residents[], 
                          Hospital _hospitals[], 
                          HashTable _residentsPref, 
                          HashTable _hospitalsPref) {
        this.residents = _residents;
        this.hospitals = _hospitals;
        this.residentsPref = _residentsPref;
        this.hospitalsPref = _hospitalsPref;
    }

    public HashTable doMatching() {
        HashTable matches = new HashTable(hospitals.length+1);

        // All residents start out as free
        Stack freeResidents = new Stack();
        for (int i=residents.length-1; i>=0; i--) {
            freeResidents.push(residents[i].getName());
        }

        // Array of residents already assigned a hospital
        String[] assignedResidents = new String[residents.length];


        while (!freeResidents.isEmpty()) {
            // Get the next resident and their hospital preferences
            String currResident = freeResidents.pop().getName();
            int resKey = Integer.parseInt(currResident.replaceAll("[^0-9]", ""));
            LinkedList currResidentPref = residentsPref.get(resKey);


            // Loop through the current resident's hospital preferences
            Node hospital = currResidentPref.getHead();
            while (hospital != null) {

                // Get the hospital name, capacity, and key
                String hospitalName = hospital.getName();
                int hospitalCapacity = getCapactiy(hospital.getName());
                int hosKey = Integer.parseInt(hospitalName.replaceAll("[^0-9]", ""));

                // Check if the resident has already been assigned a hospital
                boolean alreadyAssigned = false;
                for (int i=0; i<assignedResidents.length; i++) {
                    if (assignedResidents[i] != null && currResident.compareTo(assignedResidents[i]) == 0) {
                        alreadyAssigned = true;
                    }
                }

                // Match all unassigned residents
                if (!alreadyAssigned) {
                    // If a hospital has room, provisionally assign the resident
                    if (matches.get(hosKey) == null || 
                            matches.get(hosKey).getSize() < hospitalCapacity) {
                        matches.put(hosKey, currResident);
                        assignedResidents[resKey-1] = currResident;

                        // Check if the hospital is now full
                        if (matches.get(hosKey).getSize() == hospitalCapacity) {
                            // Remove the worst candidate
                            LinkedList currHospitalPref = hospitalsPref.get(hosKey);
        
                            // Remove the resident from hospital preferences
                            int i = currHospitalPref.getSize()-1;
                            String removedRes = currHospitalPref.removeAt(i);

                            // Remove the hospital from resident preferences
                            int removeKey = Integer.parseInt(removedRes.replaceAll("[^0-9]", ""));
                            LinkedList removedPref = residentsPref.get(removeKey);
                            removedPref.removeNode(hospital.getName());    
                        }

                        // Resident has been assigned to a hospital at this point, so we can go to the next resident
                        break;
                    } else {
                        // Hospital is already full, so we check for whack candidates that we can kick out (in the nicest way possible)
                        LinkedList currHospitalPref = hospitalsPref.get(hosKey);
                        Node activeAssignment = null;
                       
                        // Loop through current matches associated with current hospital, starting at the end of the list
                        int i = matches.get(hosKey).getSize()-1;
                        while (i >= 0) {
                            activeAssignment = matches.get(hosKey).getNode(i);

                            // The hospital prefers the current resident over the one currently assigned
                            if (currHospitalPref.getIndex(activeAssignment.getName()) > currHospitalPref.getIndex(currResident)) {
                                String removedResident = matches.get(hosKey).removeAt(i);

                                // We now have to reassign the removed resident
                                for (int j =0; j<assignedResidents.length; j++) {
                                    if (assignedResidents[j] != null && assignedResidents[j].compareTo(removedResident) == 0) {
                                        assignedResidents[j] = null;
                                        freeResidents.push(removedResident);
                                    }
                                }
                                // Create the new match with the current resident
                                matches.put(hosKey, currResident);

                                // We already found a matched resident that is a worse candidate than the current resident, so we don't need to keep looking
                                break;
                            }
                            i--;
                        }

                        // Check if the hospital is now full
                        if (matches.get(hosKey).getSize() == hospitalCapacity) {
                            currHospitalPref = hospitalsPref.get(hosKey);
        
                            // Remove the resident from hospital preferences
                            i = currHospitalPref.getSize()-1;
                            String removed = currHospitalPref.removeAt(i);

                            // Remove the hospital from the resident preferences
                            int removeKey = Integer.parseInt(removed.replaceAll("[^0-9]", ""));
                            LinkedList removedPref = residentsPref.get(removeKey);
                            removedPref.removeNode(hospital.getName());    

                            // We have to reassign the removed resident now so...
                            for (int j=0; j<assignedResidents.length; j++) {
                                if (assignedResidents[j] != null && removed.compareTo(assignedResidents[j]) == 0) {
                                    assignedResidents[j] = null;

                                    // Remove the initial match associated with the removed resident and push to free residents stack
                                    freeResidents.push(removed);
                                }
                            }
                        }
                        // Resident has been assigned to a hospital at this point, so we can go to the next resident
                        break;
                    }
                }
                hospital = hospital.getNext();
            }
        }
        return matches;
    }

    // Returns the capcaity of a given hospital
    public int getCapactiy(String hospitalName) {
        int capacity = 0;
        for (int i=0; i<hospitals.length; i++) {
            if (hospitals[i].getName().compareTo(hospitalName) == 0) {
                capacity = hospitals[i].getCapacity();
            }
        }
        return capacity;
    }

    // Matching variation where hospitals don't rank residents
    public HashTable doMatchingVariation() {
        HashTable matches = new HashTable(hospitals.length+1);

        // Place residents in random order
        shuffle(residents);

        // All residents start out as free
        Resident[] freeResidents = new Resident[residents.length];
        for (int i=0; i<residents.length; i++) {
            freeResidents[i] = residents[i];
        }

        // Sort the hospitals from most selective to least selective
        InsertionSort insertionSortObj = new InsertionSort();
        insertionSortObj.sort(hospitals);

        for (int i=0; i<hospitals.length; i++) {
            int hosKey = Integer.parseInt(hospitals[i].getName().replaceAll("[^0-9]", ""));

            // Place each (randomly selected) resident in their first choice as long as capacity hasn't been reached
            for (int j=0; j<freeResidents.length; j++) {
                if (freeResidents[j] != null) {
                    Resident currResident = freeResidents[j];
                    if (currResident.getFirstChoice().compareTo(hospitals[i].getName()) == 0) {
                        freeResidents[j] = null;
                        matches.put(hosKey, currResident.getName());

                        // Check if hospital reached capacity
                        if (matches.get(hosKey).getSize() == hospitals[i].getCapacity()) {
                            break;
                        }
                    }
                } 
            }
        }

        // Loop through all the residents that didn't get their first choice
        for (int i=0; i<freeResidents.length; i++) {
            if (freeResidents[i] != null) {
                int resKey = Integer.parseInt(freeResidents[i].getName().replaceAll("[^0-9]", ""));
                LinkedList currResidentPref = residentsPref.get(resKey);
                int j = 1;
                // Loop through each of the residents preferences until a spot is found
                while (freeResidents[i] != null) {
                    if (currResidentPref.getNode(j) == null) {
                        break;
                    } else {
                        String nextChoice = currResidentPref.getNode(j).getName();
                        int hosKey = Integer.parseInt(nextChoice.replaceAll("[^0-9]", ""));
                        int hospitalCapacity = getCapactiy(nextChoice);
                        
                        if (matches.get(hosKey) == null || matches.get(hosKey).getSize() < hospitalCapacity) {
                            matches.put(hosKey, freeResidents[i].getName());
                            freeResidents[i] = null;
                        }
                        j++;
                    }
                }
            }
        }
        return matches;
    }

    public void shuffle(Resident[] array) {
        Random randomGen = new Random();
        int n = 0; // number of shuffled elements
        while (n < array.length-1) {
            n++;
            int randIndex = randomGen.nextInt(n); // select a random index value

            // swap the next array element with a random element
            Resident temp = array[n];
            array[n] = array[randIndex];
            array[randIndex] = temp;
        }
    }
}
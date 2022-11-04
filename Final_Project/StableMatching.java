public class StableMatching {

    public StableMatching() {
    }

    public HashTable doMatching(String residents[], 
                           String hospitals[], 
                           HashTable residentsPref, 
                           HashTable hospitalsPref) {


        HashTable matches = new HashTable(hospitals.length+1);

        // All residents start out as free
        Stack freeResidents = new Stack();
        for (int i=residents.length-1; i>=0; i--) {
            freeResidents.push(residents[i]);
        }

        // Array of residents already assigned a hospital
        String[] assignedResidents = new String[residents.length];


        while (!freeResidents.isEmpty()) {
            // Get the next resident and their hospital preferences
            String currResident = freeResidents.pop().getName();
            int resKey = Integer.parseInt(currResident.substring(currResident.lastIndexOf("r") + 1));
            LinkedList currResidentPref = residentsPref.get(resKey);


            // Loop through the current resident's hospital preferences
            Node hospital = currResidentPref.getHead();
            while (hospital != null) {

                // Get the hospital name, capacity, and key
                String hospitalName = hospital.getName();
                int hospitalCapacity = getCapactiy(hospital.getName());
                int hosKey = Integer.parseInt(hospitalName.substring(hospitalName.lastIndexOf("h") + 1));

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
                            int removeKey = Integer.parseInt(removedRes.substring(removedRes.lastIndexOf("r") + 1));
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
                            int removeKey = Integer.parseInt(removed.substring(removed.lastIndexOf("r") + 1));
                            LinkedList removedPref = residentsPref.get(removeKey);
                            removedPref.removeNode(hospital.getName());    

                            // We have to reassign the removed resident now so...
                            for (int j=0; j<assignedResidents.length; j++) {
                                if (assignedResidents[j] != null && removed.compareTo(assignedResidents[j]) == 0) {
                                    assignedResidents[j] = null;

                                    // Remove the initial match associated with the removed resident and push to free residents stack
                                    String removedMatch = matches.get(hosKey).removeNode(removed);
                                    System.out.println(removedMatch);
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

    // Get the capcaity of a given hospital
    public int getCapactiy(String hospitalName) {
        int capacity = 0;
        switch (hospitalName) {
            case "h1":
                capacity = 4;
                break;
            case "h2":
                capacity = 3;
                break;
            case "h3":
                capacity = 3;
                break;
            case "h4":
                capacity = 2;
                break;
            case "h5":
                capacity = 1;
                break;
        }
        return capacity;
    }
}
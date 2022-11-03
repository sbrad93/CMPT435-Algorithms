public class StableMatching {

    public StableMatching() {
    }

    public void doMatching(String residents[], 
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

            System.out.println("----- Resident: " + currResident);

            // Loop through the current resident's hospital preferences
            Node hospital = currResidentPref.getHead();
            while (hospital != null) {
                // Get the hospital name, capacity, and key
                String hospitalName = hospital.getName();
                int hospitalCapacity = getCapactiy(hospital.getName());
                int hosKey = Integer.parseInt(hospitalName.substring(hospitalName.lastIndexOf("h") + 1));

                System.out.println("Hospital: " + hospitalName);
                if (matches.get(hosKey) != null) {
                    System.out.println("Current size: " + matches.get(hosKey).getSize());
                }

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

                        System.out.println("Assigning " + currResident + " to " + hospitalName);
                        matches.put(hosKey, currResident);
                        assignedResidents[resKey-1] = currResident;

                        // Check if the hospital is now full
                        if (matches.get(hosKey).getSize() == hospitalCapacity) {
                            // Remove the worst candidate
                            System.out.println("\nHospital is full, removing worst candidate...");
                            LinkedList currHospitalPref = hospitalsPref.get(hosKey);
        
                            // Remove the resident from hospital preferences
                            int i = currHospitalPref.getSize()-1;
                            String removed = currHospitalPref.remove(i);
                            System.out.println("Removed: " + removed);

                            // Remove the hospital from resident preferences
                            int removeKey = Integer.parseInt(removed.substring(removed.lastIndexOf("r") + 1));
                            LinkedList removedPref = residentsPref.get(removeKey);
                            removedPref.removeNode(hospital.getName());    
                        }

                        // Resident has been assigned to a hospital, so we can go to the next resident
                        break;


                    } else {
                        // Hospital is already full
                        System.out.println("\n(This shouldn't happen but.. ) Hospital is already full, removing worst candidate");
                        LinkedList currHospitalPref = hospitalsPref.get(hosKey);
                        Node activeAssignment = null;
                       
                        int i = matches.get(hosKey).getSize()-1;
                        while (i >= 0) {
                            activeAssignment = matches.get(hosKey).getNode(i);

                            int index = currHospitalPref.getIndex(currResident);
                            System.out.println("Curr Resident: " + currResident);
                            System.out.println("Curr Resident Index: " + index);

                            index = currHospitalPref.getIndex(activeAssignment.getName());
                            System.out.println("Active assignment: " + activeAssignment.getName());
                            System.out.println("Active Assignment Index: " + index);

                            // The hospital prefers the current resident over the one currently assigned
                            if (currHospitalPref.getIndex(activeAssignment.getName()) > currHospitalPref.getIndex(currResident)) {
                                String removedResident = matches.get(hosKey).remove(i);
                                System.out.println("Removing resident: " + removedResident);
                                for (int j =0; j<assignedResidents.length; j++) {
                                    if (assignedResidents[j] != null && assignedResidents[j].compareTo(removedResident) == 0) {
                                        System.out.println("Remvoing from assigned residents: " + assignedResidents[j]);
                                        assignedResidents[j] = null;
                                    }
                                }
                                freeResidents.push(removedResident);

                                System.out.println("Assigning " + currResident + " to " + hospitalName);
                                matches.put(hosKey, currResident);

                                matches.get(hosKey).print();
                                break;
                            }
                            System.out.println();
                            i--;
                        }

                        // Check if the hospital is now full
                        if (matches.get(hosKey).getSize() == hospitalCapacity) {
                            System.out.println("\nHospital is full, removing worst candidate...");
                            currHospitalPref = hospitalsPref.get(hosKey);
        
                            i = currHospitalPref.getSize()-1;
                            String removed = currHospitalPref.remove(i);
                            System.out.println("Removed: " + removed);

                            int removeKey = Integer.parseInt(removed.substring(removed.lastIndexOf("r") + 1));
                            LinkedList removedPref = residentsPref.get(removeKey);
                            removedPref.removeNode(hospital.getName());    

                            for (int j=0; j<assignedResidents.length; j++) {
                                if (assignedResidents[j] != null && removed.compareTo(assignedResidents[j]) == 0) {
                                    assignedResidents[j] = null;

                                    String removedMatch = matches.get(hosKey).removeNode(removed);
                                    System.out.println(removedMatch);

                                    freeResidents.push(removed);
                                }
                            }
                        }

                        break;
                    }
                }

                
                
                hospital = hospital.getNext();
            }
            System.out.println("\n\n------------------");
        }

        matches.print();

        // for (int i=0; i<assignedResidents.length; i++) {
        //     System.out.println(assignedResidents[i]);
        // }
    }

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

    public void rejectWorstAssignment() {

    }
}
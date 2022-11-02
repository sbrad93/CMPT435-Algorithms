public class StableMatching {

    public StableMatching() {
    }

    public void doMatching(String residents[], 
                           String hospitals[], 
                           HashTable residentsPref, 
                           HashTable hospitalsPref) {

        HashTable matches = new HashTable(hospitals.length+1);

        // All residents start out as free
        LinkedList freeResidents = new LinkedList();
        for (int i=0; i<residents.length; i++) {
            freeResidents.add(residents[i]);
        }

        // Array of residents already assigned a hospital
        String[] assignedResidents = new String[residents.length];

        while (!freeResidents.isEmpty()) {
            // Get the next resident and their preferences
            String currResident = freeResidents.remove(0);
            int resKey = Integer.parseInt(currResident.substring(currResident.lastIndexOf("r") + 1));
            LinkedList currResidentPref = residentsPref.get(resKey);

            // Loop through hospitals in resident preferences
            Node hospital = currResidentPref.getHead();
            String hospitalName = hospital.getName();
            while (hospital != null) {
                // Get the hospital capacity
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
                    if (matches.get(hosKey) == null ||
                        matches.get(hosKey).getSize() < hospitalCapacity-1) {
                        
                        matches.put(hosKey, currResident);
                        assignedResidents[resKey-1] = currResident;
                    } else {
                        // String activeAssignment = matches.get(resKey).getHead().getName();
                        // LinkedList currHospitalPref = hospitalsPref.get(hosKey);

                        // Node resident = currHospitalPref.getHead();
                        // int i = 0;
                        // while (resident.getNext() != null) {
                        //     if (resident.getName() == currResident) {
                        //         String rejectedResident = currHospitalPref.remove(i);
                        //     }
                        //     i++;
                        // }

                            System.out.println(hospital.getName() + " is full");
                    }
                } else {
                    // ????
                }
                
                hospital = hospital.getNext();  
            }
            System.out.println("\n\n------------------");
        }

        matches.print();

        for (int i=0; i<assignedResidents.length; i++) {
            System.out.println(assignedResidents[i]);
        }
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
            default:
                System.out.println("Invalid hospital name");
        }
        return capacity;
    }
}
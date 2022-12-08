class InsertionSort {

    int numComparisons = 0;

    public void sort(Hospital[] array) {
        int n = array.length;

        for (int i=1; i<n; i++) {
            Hospital currElement= array[i];
            int j = i-1;

            // compare the current element to the previous element
            while (j>= 0 && array[j].getAcceptanceRate() > currElement.getAcceptanceRate()) {
                // keep comparing until current element is no longer less than elements before
                array[j+1] = array[j];
                j = j-1;
                numComparisons++;
            }
            // move greater elements one index forward
            array[j+1] = currElement;
        }
    }

    public void print(Hospital[] array) {
        for (int i=0; i<array.length; i++) {
            array[i].print();
        }
    }

    public void printNumComparisons() {
        System.out.println("\nInsertion Sort\nNumber of Comparisons: " + numComparisons);
    }

}
class InsertionSort {

    int numComparisons = 0;

    InsertionSort() {
        this.numComparisons = 0;
    }

    public void sort(String[] array) {
        int n = array.length;

        for (int i=1; i<n; i++) {
            String currElement = array[i];
            int j = i-1;

            // compare the current element to the previous element
            while (j>= 0 && array[j].compareTo(currElement) > 0) {
                // keep comparing until current element is no longer less than elements before
                array[j+1] = array[j];
                j = j-1;
                this.numComparisons++;
            }
            // move greater elements one index forward
            array[j+1] = currElement;
        }
    }

    public void print(String[] array) {
        for (int i=0; i<array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public void printNumComparisons() {
        System.out.println("\nInsertion Sort\nNumber of Comparisons: " + this.numComparisons);
    }
}
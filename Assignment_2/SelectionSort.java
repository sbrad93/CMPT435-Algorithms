class SelectionSort {
    int numComparisons = 0;

    public void sort(String[] array) {
        int n = array.length;
        int minIndex = 0;

        // no need to traverse entire array
        // final element will already be in correct position
        for (int i=0; i<n-2; i++) {
            minIndex = i;
            for (int j=i+1; j<n; j++) {
                // find the index of the 'smallest' element
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
                numComparisons++;
            }
            // swap smallest element with the current element
            String temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public void print(String[] array) {
        for (int i=0; i<array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public void printNumComparisons() {
        System.out.println("\nSelection Sort\nNumber of Comparisons: " + numComparisons);
    }
}
class MergeSort {

    int numComparisons = 0;

    public void sort(String[] array, int start, int end) {
        if (start < end) {
            // find midpoint
            int mid = start + (end - start)/2;
 
            // sort first and second halves
            sort(array, start, mid);
            sort(array, mid + 1, end);

            // create arrays for elements on either side of midpoint
            String[] L = new String[mid-start+1];
            String[] R = new String[end-mid];
    
            // initialize data in temp arrays
            for (int i = 0; i < L.length; ++i)
                L[i] = array[start + i];
            for (int j = 0; j < R.length; ++j)
                R[j] = array[mid + 1 + j];
 
            // merge the sorted halves
            merge(array, start, L, R);
        }
    }

    public void merge(String[] array, int start, String[] L, String[] R) {
        // index for first half of array
        int i = 0;
        // index for second half of array
        int j = 0;
        // initial index of merged subarray
        int k = start;
        System.out.println(k);

        while (i < L.length && j < R.length) {
            if (L[i].compareTo(R[j]) <= 0) {
                array[k++] = L[i++];
            }
            else {
                array[k++] = R[j++];
            }
            numComparisons++;
        }
 
        // copy remaining elements for both halves of array
        while (i < L.length) {
            array[k++] = L[i++];
        }
        while (j < R.length) {
            array[k++] = R[j++];
        }
    }
 
    public void print(String[] array) {
        for (int i = 0; i < array.length; ++i) {
            System.out.println(array[i]);
        }
    }

    public void printNumComparisons() {
        System.out.println("\nMerge Sort\nNumber of Comparisons: " + numComparisons);
    }
}
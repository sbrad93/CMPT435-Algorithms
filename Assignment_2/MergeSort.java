class MergeSort {

    int numComparisons = 0;

    public void sort(String[] array, int start, int end) {
        if (start < end) {
            // find midpoint
            int mid = start+(end-start)/2;
 
            // sort first and second halves
            sort(array, start, mid);
            sort(array, mid + 1, end);

            // create arrays for elements on either side of midpoint
            String[] left = new String[mid-start+1];
            String[] right = new String[end-mid];

            // left index
            int i=0;
            // right index
            int j=0;
            // initialize arrays to be merged
            while (i<left.length) {
                left[i] = array[start + i];
                i++;
            }
            while (j<right.length) {
                right[j] = array[mid + j + 1];
                j++;
            }
                
            // merge the sorted halves
            merge(array, start, left, right);
        }
    }

    public void merge(String[] array, int start, String[] left, String[] right) {
        // index for first half of array
        int i = 0;
        // index for second half of array
        int j = 0;
        // initial index of merged subarray
        int k = start;

        while (i<left.length && j<right.length) {
            if (left[i].compareTo(right[j]) < 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
            numComparisons++;
        }
        // include remaining elements
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
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